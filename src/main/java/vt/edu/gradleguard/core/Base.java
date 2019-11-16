package vt.edu.gradleguard.core;

import frontEnd.Interface.EntryPoint_Plugin;
import frontEnd.Interface.outputRouting.ExceptionHandler;
import frontEnd.MessagingSystem.routing.EnvironmentInformation;
import frontEnd.MessagingSystem.routing.outputStructures.OutputStructure;

import java.util.ArrayList;

public class Base {

    public static OutputStructure entryPoint(ArrayList<String> sources, ArrayList<String> dependencies, String fileOut, String mainFile) throws ExceptionHandler {
        EnvironmentInformation singleton = null;

        EntryPoint_Plugin.main(sources, dependencies, fileOut, mainFile, singleton);

        return singleton.getOutput();
    }

}
