package vt.edu.gradleguard

import groovy.io.FileType
import org.gradle.api.Plugin
import org.gradle.api.Project
import vt.edu.gradleguard.core.Base
import vt.edu.gradleguard.core.Utils

/**
 * The main class containing all of the tasks for the plugin.
 * This class handles all of the arguments from the plugin and passes them off into the Cryptoguard wrapper.
 *
 * <ul>
 *     <li>version - Display the version for Gradleguard and Cryptoguard being used</li>
 *     <li>previewFiles - Display all of the files automatically pulled from </li>
 *     <li>scanFiles - Scans the files pulled via the file retrieval</li>
 * </ul>
 *
 * @author franceme
 * @version 00.00.12
 * @since V00.00.12
 */
class GradleGuardPlugin implements Plugin<Project> {

    //region Helper Class
    /**
     * <p>depKlass</p>
     *
     * A subclass to help handle the wrapping for retrieving the dependencies.
     */
    class depKlass {

        //region Attributes
        String group = '';
        String name = '';
        String version = '';
        File file = null;
        //endregion

        //region Constructors
        /**
         * The constructor for the dependency sub class
         *
         * @param group a {@link java.lang.String} object - The group of the dependency
         * @param name a {@link java.lang.String} object - The name of the dependency
         * @param version a {@link java.lang.String} object - The version of the dependency
         */
        depKlass(String group, String name, String version) {
            this.group = group;
            this.name = name;
            this.version = version;
        }
        //endregion

        //region Overridden Methods
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
        //endregion

        //region region Helper Methods
        /**
         * This is a helper method to determine the generated path (by gradle) to get the absolute path of the jar.
         *
         * @return a {@link java.lang.String} object - The sub path of the dependency
         */
        public String subPath() {
            return String.join(System.getProperty('file.separator'), this.group, this.name, this.version)
        }

        /**
         * A helper method to determine the live name of the jar for the dependency.
         *
         * @return a {@link java.lang.String} object -
         */
        public String fileName() {
            return this.name + '-' + this.version + '.jar'
        }
        //endregion
    }
    //endregion
    //region Apply
    @Override
    void apply(Project project) {

        //region Retrieve Version
        project.task('version') {

            //Group is the task groups, on the side, ()
            group = Utils.group

            doLast {
                println Utils.cmdSplit
                println "Gradleguard Version: " + Utils.projectVersion
                println "Cryptoguard Version: " + util.Utils.projectVersion
                println Utils.cmdSplit
            }

        }

        

        //endregion
        //region Preview Files
        //task - the name of the task
        project.task('previewFiles') {

            //Group is the task groups, on the side, ()
            group = Utils.group

            //The code itself
            doLast {

                ArrayList<File> sourceFiles = new ArrayList<>()
                ArrayList<depKlass> dependencies = new ArrayList<>()

                new File(project.projectDir.toString(), 'build/classes/').eachFileRecurse(FileType.FILES) { file ->
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
                        println(file.getName())
                }

                println "Found the following dependencies"
                dependencies.each {
                    dep ->
                        println(dep.file.getName())
                }

                def File outputFile = new File(project.buildDir, "tmp/_cryptoguard.json")
                if (outputFile.exists()) {
                    outputFile.delete();
                    println "Using the output file at: " + outputFile.getAbsolutePath()
                }
            }
        }

        project.task('flag') {
        if (project.hasProperty("-o"))
        {
            // args += '-o'
            String var =  project.getProperty('-o')
            println "-o " + var
        }
        if (project.hasProperty("-new"))
        {
            // args += '-new'
            String var =  project.getProperty('-new')
            println "-new " + var
        }
        if (project.hasProperty("-t"))
        {
            // args += '-t'
            String var =  project.getProperty('-t')
            println "-t " + var
        }
        if (project.hasProperty("-m"))
        {
            // args += '-m'
            String var =  project.getProperty('-m')
            println "-m " + var
        }
        if (project.hasProperty("-n"))
        {
            // args += '-n'
            String var =  project.getProperty('-n')
            println "-n " + var
        }
        if (project.hasProperty("-exp"))
        {
            // args += '-exp'
            String var =  project.getProperty('-exp')
            println "-exp " + var
        }
        if (project.hasProperty("-V"))
        {
            // args += '-V'
            String var =  project.getProperty('-V')
            println "-V " + var
        }
        if (project.hasProperty("-vx"))
        {
            // args += '-vx'
            String var =  project.getProperty('-vx')
            println "-vx " + var
        }
        if (project.hasProperty("-v"))
        {
            // args += '-v'
            String var =  project.getProperty('-v')
            println "-v " + var
        }
        if (project.hasProperty("-vv"))
        {
            // args += '-vv'
            String var =  project.getProperty('-vv')
            println "-vv " + var
        }
        if (project.hasProperty("-ts"))
        {
            // args += '-ts'
            String var =  project.getProperty('-ts')
            println "-ts " + var
        }
        if (project.hasProperty("-depth"))
        {
            // args += '-depth'
            String var =  project.getProperty('-depth')
            println "-depth " + var
        }
        if (project.hasProperty("-L"))
        {
            // args += '-L'
            String var =  project.getProperty('-L')
            println "-L " + var
        }
        if (project.hasProperty("-java"))
        {
            // args += '-java'
            String var =  project.getProperty('-java')
            println "-java " + var
        }
        if (project.hasProperty("-android"))
        {
            // args += '-android'
            String var =  project.getProperty('-android')
            println "-android " + var
        }
        if (project.hasProperty("-H"))
        {
            // args += '-H'
            String var =  project.getProperty('-H')
            println "-H " + var
        }
        if (project.hasProperty("-st"))
        {
            // args += '-st'
            String var =  project.getProperty('-st')
            println "-st " + var
        }
        if (project.hasProperty("-h"))
        {
            // args += '-h'
            String var =  project.getProperty('-h')
            println "-h " + var
        }
        if (project.hasProperty("-main"))
        {
            // args += '-main'
            String var =  project.getProperty('-main')
            println "-main " + var
        }
    }

        //endregion
        //region Scan Files
        //task - the name of the task
        project.task('scanFiles') {

            //Group is the task groups, on the side, ()
            group = Utils.group

            //The code itself
            doLast {

                ArrayList<String> sourceFiles = new ArrayList<>()
                ArrayList<String> dependencies = new ArrayList<>()

                new File(project.projectDir.toString(), 'build/classes/').eachFileRecurse(FileType.FILES) { file ->
                    sourceFiles.add(file.getAbsolutePath())
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

                            dependencies.add(curKlass.getFile().getAbsolutePath());
                        }
                    }
                }

                def File outputFile = new File(project.buildDir, "tmp/_cryptoguard.json")
                if (outputFile.exists())
                    outputFile.delete()

                try {
                    def String result = Base.entryPoint(sourceFiles, dependencies, outputFile.getAbsolutePath(), null,2)
                } catch (Exception e) {
                    println "Error"
                    e.printStackTrace()
                }
            }
        }
        //endregion
    }
    //endregion
}
