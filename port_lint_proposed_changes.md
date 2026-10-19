# port-lint Proposed Changes

**Generated:** 2026-10-18
**Source:** tmp/gazebo/gazebo/src
**Target:** src/commonMain/kotlin

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/gazebo/dupe/Iter.kt` | `// port-lint: source dupe/src/iter.rs` | `// port-lint: source ext/iter.rs` | `ext/iter.rs` | `port-lint provenance header matched only by basename: 'dupe/src/iter.rs' vs expected 'ext/iter.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/gazebo/dupe/Option.kt` | `// port-lint: source dupe/src/option.rs` | `// port-lint: source ext/option.rs` | `ext/option.rs` | `port-lint provenance header matched only by basename: 'dupe/src/option.rs' vs expected 'ext/option.rs'` |
