# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 11/15 (73.3%)
- **Function parity:** 77/91 matched (target 156) — 84.6%
- **Class/type parity:** 20/24 matched (target 39) — 83.3%
- **Combined symbol parity:** 97/115 matched (target 195) — 84.3%
- **Average inline-code cosine:** 0.49 (function body across 11 matched files)
- **Average documentation cosine:** 0.46 (doc text across 11 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 8 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. cell

- **Target:** `gazebo.Cell`
- **Similarity:** 0.68
- **Dependents:** 2
- **Priority Score:** 2022703.2
- **Functions:** 22/23 matched (target 38)
- **Missing functions:** `get_str`
- **Types:** 3/4 matched (target 8)
- **Missing types:** `Target`
- **Tests:** 7/8 matched

### 2. hash

- **Target:** `gazebo.Hash`
- **Similarity:** 0.72
- **Dependents:** 2
- **Priority Score:** 2001002.9
- **Functions:** 9/9 matched (target 12)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 3. cmp

- **Target:** `gazebo.Cmp`
- **Similarity:** 0.53
- **Dependents:** 1
- **Priority Score:** 1030504.7
- **Functions:** 1/4 matched (target 15)
- **Missing functions:** `eq`, `partial_cmp`, `cmp`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/4 matched

### 4. cast

- **Target:** `gazebo.Cast`
- **Similarity:** 0.38
- **Dependents:** 0
- **Priority Score:** 71706.2
- **Functions:** 9/16 matched (target 15)
- **Missing functions:** `downcast_string`, `drop`, `magic`, `to_bytes`, `from_bytes`, `test_transmute_wrong_sizes`, `test_transmute_wrong_alignment`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 3/10 matched

### 5. ext.vec

- **Target:** `ext.VecExt`
- **Similarity:** 0.29
- **Dependents:** 0
- **Priority Score:** 21707.1
- **Functions:** 10/11 matched
- **Missing functions:** `map`
- **Types:** 5/6 matched (target 5)
- **Missing types:** `Item`

### 6. file

- **Target:** `gazebo.File`
- **Similarity:** 0.46
- **Dependents:** 0
- **Priority Score:** 20505.4
- **Functions:** 2/4 matched (target 7)
- **Missing functions:** `drop`, `random_part`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/3 matched

### 7. ext.iter

- **Target:** `ext.IterExt [PROVENANCE-FALLBACK]`
- **Similarity:** 0.36
- **Dependents:** 0
- **Priority Score:** 11306.4
- **Functions:** 9/9 matched (target 20)
- **Missing functions:** _none_
- **Types:** 3/4 matched
- **Missing types:** `Item`
- **Provenance warning:** port-lint provenance header matched only by basename: `dupe/src/iter.rs` vs expected `ext/iter.rs`
- **Proposed provenance header:** `// port-lint: source ext/iter.rs` (current: `// port-lint: source dupe/src/iter.rs`)
- **Lint issues:** 1

### 8. ext.option

- **Target:** `ext.OptionExt [PROVENANCE-FALLBACK]`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 10308.0
- **Functions:** 1/1 matched (target 3)
- **Missing functions:** _none_
- **Types:** 1/2 matched (target 3)
- **Missing types:** `Item`
- **Provenance warning:** port-lint provenance header matched only by basename: `dupe/src/option.rs` vs expected `ext/option.rs`
- **Proposed provenance header:** `// port-lint: source ext/option.rs` (current: `// port-lint: source dupe/src/option.rs`)
- **Lint issues:** 1

### 9. phantom

- **Target:** `gazebo.Phantom`
- **Similarity:** 0.56
- **Dependents:** 0
- **Priority Score:** 904.4
- **Functions:** 8/8 matched (target 12)
- **Missing functions:** _none_
- **Types:** 1/1 matched
- **Missing types:** _none_

### 10. variants

- **Target:** `gazebo.Variants`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 507.4
- **Functions:** 3/3 matched (target 17)
- **Missing functions:** _none_
- **Types:** 2/2 matched (target 8)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 11. types

- **Target:** `gazebo.Types`
- **Similarity:** 0.91
- **Dependents:** 0
- **Priority Score:** 400.9
- **Functions:** 3/3 matched (target 6)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

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
| `ext` | `ext.Ext` | 0 | `ext.rs` | `ext/Ext.kt` |
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |
| `prelude` | `Prelude` | 0 | `prelude.rs` | `Prelude.kt` |
| `test` | `Test` | 0 | `test.rs` | `Test.kt` |

