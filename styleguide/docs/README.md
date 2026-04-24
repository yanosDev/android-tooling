# Styleguide Component Documentation

The `styleguide` module is a Compose design system for Android built on the **Atomic Design** pattern. Components are
organized into three tiers:

- **Atoms** — smallest, self-contained primitives (text, icon, button shell, divider, indicators, progress).
- **Molecules** — compositions of atoms that form a recognizable UI unit (primary button, tab row, top app bar,
  scaffold).
- **Organisms** — full-screen patterns that wire architecture together (default screen layout, ViewModel contract,
  UIState machine).

All components live under the package `de.yanosdev.styleguide.theme.*` and must be called with named arguments at all
call sites inside `de.yanosdev.*` code.

---

## Contents

- [Atoms](atoms.md)
- [Molecules](molecules.md)
- [Organisms](organisms.md)
