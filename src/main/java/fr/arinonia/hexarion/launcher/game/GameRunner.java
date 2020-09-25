package fr.arinonia.hexarion.launcher.game;

import fr.arinonia.hexarion.launcher.Launcher;
import fr.arinonia.hexarion.launcher.utils.OperatingSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Arinonia on 24/08/2020 inside the package - fr.arinonia.hexarion.launcher.game.file
 */
public class GameRunner {
    private final Launcher launcher;

    public GameRunner(Launcher launcher) {
        this.launcher = launcher;
    }

    public Process launch() {
        List<String> arguments = new ArrayList<>();
        StringBuilder sBuilder = new StringBuilder();

        if (OperatingSystem.getCurrentPlatform() != OperatingSystem.MACOS)
            sBuilder.append(this.launcher.getFileManager().getRuntime());
        else
            sBuilder.append(OperatingSystem.getCurrentPlatform().getJavaDir());

        sBuilder.append(File.separator);
        sBuilder.append("bin");
        sBuilder.append(File.separator);
        sBuilder.append("java");
        if (OperatingSystem.getCurrentPlatform() == OperatingSystem.WINDOWS){
            sBuilder.append("w.exe");
        }
        arguments.add(sBuilder.toString());

        arguments.add("-Xms1G");
        arguments.add(this.launcher.getFileManager().getRamFile().exists() ? "-Xmx" + this.launcher.getRamManager().getRam() + "G" : "-Xmx1024M");
        arguments.add("-XX:MetaspaceSize=256M");

        arguments.add("-Dfml.ignorePatchDiscrepancies=true");
        arguments.add("-Dfml.ignoreInvalidMinecraftCertificates=true");

        arguments.add("-Djava.library.path=" + this.launcher.getFileManager().getNatives().getAbsolutePath());

        arguments.add("-cp");

        StringBuilder libs = new StringBuilder();
        for (File files : this.launcher.getFileManager().getLibs().listFiles()) {
            libs.append(files.getAbsolutePath());
            libs.append(System.getProperty("path.separator"));
        }
        libs.append(System.getProperty("path.separator"));
        libs.append(new File(this.launcher.getFileManager().getGame(), "minecraft.jar").getAbsolutePath());

        arguments.add(libs.toString());

        arguments.add("net.minecraft.client.main.Main");

        arguments.add("--username=" + this.launcher.getProfile().getDisplayName());
        arguments.add("--accessToken=" + this.launcher.getProfile().getAccessToken());
        arguments.add("--uuid=" + this.launcher.getProfile().getUuid());
        arguments.add("--version=1.7.10");
        arguments.add("--gameDir=" + this.launcher.getFileManager().getGame().getAbsolutePath());
        arguments.add("--assetsDir=" + this.launcher.getFileManager().getAssets().getAbsolutePath());
        arguments.add("--assetIndex=1.7.10");
        arguments.add("--userProperties={}");
        arguments.add("--userType=legacy");

        System.out.println("arguments: " + arguments.toString());

        ProcessBuilder builder = new ProcessBuilder(arguments);
        builder.directory(this.launcher.getFileManager().getGame());
        builder.redirectErrorStream(true);
        builder.environment().remove("_JAVA_OPTIONS");

        try {
            final Process p = builder.start();
            this.launcher.getParent().setVisible(false);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null) sb.append(line + "\n");

            System.out.println(sb.toString());
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
