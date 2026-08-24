# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 3/3 (100.0%)
- **Function parity:** 12/13 matched (target 21) — 92.3%
- **Class/type parity:** 4/4 matched (target 7) — 100.0%
- **Combined symbol parity:** 16/17 matched (target 28) — 94.1%
- **Average inline-code cosine:** 0.78 (function body across 3 matched files)
- **Average documentation cosine:** 0.49 (doc text across 3 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 0 files with <0.60 function similarity

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

### 3. cmp_any.lib

- **Target:** `cmpany.Lib [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

