# `.claude/settings.json` — annotated reference

This file mirrors `.claude/settings.json` with inline comments documenting the reasoning behind each
section. It is written in **JSONC** (JSON with `//` comments) — the real `settings.json` cannot
contain comments because standard JSON rejects them.

**Invariant:** the JSON payload in this file must stay byte-for-byte identical to
`.claude/settings.json` apart from the comments. If you change one, update the other in the same
commit.

```jsonc
{
  "$schema": "https://json.schemastore.org/claude-code-settings.json",

  // Explicit allow list controlling hook network traffic — empty, so no hook
  // can make outbound HTTP calls regardless of which hooks are installed.
  "allowedHttpHookUrls": [],
  
  // Prevents unnecessary noise in commits.
  "attribution": {
    "commit": "",
    "pr": ""
  },

  "hooks": {
  },

  // Explicit allow list controlling which env vars hooks can access — empty,
  // so no env var leaks into hook execution regardless of which hooks are
  // installed.
  "httpHookAllowedEnvVars": [],

  "permissions": {
    "allow": [
      "Bash(./gradlew)",
      "Bash(./gradlew *:assemble*)",
      "Bash(./gradlew *:bundle*)",
      "Bash(./gradlew *:check*)",
      "Bash(./gradlew *:dependencies)",
      "Bash(./gradlew *:install*)",
      "Bash(./gradlew *:lint*)",
      "Bash(./gradlew *:test*)",
      "Bash(./gradlew assemble*)",
      "Bash(./gradlew build)",
      "Bash(./gradlew bundle*)",
      "Bash(./gradlew check*)",
      "Bash(./gradlew clean)",
      "Bash(./gradlew dependencies)",
      "Bash(./gradlew lint*)",
      "Bash(./gradlew projects)",
      "Bash(./gradlew properties)",
      "Bash(./gradlew tasks)",
      "Bash(./gradlew tasks --all)",
      "Bash(./gradlew tasks --group *)",
      "Bash(./gradlew tasks --task *)",
      "Bash(./gradlew test*)",
      "Bash(./gradlew versionCatalogUpdate --interactive)",
      "Bash(git blame *)",
      "Bash(git branch)",
      "Bash(git diff *)",
      "Bash(git log *)",
      "Bash(git ls-files *)",
      "Bash(git ls-tree *)",
      "Bash(git show *)",
      "Bash(git status)",
      "Bash(git tag)"
    ],
    "deny": [
      "Bash(./gradlew * --gradle-user-home *)",
      "Bash(./gradlew * --init-script *)",
      "Bash(./gradlew * --project-dir *)",
      "Bash(./gradlew * -I *)",
      "Bash(./gradlew * -g *)",
      "Bash(./gradlew * -p *)",
      "Bash(./gradlew *:publish*)",
      "Bash(./gradlew *:sign*)",
      "Bash(./gradlew *:upload*)",
      "Bash(./gradlew --include-build *)",
      "Bash(./gradlew publish*)",
      "Bash(./gradlew sign*)",
      "Bash(./gradlew upload*)",
      "Bash(./gradlew versionCatalogUpdate)",
      "Bash(./gradlew wrapper*)",
      "Bash(git*branch*--delete*)",
      "Bash(git*branch*--force*)",
      "Bash(git*branch*-C*)",
      "Bash(git*branch*-D*)",
      "Bash(git*branch*-M*)",
      "Bash(git*branch*-d*)",
      "Bash(git*branch*-f*)",
      "Bash(git*checkout*)",
      "Bash(git*cherry-pick*)",
      "Bash(git*clean*)",
      "Bash(git*clone*)",
      "Bash(git*config*)",
      "Bash(git*credential*)",
      "Bash(git*filter-branch*)",
      "Bash(git*gc*)",
      "Bash(git*init*)",
      "Bash(git*merge*)",
      "Bash(git*mv*)",
      "Bash(git*push*-f*)",
      "Bash(git*push*--force*)",
      "Bash(git*rebase*)",
      "Bash(git*reflog*)",
      "Bash(git*remote*)",
      "Bash(git*reset*)",
      "Bash(git*reset*--hard*)",
      "Bash(git*restore*)",
      "Bash(git*revert*)",
      "Bash(git*rm*)",
      "Bash(git*stash*)",
      "Bash(git*submodule*)",
      "Bash(git*tag*--delete*)",
      "Bash(git*tag*--force*)",
      "Bash(git*tag*-d*)",
      "Bash(git*tag*-f*)",
      "Bash(git*update-ref*)",
      "Bash(git*worktree*)",
      "Edit(**/*.bks)",
      "Edit(**/*.cer)",
      "Edit(**/*.crt)",
      "Edit(**/*.der)",
      "Edit(**/*.env)",
      "Edit(**/*.env.*)",
      "Edit(**/*.jks)",
      "Edit(**/*.key)",
      "Edit(**/*.keystore)",
      "Edit(**/*.p12)",
      "Edit(**/*.pem)",
      "Edit(**/*.pfx)",
      "Edit(**/credentials.properties)",
      "Edit(**/local.properties)",
      "Edit(**/*credentials.json)",
      "Edit(**/*credentials*.json)",
      "Edit(**/*keystore.json)",
      "Edit(**/*keystore*.json)",
      "Edit(**/*services.json)",
      "Edit(**/*services*.json)",
      "Edit(**/.credentials/**)",
      "Edit(**/.secrets/**)",
      "Edit(**/credentials/**)",
      "Edit(**/secrets/**)",
      "Edit(/.claude/settings.json)",
      "Edit(/.claude/settings.local.json)",
      "Edit(/.mcp.json)",
      "Edit(/gradle/wrapper/gradle-wrapper.jar)",
      "Edit(/gradle/wrapper/gradle-wrapper.properties)",
      "Read(**/*.bks)",
      "Read(**/*.cer)",
      "Read(**/*.crt)",
      "Read(**/*.der)",
      "Read(**/*.env)",
      "Read(**/*.env.*)",
      "Read(**/*.jks)",
      "Read(**/*.key)",
      "Read(**/*.keystore)",
      "Read(**/*.p12)",
      "Read(**/*.pem)",
      "Read(**/*.pfx)",
      "Read(**/credentials.properties)",
      "Read(**/local.properties)",
      "Read(**/*credentials.json)",
      "Read(**/*credentials*.json)",
      "Read(**/*keystore.json)",
      "Read(**/*keystore*.json)",
      "Read(**/*services.json)",
      "Read(**/*services*.json)",
      "Read(**/.credentials/**)",
      "Read(**/.secrets/**)",
      "Read(**/credentials/**)",
      "Read(**/secrets/**)",
      "Write(**/*.bks)",
      "Write(**/*.cer)",
      "Write(**/*.crt)",
      "Write(**/*.der)",
      "Write(**/*.env)",
      "Write(**/*.env.*)",
      "Write(**/*.jks)",
      "Write(**/*.key)",
      "Write(**/*.keystore)",
      "Write(**/*.p12)",
      "Write(**/*.pem)",
      "Write(**/*.pfx)",
      "Write(**/credentials.properties)",
      "Write(**/local.properties)",
      "Write(**/*credentials.json)",
      "Write(**/*credentials*.json)",
      "Write(**/*keystore.json)",
      "Write(**/*keystore*.json)",
      "Write(**/*services.json)",
      "Write(**/*services*.json)",
      "Write(**/.credentials/**)",
      "Write(**/.secrets/**)",
      "Write(**/credentials/**)",
      "Write(**/secrets/**)",
      "Write(/.claude/settings.json)",
      "Write(/.claude/settings.local.json)",
      "Write(/.mcp.json)",
      "Write(/gradle/wrapper/gradle-wrapper.jar)",
      "Write(/gradle/wrapper/gradle-wrapper.properties)"
    ],
    "disableAutoMode": "disable",
    "disableBypassPermissionsMode": "disable"
  },
  "sandbox": {
    // Commands without a sandbox are never allowed, and startup fails closed
    // if the sandbox can't be initialized. `autoAllowBashIfSandboxed` stays
    // off so each Bash call still hits the permission allow/deny list even
    // when sandboxing is active.
    "allowUnsandboxedCommands": false,
    "autoAllowBashIfSandboxed": false,
    "enabled": true,
    "failIfUnavailable": true,
    "excludedCommands": [],
    "filesystem": {
      "allowRead": [
        ".",
        "~/.android",
        "~/.config/jgit",
        "~/.gitconfig",
        "~/.gitignore_global",
        "~/.gradle/.tmp",
        "~/.gradle/android",
        "~/.gradle/caches",
        "~/.gradle/daemon",
        "~/.gradle/init.d",
        "~/.gradle/native",
        "~/.gradle/notifications",
        "~/.gradle/wrapper/dists",
        "~/Applications/Android Studio.app",
        "~/Library/Java"
      ],
      // Known RCE surface, accepted as the cost of a working toolchain:
      //   /tmp, /var/folders             scratch space; attacker-writable
      //                                  files here become exploitable only
      //                                  in combination with an allowed
      //                                  command that reads them by path
      //   ~/.android                     Android SDK state, written by
      //                                  Gradle's Android plugin
      //   ~/.gradle/caches, daemon,      Gradle's dependency cache and
      //   native, notifications, .tmp    runtime state — a poisoned cache
      //                                  entry executes on the next build
      //   ~/.gradle/wrapper/dists        Gradle distributions the wrapper
      //                                  unpacks — swapping one replaces
      //                                  the Gradle binary itself
      // Required: Gradle and the Android toolchain refuse to run without
      // write access to these paths. Mitigated by the Bash allow list
      // (no arbitrary commands) and by keeping the sandbox `denyRead: ~`
      // so nothing in the home directory can be read back into Claude's
      // context for reflection-based attacks.
      "allowWrite": [
        "/tmp",
        "/var/folders",
        "~/.android",
        "~/.config/jgit",
        "~/.gradle/.tmp",
        "~/.gradle/android",
        "~/.gradle/caches",
        "~/.gradle/daemon",
        "~/.gradle/native",
        "~/.gradle/notifications",
        "~/.gradle/wrapper/dists"
      ],
      // Known risk, accepted: reads default to allowed everywhere except `~`.
      // A true deny-by-default read policy would have to enumerate every
      // path the toolchain touches — system frameworks under `/System` and
      // `/Library`, Xcode command-line tools under `/usr`, the JDK under
      // `/Library/Java` or inside Android Studio, shared libraries loaded
      // by `java`, `git`, and `adb`, and more. Any miss breaks the build.
      // `~` is the one directory with a concentrated payoff for exfil
      // (SSH keys, shell history, browser profiles, other projects), so it
      // is denied explicitly; the `allowRead` list above re-opens only the
      // subpaths Gradle and the Android toolchain need.
      "denyRead": [
        "~"
      ],
      // Blocks the two privilege-escalation paths Claude could reach from
      // inside a session:
      //   .claude/settings.json(.local) — if Claude could rewrite these it
      //                                   could broaden its own allow list,
      //                                   disable the sandbox, or lift the
      //                                   `~` read deny. Duplicated under
      //                                   Edit/Write in `permissions.deny`
      //                                   above as defense in depth.
      //   .mcp.json                     — registering a malicious MCP
      //                                   server is equivalent to adding an
      //                                   unrestricted tool to Claude.
      //   gradle/wrapper                — covers `gradle-wrapper.jar` and
      //                                   `gradle-wrapper.properties`; the
      //                                   jar is the Gradle binary, the
      //                                   properties file picks which
      //                                   distribution to download.
      // Residual risk: the Gradle distribution unpacked under
      // `~/.gradle/wrapper/dists` is writable (see `allowWrite` above) and
      // a poisoned cache entry under `~/.gradle/caches` can execute during
      // dependency resolution. Those surfaces cannot be closed without
      // breaking the toolchain; they remain accepted attack vectors.
      "denyWrite": [
        "./.claude/settings.json",
        "./.claude/settings.local.json",
        "./.mcp.json",
        "gradle/wrapper"
      ]
    },
    "network": {
      // Required by the Gradle daemon's FileLockContentionHandler, which
      // binds a local socket. Without this Gradle fails on startup with
      // `SocketException: Operation not permitted`.
      "allowLocalBinding": true,

      // Explicit allow list controlling outbound network traffic — only
      // our internal Artifactory is reachable. It acts as a caching proxy
      // for external Maven repositories, so all dependency resolution
      // (Gradle, git, hooks, ad-hoc shell) must go through it. External
      // repositories must never be accessed directly.
      "allowedDomains": [
        "artifactory.chrono24.net"
      ],
      "deniedDomains": []
    }
  }
}
```

## Known residual risks

Gaps identified during review that are intentionally not closed in the current config. Revisit if
the threat model changes or if tighter controls are compatible with the workflow.

### Project build files are writable

`allowWrite` implicitly includes the project root, and nothing in `denyWrite` protects the files
Gradle reads on every invocation:

- `build.gradle.kts` (root and every module)
- `settings.gradle.kts`
- `gradle.properties`
- `gradle/libs.versions.toml`
- `buildSrc/**`

An attacker who gets Claude to write into any of these via prompt injection has RCE on the next
allowed `./gradlew` call — `./gradlew tasks`, `./gradlew build`, etc. Closing this is low cost (add
the paths to `sandbox.filesystem.denyWrite`) but creates friction any time a build-file change is
intentional.

### Custom-task exfil / artifact push

`permissions.deny` blocks Gradle tasks whose names start with `publish`, `sign`, `upload`, or
`wrapper`. An attacker-written task named `deploy`, `myTask`, `refresh`, etc. is not caught.
Mitigating the build-file writes above removes this avenue, because the attacker needs to write the
task into a build file first.

Partly mitigated by `sandbox.network.allowedDomains: []` — even if a malicious task runs, it cannot
reach any remote endpoint to exfil or fetch a payload without the user first broadening the network
allow list.

### `~/.gradle/caches` and `~/.gradle/wrapper/dists` are writable

Already noted inline on `allowWrite`. The Gradle dependency cache and wrapper distribution directory
are writable because Gradle cannot function without them. A poisoned cache entry executes on the
next build, and a rewritten wrapper distribution replaces the Gradle binary itself. These surfaces
cannot be closed without breaking the toolchain; they remain accepted.

### Global read, only `~` denied

Documented inline on `denyRead`. `/` is broadly readable because the JDK, Xcode tools, system
frameworks, and dynamic libraries live outside any path we could reasonably enumerate. The
concentrated exfil target — the home directory — is denied, but anything not under `~` remains
readable. Accepted.
