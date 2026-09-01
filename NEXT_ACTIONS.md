# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 3/3 (100.0%)
- **Function parity:** 12/13 matched (target 21) — 92.3%
- **Class/type parity:** 4/4 matched (target 6) — 100.0%
- **Combined symbol parity:** 16/17 matched (target 27) — 94.1%
- **Average inline-code cosine:** 0.45 (function body across 3 matched files)
- **Average documentation cosine:** 0.49 (doc text across 3 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 1 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. cmp_any.eq

- **Target:** `cmpany.PartialEqAny [PROVENANCE-FALLBACK]`
- **Similarity:** 0.63
- **Dependents:** 0
- **Priority Score:** 11003.7
- **Functions:** 6/7 matched (target 12)
- **Missing functions:** `token`
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 2/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `eq.rs` vs expected `eq.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:eq.rs` vs expected `eq.rs`
- **Proposed provenance header:** `// port-lint: source eq.rs` (current: `// port-lint: source eq.rs`)
- **Proposed provenance header:** `// port-lint: tests eq.rs` (current: `// port-lint: tests eq.rs`)
- **Lint issues:** 2

### 2. cmp_any.ord

- **Target:** `cmpany.OrdAny [PROVENANCE-FALLBACK]`
- **Similarity:** 0.71
- **Dependents:** 0
- **Priority Score:** 702.9
- **Functions:** 6/6 matched (target 9)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ord.rs` vs expected `ord.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ord.rs` vs expected `ord.rs`
- **Proposed provenance header:** `// port-lint: source ord.rs` (current: `// port-lint: source ord.rs`)
- **Proposed provenance header:** `// port-lint: tests ord.rs` (current: `// port-lint: tests ord.rs`)
- **Lint issues:** 2

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Matched

| Source | Target | Path |
|--------|--------|------|
| `cmp_any.lib` | `cmpany.Lib` | `cmp_any/src/lib` |

