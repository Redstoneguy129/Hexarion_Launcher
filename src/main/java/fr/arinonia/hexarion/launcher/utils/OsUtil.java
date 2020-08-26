package fr.arinonia.hexarion.launcher.utils;

/**
 * Created by Arinonia on 24/08/2020 inside the package - fr.arinonia.hexarion.launcher.utils
 */
public class OsUtil {
    public static EnumOS getOs(){
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? EnumOS.WINDOWS
                : (os.contains("mac") ? EnumOS.MAC
                : (os.contains("linux") ? EnumOS.LINUX
                : (os.contains("unix") ? EnumOS.MAC : EnumOS.UNKNOWN)));
    }

    public enum EnumOS{
        LINUX,
        WINDOWS,
        MAC,
        UNKNOWN
    }
}
