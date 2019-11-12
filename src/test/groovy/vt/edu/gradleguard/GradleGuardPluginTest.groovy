package vt.edu.gradleguard
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import vt.edu.gradleguard.core.Task

class GradleGuardPluginTest {

    @Test
    public void testThePlugin(){
        // ~ make a gradle project
        Project project = ProjectBuilder.builder().build()

        // ~ Importing the plugin
        project.pluginManager.apply 'vt.edu.gradleguard'

        // ~ Running the test
        Assertions.assertNotNull(Task.retrieveTask('testTask').taskName)
    }
}
