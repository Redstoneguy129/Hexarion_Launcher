package fr.arinonia.hexarion.launcher.utils;

import java.io.File;

public enum OperatingSystem {
    LINUX("linux", new String[]{"linux", "unix"}),
    WINDOWS("windows", new String[]{"win"}),
    MACOS("osx", new String[]{"mac"}),
    UNKNOWN("unknown", new String[0]);

    private final String name;
    private final String[] aliases;

    OperatingSystem(final String name, final String[] aliases) {
        this.name = name;
        this.aliases = ((aliases == null) ? new String[0] : aliases);
    }

    public static OperatingSystem getCurrentPlatform() {
        final String osName = System.getProperty("os.name").toLowerCase();
        for (final OperatingSystem os : values()) {
            for (final String alias : os.getAliases()) {
                if (osName.contains(alias)) {
                    return os;
                }
            }
        }
        return OperatingSystem.UNKNOWN;
    }

    public String getName() {
        return this.name;
    }

    private String[] getAliases() {
        return this.aliases;
    }

    public boolean isSupported() {
        return this == OperatingSystem.UNKNOWN;
    }

    public String getJavaDir() {
        final String separator = System.getProperty("file.separator");
        if (getCurrentPlatform() == OperatingSystem.MACOS){
            return System.getProperty("java.home");
        }
        final String path = System.getProperty("java.home") + separator + "bin" + separator;
        if (getCurrentPlatform() == OperatingSystem.WINDOWS && new File(path + "javaw.exe").isFile()) {
            return path + "javaw.exe";
        }
        return path + "java";
    }
}