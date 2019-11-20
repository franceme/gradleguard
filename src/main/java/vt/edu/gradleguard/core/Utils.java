package vt.edu.gradleguard.core;

/**
 * <p>Utils class.</p>
 * The java file routing and handling the Entrypoint hand off.
 *
 * @author franceme
 * @version 00.00.12
 * @since V00.01.01
 */
public class Utils {
    /** Constant <code>group="gradleguard"</code> */
    public static final String group = "gradleguard";
    /** Constant <code>projectVersion="V00.01.01"</code> */
    public static final String projectVersion = "V00.01.01";
    /** Constant <code>cmdSplit="repeat(80, =) + \n"</code> */
    public static final String cmdSplit = repeat(80, "=") + "\n";

    /**
     * <p>repeat.</p>
     *
     * A helper method to "repeat" a string count amount of times.
     * aka: repeat(10, "*") = **********
     *
     * @param count a int object - .
     * @param with a {@link java.lang.String} object - .
     * @return a {@link java.lang.String} object - .
     */
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

}

