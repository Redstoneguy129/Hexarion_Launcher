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
        /*List<String> arguments = new ArrayList<>();

        arguments.add(OperatingSystem.getCurrentPlatform().getJavaDir());

        String javaArguments = "";//TODO CONFIG

        if (javaArguments.isEmpty()){
            javaArguments = "-XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy";
        }

        arguments.add("-Xmx1G");
        //arguments.add("-Xms1024M");

        arguments.add("-XX:MetaspaceSize=256M");

        arguments.add("-Dfml.ignorePatchDiscrepancies=true");
        arguments.add("-Dfml.ignoreInvalidMinecraftCertificates=true");

        arguments.add("-Djava.library.path=" + this.launcher.getFileManager().getNatives().getAbsolutePath());

        //arguments.add("-Dfml.log.level=");

        if (OsUtil.getOs() == OsUtil.EnumOS.MAC){
            arguments.add("-Dapple.laf.useScreenMenuBar=true");
            arguments.add("-Xdock:icon=" + new File(this.launcher.getFileManager().getAssets(), "icons/minecraft.icns").getAbsolutePath());
            arguments.add("-Xdock:name=\"" + "Hexarion" + "\"");
        }

        if (!javaArguments.isEmpty()){
            for (String arg : javaArguments.split(" ")){
                if (!arg.isEmpty()){
                    if (arguments.toString().contains(arg)){
                        System.err.println(arg + " is duplicate");
                        continue;
                    }
                    arguments.add(arg);
                }
            }
        }

        StringBuilder libs = new StringBuilder();
        for (File files : this.launcher.getFileManager().getLibs().listFiles()) {
            libs.append(System.getProperty("path.separator"));
            libs.append(files.getAbsolutePath());
        }
        libs.append(System.getProperty("path.separator"));
        libs.append(new File(this.launcher.getFileManager().getGame(), "minecraft.jar").getAbsolutePath());

        arguments.add("-cp");
        arguments.add(libs.toString());

        arguments.add("net.minecraft.client.main.Main");

        arguments.add("--username=" + this.launcher.getProfile().getDisplayName());
        arguments.add("--accessToken=" + this.launcher.getProfile().getAccessToken());
        arguments.add("--uuid=" + this.launcher.getProfile().getUuid());
        arguments.add("--version=1.7.10");
        arguments.add("--gameDir=" + this.launcher.getFileManager().getGame().getAbsolutePath());
        arguments.add("--assetsDir=" + this.launcher.getFileManager().getAssets().getAbsolutePath());
        arguments.add("--assetsIndex=1.7.10");
        arguments.add("--userProperties={}");
        arguments.add("--userType=legacy");

        for (String args : arguments){
            System.out.println("args: " + args);
        }

        String argsString = arguments.toString();

        System.out.println("Launching Minecraft with the following arguments: " + " " + argsString);

        ProcessBuilder builder = new ProcessBuilder(arguments);
        builder.directory(this.launcher.getFileManager().getGame());
        builder.redirectErrorStream(true);
        //builder.environment().remove("_JAVA_OPTIONS");
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        List<String> arguments = new ArrayList<>();

        arguments.add(OperatingSystem.getCurrentPlatform().getJavaDir());

        arguments.add("-Xms1G");
        arguments.add(this.launcher.getFileManager().getRamFile().exists() ? "-Xmx" + this.launcher.getRamManager().getRam() + "G" : "-Xmx1024M");
//"-Xms1024M"
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

        /*String args = "C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/mojang/netty/1.6/netty-1.6.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/mojang/realms/1.3.5/realms-1.3.5.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/java3d/vecmath/1.3.1/vecmath-1.3.1.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/net/sf/trove4j/trove4j/3.0.3/trove4j-3.0.3.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/net/sf/jopt-simple/jopt-simple/4.5/jopt-simple-4.5.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/io/netty/netty-all/4.0.10.Final/netty-all-4.0.10.Final.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/google/guava/guava/15.0/guava-15.0.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/commons-io/commons-io/2.4/commons-io-2.4.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/commons-codec/commons-codec/1.9/commons-codec-1.9.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/com/mojang/authlib/1.5.21/authlib-1.5.21.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/logging/log4j/log4j-api/2.0-beta9/log4j-api-2.0-beta9.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/apache/logging/log4j/log4j-core/2.0-beta9/log4j-core-2.0-beta9.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.1/lwjgl_util-2.9.1.jar;C:/Users/arino/AppData/Roaming/.minecraft/libraries/tv/twitch/twitch/5.16/twitch-5.16.jar;C:/Users/arino/AppData/Roaming/.Hexarion/minecraft.jar";
        args = args.replace('/', File.separatorChar);
        arguments.add(args);*/

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
            final Process p=builder.start();
            this.launcher.getParent().setVisible(false);
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null) sb.append(line);

            System.out.println(sb.toString());
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
