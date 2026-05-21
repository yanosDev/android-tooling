---
paths:
  - "**/*.kt"
---

# Kotlin conventions

## Multi-line lambdas — name the parameter

`it` is fine inside a single-line lambda. Once the lambda spans multiple lines, give the parameter
a name. `it` becomes ambiguous as the body grows or as lambdas nest inside other lambdas.

```kotlin
// good
list.map { item ->
    item.copy(
        name = item.name.trim(),
        // ...
    )
}

// avoid
list.map {
    it.copy(
        name = it.name.trim(),
        // ...
    )
}
```

## Named arguments

Always use named arguments when calling `data class` constructors — names document each field at
the call site and survive field reordering without silent type-compatible swaps.

For other functions, named arguments are optional; prefer them when a call has more than one
parameter of the same type, or when a boolean/lambda parameter would be unclear positionally.
