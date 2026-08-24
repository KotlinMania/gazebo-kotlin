import Testing
import Gazebo

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
@Suite("Gazebo Swift Export Smoke Tests")
struct GazeboExportTests {
    @Test("Gazebo swift module imported cleanly")
    func testSwiftModuleLoads() {
        #expect(Bool(true))
    }
}
