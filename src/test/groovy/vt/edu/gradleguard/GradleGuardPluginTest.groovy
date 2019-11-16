package vt.edu.gradleguard
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GradleGuardPluginTest {

    @Test
    public void testThePlugin(){
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'vt.edu.gradleguard.plugin'
        Assertions.assertNotNull(project.tasks.previewFiles)
    }
}
