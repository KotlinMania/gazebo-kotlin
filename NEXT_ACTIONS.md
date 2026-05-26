# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 3/3 (100.0%)
- **Function parity:** 8/13 matched (target 16) — 61.5%
- **Class/type parity:** 4/4 matched (target 6) — 100.0%
- **Combined symbol parity:** 12/17 matched (target 22) — 70.6%
- **Average inline-code cosine:** 0.46 (function body across 2 matched files)
- **Average documentation cosine:** 0.82 (doc text across 2 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 3 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. ord

- **Target:** `cmpany.OrdAny`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 30706.3
- **Functions:** 3/6 matched
- **Missing functions:** `eq`, `partial_cmp`, `cmp`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. eq

- **Target:** `cmpany.PartialEqAny`
- **Similarity:** 0.55
- **Dependents:** 0
- **Priority Score:** 21004.5
- **Functions:** 5/7 matched (target 10)
- **Missing functions:** `eq`, `token`
- **Types:** 3/3 matched (target 4)
- **Missing types:** _none_
- **Tests:** 2/3 matched

### 3. lib

- **Target:** `cmpany.Lib [STUB]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Next Commands

```bash
# Initialize task queue for systematic porting
cd tools/ast_distance
./ast_distance --init-tasks ../../tmp/gazebo/cmp_any/src rust ../../src/commonMain/kotlin/io/github/kotlinmania/gazebo/cmpany kotlin tasks.json ../../AGENTS.md

# Get next high-priority task
./ast_distance --assign tasks.json <agent-id>
```
