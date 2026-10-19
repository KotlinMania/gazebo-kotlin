# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 2/15 (13.3%)
- **Function parity:** 0/98 matched (target 4) — 0.0%
- **Class/type parity:** 0/29 matched (target 2) — 0.0%
- **Combined symbol parity:** 0/127 matched (target 6) — 0.0%
- **Average inline-code cosine:** 0.00 (function body across 2 matched files)
- **Average documentation cosine:** 0.09 (doc text across 2 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 2 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. ext.iter

- **Target:** `dupe.Iter [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 131310.0
- **Functions:** 0/9 matched (target 3)
- **Missing functions:** `try_any`, `check`, `try_all`, `try_eq_by`, `try_cmp_by`, `try_unzip`, `into_singleton`, `owned`, `next`
- **Types:** 0/4 matched (target 1)
- **Missing types:** `IterExt`, `IterOwned`, `Item`, `Owned`
- **Provenance warning:** port-lint provenance header matched only by basename: `dupe/src/iter.rs` vs expected `ext/iter.rs`
- **Proposed provenance header:** `// port-lint: source ext/iter.rs` (current: `// port-lint: source dupe/src/iter.rs`)
- **Lint issues:** 1

### 2. ext.option

- **Target:** `dupe.Option [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 30310.0
- **Functions:** 0/1 matched
- **Missing functions:** `try_map`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `OptionExt`, `Item`
- **Provenance warning:** port-lint provenance header matched only by basename: `dupe/src/option.rs` vs expected `ext/option.rs`
- **Proposed provenance header:** `// port-lint: source ext/option.rs` (current: `// port-lint: source dupe/src/option.rs`)
- **Lint issues:** 1

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

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |

