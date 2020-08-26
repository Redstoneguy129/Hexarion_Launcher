package fr.arinonia.hexarion.launcher;

import fr.arinonia.hexarion.launcher.utils.OperatingSystem;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.utils
 */
public class Constants {

    public static final String LAUNCHER_NAME = "Hexarion launcher";

    public static final String AUTH_URL = "https://hexarion.fr";

    public static final String DOWNLOAD_JAVA_URL = "http://dev.valkyria.fr/download/java/java_" + OperatingSystem.getCurrentPlatform().getName().toLowerCase() + ".json";
    public static final String DOWNLOAD_GAME_URL = "http://hexarion.chaun14.fr/launcher/instance.json";
}
