# port-lint Proposed Changes

**Generated:** 2026-09-01
**Source:** tmp
**Target:** src/commonMain/kotlin/io/github/kotlinmania/gazebo

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/gazebo/cmpany/PartialEqAny.kt` | `// port-lint: source eq.rs` | `// port-lint: source eq.rs` | `eq.rs` | `port-lint provenance header matched only after fallback normalization: 'eq.rs' vs expected 'eq.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/gazebo/cmpany/PartialEqAnyTest.kt` | `// port-lint: tests eq.rs` | `// port-lint: tests eq.rs` | `eq.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:eq.rs' vs expected 'eq.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/gazebo/cmpany/OrdAny.kt` | `// port-lint: source ord.rs` | `// port-lint: source ord.rs` | `ord.rs` | `port-lint provenance header matched only after fallback normalization: 'ord.rs' vs expected 'ord.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/gazebo/cmpany/OrdAnyTest.kt` | `// port-lint: tests ord.rs` | `// port-lint: tests ord.rs` | `ord.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:ord.rs' vs expected 'ord.rs'` |
