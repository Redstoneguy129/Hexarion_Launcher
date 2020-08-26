package fr.arinonia.hexarion.launcher.game.file;

import fr.arinonia.hexarion.launcher.Launcher;

import java.io.*;

/**
 * Created by Arinonia on 26/08/2020 inside the package - fr.arinonia.hexarion.launcher.game.file
 */
public class RamManager {

    private final Launcher launcher;

    public RamManager(Launcher launcher) {
        this.launcher = launcher;
    }

    public String getRam() {
        int ram = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.launcher.getFileManager().getRamFile()));
            String line;
            while ((line = br.readLine()) != null) {
                ram = Integer.parseInt(line.substring(0, line.length() - 2));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(ram);
    }


    public void setRam(String val) {
        File ramfile = this.launcher.getFileManager().getRamFile();
        if (!ramfile.exists()) {
            try {
                ramfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter rama = new FileWriter(this.launcher.getFileManager().getRamFile());
            rama.write(val);
            rama.close();
        } catch (Exception exception) {}
    }
}
