---
paths:
  - "**/app/**/*.kt"
  - "**/nav/**/*.kt"
  - "**/model/**/*.kt"
  - "**/styleguide/**/*.kt"
  - "**/feature/**/*.kt"
---

# Compose conventions

## Named arguments for `@Composable` functions

Always use named arguments when calling `@Composable` functions. Composables typically take many
parameters of similar types (`Modifier`, lambdas, booleans). Positional calls break on signature
changes and read poorly.
