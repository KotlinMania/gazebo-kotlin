=== Deep Analysis: tmp/gazebo (rust) -> src/commonMain/kotlin (kotlin) ===

Scanning source codebase (rust)...
Codebase: tmp/gazebo (rust)
  Files: 3
  Total imports: 13

Scanning target codebase (kotlin)...
Codebase: src/commonMain/kotlin (kotlin)
  Files: 5
  Total imports: 8

Comparing codebases...
Computing AST similarities...

=== Codebase Comparison Report ===

Source: tmp/gazebo (3 files)
Target: src/commonMain/kotlin (5 files)
Scoring invariant: FnSim is required function body/parameter similarity. Class/type and symbol parity are reported beside it; whole-file shape is diagnostic only.

Matched:   3 files
Unmatched: 0 source, 0 target

=== Matched Files (by porting priority) ===

Source                        Target                        FnSim     Dependents FunctionParityTypeParity  Priority
 --------------------------------------------------------------------------------------------------------------------------
cmp_any.eq                    cmpany.PartialEqAny [PROVENANCE-FALLBACK]0.63      0          6/7           3/3         11003.7   
cmp_any.ord                   cmpany.OrdAny [PROVENANCE-FALLBACK]0.71      0          6/6           1/1         702.9     
cmp_any.lib                   cmpany.Lib [PROVENANCE-FALLBACK]1.00      0          0/0           0/0         0.0       

=== Function and Symbol Details ===

cmp_any.eq -> cmpany.PartialEqAny [PROVENANCE-FALLBACK]
  similarity: 0.63, priority: 11003.7, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `eq.rs` vs expected `eq.rs`
  provenance warning: port-lint provenance header matched only after fallback normalization: `tests:eq.rs` vs expected `eq.rs`
  functions: 6/7 matched (target total: 12, required body score: 0.63)
  missing functions: token
  types: 3/3 matched (target total: 4)
  missing types: none
  tests: 2/3 matched

cmp_any.ord -> cmpany.OrdAny [PROVENANCE-FALLBACK]
  similarity: 0.71, priority: 702.9, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `ord.rs` vs expected `ord.rs`
  provenance warning: port-lint provenance header matched only after fallback normalization: `tests:ord.rs` vs expected `ord.rs`
  functions: 6/6 matched (target total: 9, required body score: 0.71)
  missing functions: none
  types: 1/1 matched (target total: 2)
  missing types: none
  tests: 1/1 matched

cmp_any.lib -> cmpany.Lib [PROVENANCE-FALLBACK]
  similarity: 1.00, priority: 0.0, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
  functions: 0/0 matched (target total: 0, required body score: 1.00)
  missing functions: none
  types: 0/0 matched (target total: 1)
  missing types: none


=== Provenance Header Fallbacks ===

These files were paired only after normalization; fix the port-lint source header.
  - cmp_any.eq -> cmpany.PartialEqAny: port-lint provenance header matched only after fallback normalization: `eq.rs` vs expected `eq.rs`
    proposed: // port-lint: source eq.rs
  - cmp_any.eq -> cmpany.PartialEqAny: port-lint provenance header matched only after fallback normalization: `tests:eq.rs` vs expected `eq.rs`
    proposed: // port-lint: tests eq.rs
  - cmp_any.ord -> cmpany.OrdAny: port-lint provenance header matched only after fallback normalization: `ord.rs` vs expected `ord.rs`
    proposed: // port-lint: source ord.rs
  - cmp_any.ord -> cmpany.OrdAny: port-lint provenance header matched only after fallback normalization: `tests:ord.rs` vs expected `ord.rs`
    proposed: // port-lint: tests ord.rs
  - cmp_any.lib -> cmpany.Lib: port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
    proposed: // port-lint: source lib.rs

=== Porting Quality Summary ===

Matched by exact header:          0 / 3
Matched by provenance fallback:   3 / 3
Matched by name:                  0 / 3
Total TODOs in target: 0
Total lint errors:    5
Stub files:           0

=== Big Picture ===

- Missing files: 0
- Incomplete ports (similarity < 60%): 0
- Stub files: 0
- Files missing functions: 1 (total deficit: 1 functions)
- Type definitions missing: 0
- Files missing tests: 1 (total deficit: 1 unported `#[test]` functions)
- Documentation coverage: 21 / 20 lines (105%)

Primary focus: port missing functions/tests to reach per-file parity (1 functions, 1 tests)

=== Files with Issues ===

File                          Similarity LineRatio  FunctionParityTests     TODOs Lint  Status
----------------------------------------------------------------------------------------------------
cmpany.PartialEqAny [PROVENA  0.63       0.00       6/7           2/3       0     2     MISSING_FUNCS
  missing functions: `token`
cmpany.OrdAny [PROVENANCE-FA  0.71       0.00       6/6           1/1       0     2     LINT
cmpany.Lib [PROVENANCE-FALLB  1.00       0.00       -             -         0     1     LINT

=== Porting Recommendations ===

Incomplete ports (similarity < 60%): 0
Missing files: 0


=== Documentation Gaps ===

Documentation coverage: 21 / 20 lines (105%)
Files with >20% doc gap: 0

No significant documentation gaps found.

=== Generating Reports ===

✅ Generated: port_status_report.md
✅ Generated: high_priority_ports.md
✅ Generated: NEXT_ACTIONS.md
✅ Generated: port_lint_proposed_changes.md

📁 All reports generated successfully!
