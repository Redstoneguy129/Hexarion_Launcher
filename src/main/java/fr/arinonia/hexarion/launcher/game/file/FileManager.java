package fr.arinonia.hexarion.launcher.game.file;

import java.io.File;

/**
 * Created by Arinonia on 24/08/2020 inside the package - fr.arinonia.hexarion.launcher.game.file
 */
public class FileManager {
    private String serverName;

    public FileManager(String serverName) {
        this.serverName = serverName;
    }

    public File createGameDir() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\." + this.serverName);
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home") + "/Library/Application Support/" + this.serverName);
        else
            return new File(System.getProperty("user.home") + "/." + this.serverName);
    }

    public File getAssets() {
        return new File(createGameDir(), "assets");
    }

    public File getNatives() {
        return new File(createGameDir(), "natives");
    }

    public File getLibs() {
        return new File(createGameDir(), "libs");
    }

    public File getGame() {
        return createGameDir();
    }

    public File getRuntime(){
        return new File(createGameDir(), "runtime");
    }

    public File getRamFile(){
        return new File(createGameDir(), "ram.properties");
    }
    public String getServerName() {
        return serverName;
    }
}
