package vt.edu.gradleguard.core;

import frontEnd.Interface.EntryPoint_Plugin;
import frontEnd.Interface.outputRouting.ExceptionHandler;
import frontEnd.MessagingSystem.AnalysisIssue;
import frontEnd.MessagingSystem.routing.outputStructures.common.Heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;


public class Base {

    public static String entryPoint(ArrayList<String> sources, ArrayList<String> dependencies, String fileOut, String mainFile) throws ExceptionHandler {

        Function<AnalysisIssue, String> errorAddition = analysisIssue -> "Adding the issue: " + analysisIssue.toString();
        Function<HashMap<Integer, Integer>, String> bugSummaryHandler = bugSummary -> {
            StringBuilder out = new StringBuilder();
            for (Integer key : bugSummary.keySet())
                out.append("Rule ").append(key).append(" has ").append(bugSummary.get(key)).append(" incidents.");
            return out.toString();
        };
        Function<Heuristics, String> heuristicsHandler = heuristics -> "Current Heuristics: " + heuristics.toString();

        return EntryPoint_Plugin.main(sources, dependencies, fileOut, mainFile, errorAddition, bugSummaryHandler, heuristicsHandler, 3);
    }

}
