package vt.edu.gradleguard.core;

import java.io.File;
import java.util.Optional;

public class Utils {
    public static final String group = "gradleguard";
    public static final String projectVersion = "V00.00.12";
    public static final String cmdSplit = repeat(80, "=") + "\n";

    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

}

