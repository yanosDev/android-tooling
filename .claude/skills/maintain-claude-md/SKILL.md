---
name: maintain-claude-md
description: Use this skill when creating, auditing, editing, or restructuring a CLAUDE.md file, or when deciding whether content belongs in CLAUDE.md vs. a path-scoped rule (`.claude/rules/`) vs. a skill (`.claude/skills/`) vs. a hook. Encodes the principle that CLAUDE.md is an AI-agent instruction file (not a developer onboarding doc), that every line must earn its tokens every session, and that procedural knowledge tied to specific file types belongs in path-scoped rules instead.
---

# Maintaining CLAUDE.md

CLAUDE.md is an instruction file for AI agents working in the repo. It is **not** developer
onboarding documentation. It loads into every conversation and competes with the user's actual
prompt for context. Every line must earn its tokens by being relevant to coding work — not project
history, human setup, or restated tooling rules.

## What CLAUDE.md is for

- Project background that frames every conversation: language/framework, application id quirks,
  deployment shape, where CI lives.
- Build/test/lint commands the agent will commonly run, with project-specific quirks (variants,
  flavors, mandatory flags).
- Architectural facts that shape *where* code goes: module layering, dependency rules, the layered
  chain.
- Workflow conventions the agent must follow when producing artifacts: branch name format, commit
  message format.
- Pointers to path-scoped rules and skills so the agent can find them when relevant.

## What CLAUDE.md is NOT for

- **Setup gotchas a human hits once at project bootstrap** (credentials files, vault links, IDE
  configuration, CA installs, emulator setup). The agent inherits a working tree.
- **Anything one query away.** Module lists (`settings.gradle.kts` is canonical), plugin folders (
  `ls`), package layout in any feature module, custom Gradle tasks (greppable from `buildSrc`).
  Discoverable, do not list.
- **Lint/ArchUnit/ktlint-enforced rules.** If tooling enforces it, the agent sees the failure.
  Restating is cargo cult.
- **Decorative ASCII diagrams.** They rarely beat a precise sentence. Diagrams that imply false
  relationships are worse than no diagram.
- **README content already in the repo.** Summarize what the agent must know; let them read deeper
  if needed.
- **Procedures only relevant to one part of the codebase.** Move to `.claude/rules/<x>.md` with
  `paths:` frontmatter so it loads only when matching files are touched.
- **User-side workflow.** Local-backend setup, Android Studio run configs, "in your IDE do X" — not
  agent concerns.
- **Generic developer practices.** "Provide good error messages", "write unit tests", "never commit
  secrets" — base instructions cover these.

## Where content actually belongs

| Need                                                          | Goes to                                             |
|---------------------------------------------------------------|-----------------------------------------------------|
| Always-relevant fact that frames any task                     | CLAUDE.md                                           |
| Procedural knowledge tied to specific file types/paths        | `.claude/rules/<name>.md` with `paths:` frontmatter |
| Multi-step procedure invoked by intent (not by file open)     | `.claude/skills/<name>/SKILL.md`                    |
| Deterministic enforcement at tool-call boundary (block/check) | Hook in `.claude/settings.json`                     |
| Cross-session learning Claude should record itself            | Auto memory                                         |

### Rule vs. skill (the close call)

- **Path-scoped rule** when the natural trigger is "the agent is touching files of type X." It
  auto-loads when a matching path is read; no description matching, no model judgment.
- **Skill** when the natural trigger is "the agent's *intent* is to do X" and intent might not
  co-occur with reading any specific file (e.g., authoring a new module from scratch).

Prefer the rule when both fit. Path matching is deterministic; description matching is fuzzy.

### When a hook?

Hooks are right for deterministic checks at tool-call boundaries — *not* for carrying guidance.
Three pitfalls:

- **Slow checks on every tool use** (lint, full test suite) tank iteration speed. If the check is
  slow, prefer a `Stop` hook over `PostToolUse`, or skip the hook entirely if violations are rare.
- **Hooks block; they don't teach.** If the agent needs to *understand* the rule, a rule or skill is
  the right shape; the hook is a backstop.
- **Cost of getting the hook wrong > cost of occasional violation.** Reviewers and CI catch most
  things. Don't add a hook for a problem the agent rarely creates.

## Methodology — auditing an existing CLAUDE.md

1. **Question every line.** Read line by line. For each: "if I cut this, what breaks?" If the answer
   is "nothing — the agent figures it out," cut it.
2. **Verify claims against the code, not the README.** READMEs drift; they often describe
   aspirational structure or older conventions. Open `build.gradle.kts`, convention plugins, and a
   representative source file before stating a dependency, layering rule, or pattern.
3. **Anchor patterns to canonical reference files.** Don't describe a pattern abstractly when one
   good example exists in the codebase — point at it (`feature/<x>/...Screen.kt`). Reduces words;
   stays accurate as code evolves; lets the agent learn from real code.
4. **Audit shape before content.** Before sharpening prose, ask: should this section exist at all in
   CLAUDE.md, or somewhere else?
5. **Target ≤80 lines.** Longer files reduce adherence and pay tokens every session. Skills and
   rules can be longer because they're conditionally loaded.
6. **Don't trust `/init` output verbatim.** It produces a generic dump from README + filesystem. The
   value is in pruning to what the agent actually needs.

## Common cuts (concrete examples)

- "Setup" sections, vault links, credentials.properties templates → README, not CLAUDE.md.
- "Common Development Tasks" / "Tips" / "Support and Documentation" → fluff unless directly
  load-bearing.
- "Build flavors" sections explaining keystores and IDE run configs → user workflow, not agent.
- Code-style listings of things lint enforces → lint reports them.
- Module file trees, plugin folder lists → `ls`-discoverable.
- "After changes run lint + tests" → in base instructions; restating doesn't add.
- "Match the convention in `git log`" → log often has bad examples; cite an external standard
  instead (e.g., Chris Beams).
- Single-test command examples (`--tests "*Foo"`) → standard Gradle, agent already knows.

## Worked example — extracting a screen-pattern rule

Starting state: CLAUDE.md had ~6 bullets describing the screen composable pattern, the sealed
`Action` convention, screen-side tracking via `${Name}TrackingDefinitions.kt`, ViewModel base class,
and preview rules. ~20 lines, always loaded, only relevant when touching screens.

Decision:

- Skill? Trigger is "working on a screen" — but the agent often modifies an existing screen by
  reading its file. File-read is the natural trigger.
- Path-scoped rule? Yes —
  `paths: ["**/feature/**/*Screen.kt", "**/feature/**/*ViewModel.kt", "**/feature/**/*TrackingDefinitions.kt"]`
  matches both modification and creation flows (anyone writing a new screen reads an existing one to
  learn the pattern).

Result:

- `.claude/rules/screens.md` carries the full pattern with code excerpts and a canonical reference
  file.
- CLAUDE.md drops to a one-liner pointer: "Screen / ViewModel / tracking / preview conventions live
  in `.claude/rules/screens.md` and load automatically when a `*Screen.kt`, `*ViewModel.kt`, or
  `*TrackingDefinitions.kt` file is touched."
- Saved ~15 lines of always-loaded context; the rule still fires when needed.

## When patterns change

When a project pattern migrates (e.g., tracking moves out of ViewModels into screen-side
definitions), update CLAUDE.md and the relevant `.claude/rules/<x>.md` *with* the migration. **Stale
instructions are worse than missing ones** — agents follow them and produce out-of-style code that
reviewers must catch.

## Editing process

- Make changes in small passes. Each pass cuts or sharpens one section.
- After substantive cuts, re-read end to end. Sections often become redundant when adjacent content
  is removed.
- Run new content past the test: "would I want this in context every conversation?"
- For each line/bullet you keep: "is this true *right now*, or describing how things used to be?"