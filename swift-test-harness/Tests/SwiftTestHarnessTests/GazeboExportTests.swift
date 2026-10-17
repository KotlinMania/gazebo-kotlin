import Testing
import Gazebo

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
@Suite("Gazebo Swift Export Smoke Tests")
struct GazeboExportTests {
    @Test("Gazebo swift module imported cleanly and exports primary types")
    func testSwiftModuleLoads() {
        let formatter = displaycontainer.DisplayFormatter(alternate: false)
        _ = formatter.writeStr(s: "hello")
        #expect(formatter.toString() == "hello")
        #expect(dupe.dupe(Int32(42)) == Int32(42))
    }
}
