package vt.edu.gradleguard

//import frontEnd.MessagingSystem.routing.outputStructures.OutputStructure
import groovy.io.FileType
import org.gradle.api.Plugin
import org.gradle.api.Project
import vt.edu.gradleguard.core.Base
import vt.edu.gradleguard.core.Utils

import java.util.stream.Collectors

class GradleGuardPlugin implements Plugin<Project> {

    //region Helper Class
    class depKlass {
        String group = '';
        String name = '';
        String version = '';
        File file = null;

        depKlass(String group, String name, String version) {
            this.group = group;
            this.name = name;
            this.version = version;
        }

        public String subPath() {
            return String.join(System.getProperty('file.separator'), this.group, this.name, this.version)
        }

        public String fileName() {
            return this.name + '-' + this.version + '.jar'
        }

        @Override
        public boolean equals(Object obj) {
            if (!obj instanceof depKlass)
                return false;
            else
                return this.group.equals(obj.group) && this.name.equals(obj.name) && this.version.equals(obj.version);
        }

        @Override
        public String toString() {
            def output = 'group: ' + this.group + ' name: ' + this.name + ' version: ' + this.version
            if (this.file != null)
                output += ' file: ' + this.file.getAbsolutePath()
            return output
        }
    }
    //endregion
    //region Apply
    @Override
    void apply(Project project) {

        //task - the name of the task
        project.task('previewFiles') {

            //Group is the task groups, on the side, ()
            group = Utils.group

            //The code itself
            doLast {
                
                ArrayList<File> sourceFiles = new ArrayList<>()
                ArrayList<depKlass> dependencies = new ArrayList<>()

                new File(project.projectDir.toString(), 'tempBuild/classes/').eachFileRecurse(FileType.FILES) { file ->
                    sourceFiles.add(file)
                }

                //Cycling through all of the configurations to pull out all of the dependencies
                project.configurations.each { conf ->
                    conf.allDependencies.each { dep ->
                        def curKlass = new depKlass(dep.group, dep.name, dep.version)

                        //If the arraylist doesn't already contain the dependency
                        if (!dependencies.stream().any { klass -> klass.equals(curKlass) }) {

                            //Looping through all of the files in the path, pulling out the pom
                            //Needed since the path structure is ${group}/${name}/${version}/sha1/${name}-${version}.jar
                            new File(System.getProperty('user.home'), '.gradle/caches/modules-2/files-2.1/' + curKlass.subPath()).eachFileRecurse(FileType.FILES) { file ->
                                if (curKlass.fileName().equals(file.name))
                                    curKlass.setFile(file)
                            }

                            dependencies.add(curKlass);
                        }
                    }
                }

                println "Found the following source files"
                sourceFiles.each {
                    file ->
                        println(file.getAbsolutePath())
                }

                println "Found the following dependencies"
                dependencies.each {
                    dep ->
                        println(dep.file.getAbsolutePath())
                }

                def File outputFile = new File(project.buildDir, "tmp/_cryptoguard.json")
                if (outputFile.exists())
                {
                    outputFile.delete();
                    println "Using the output file at: " + outputFile.getAbsolutePath()
                }
                
                //def OutputStructure struct = Base.entryPoint(
                //        sourceFiles.stream().map(foil->foil.getAbsolutePath()).collect(Collectors.toCollection(ArrayList::String)),
                //        dependencies.stream().map(klass->klass.file.getAbsolutePath().toString()).collect(Collectors.toCollection(ArrayList::new)),
                //        null,
                //        null
                //)
                
            }
        }
    }
    //endregion
}