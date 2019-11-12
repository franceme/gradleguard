package vt.edu.gradleguard
import org.gradle.api.Plugin
import org.gradle.api.Project
import vt.edu.gradleguard.core.Base
import vt.edu.gradleguard.core.Task
import vt.edu.gradleguard.core.Utils

class GradleGuardPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {

        //task - the name of the task
        project.task(Task.retrieveTask('testTask').taskName) {

            //Group is the task groups, on the side, ()
            group = Utils.group

            //The code itself
            doLast {
                println "Java Class Entry:  " + Base.val
            }
        }
    }
}