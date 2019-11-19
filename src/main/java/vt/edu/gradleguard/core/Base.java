package vt.edu.gradleguard.core;

import frontEnd.Interface.EntryPoint_Plugin;
import frontEnd.Interface.outputRouting.ExceptionHandler;
import frontEnd.MessagingSystem.AnalysisIssue;
import frontEnd.MessagingSystem.routing.outputStructures.common.Heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;


/**
 * The java file routing and handling the Entrypoint hand off.
 *
 * @author franceme
 * @version 00.00.12
 * @since V00.00.12
 */
public class Base {

    /**
     * <p>entryPoint.</p>
     *
     * This method hands off all of the necessary information into the Entrypoint Plugin from Cryptoguard.
     *
     * @param sources a {@link java.util.ArrayList} object - The source files (absolute paths).
     * @param dependencies a {@link java.util.ArrayList} object - The dependency files (absolute paths).
     * @param fileOut a {@link java.lang.String} object - The cryptoguard output file to be written to.
     * @param mainFile a {@link java.lang.String} object - Specifying the main class file.
     * @param debuggingLevel a int - The debugging level, used for the logger level.
     * @return a {@link java.lang.String} object - The file path to the output file or the error message.
     */
    public static String entryPoint(ArrayList<String> sources, ArrayList<String> dependencies, String fileOut, String mainFile, int debuggingLevel) throws ExceptionHandler {

        Function<AnalysisIssue, String> errorAddition = analysisIssue -> "Adding the issue: " + analysisIssue.toString();
        Function<HashMap<Integer, Integer>, String> bugSummaryHandler = bugSummary -> {
            StringBuilder out = new StringBuilder();
            for (Integer key : bugSummary.keySet())
                out.append("Rule ").append(key).append(" has ").append(bugSummary.get(key)).append(" incidents.");
            return out.toString();
        };
        Function<Heuristics, String> heuristicsHandler = heuristics -> "Current Heuristics: " + heuristics.toString();


        if (debuggingLevel > 0)
            System.out.println(Utils.cmdSplit);

        String fileResult = EntryPoint_Plugin.main(sources, dependencies, fileOut, mainFile, errorAddition, bugSummaryHandler, heuristicsHandler, debuggingLevel);
        System.out.println("Output file can be found at " + fileResult);

        if (debuggingLevel > 0)
            System.out.println(Utils.cmdSplit);

        return fileOut;
    }

}
