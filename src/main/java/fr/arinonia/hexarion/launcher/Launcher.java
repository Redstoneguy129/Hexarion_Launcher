package fr.arinonia.hexarion.launcher;

import com.azuriom.azauth.AzAuthenticator;
import com.google.gson.Gson;
import fr.arinonia.hexarion.launcher.game.GameRunner;
import fr.arinonia.hexarion.launcher.game.file.FileManager;
import fr.arinonia.hexarion.launcher.game.file.RamManager;
import fr.arinonia.hexarion.launcher.game.file.UsernameSaver;
import fr.arinonia.hexarion.launcher.profile.Profile;
import fr.arinonia.hexarion.launcher.ui.LauncherFrame;
import fr.arinonia.hexarion.launcher.updater.DownloadJob;
import fr.arinonia.hexarion.launcher.updater.DownloadManager;
import fr.arinonia.hexarion.launcher.updater.Updater;
import fr.arinonia.hexarion.launcher.utils.OperatingSystem;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher
 */
public class Launcher {

    private LauncherFrame parent;
    private ExecutorService executorService;

    private final AzAuthenticator authentication = new AzAuthenticator(Constants.AUTH_URL);
    private final Profile profile = new Profile();

    private final FileManager fileManager = new FileManager("Hexarion");
    private final GameRunner gameRunner = new GameRunner(this);
    private final RamManager ramManager = new RamManager(this);
    private final UsernameSaver saver = new UsernameSaver(this);
    private static final Gson GSON = new Gson();

    public void start() {
        System.out.println("Start class: '" + this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1) + "'");

        executorService = new ThreadPoolExecutor(1, 1, 1000,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        // Create parent Frame
        System.out.println("Create main frame..");
        parent = new LauncherFrame(this);
        parent.setVisible(true);

        System.out.println("Current operating system: " + OperatingSystem.getCurrentPlatform().getName().toLowerCase());
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            System.out.println("Termination took too long.");
        }
        System.out.println("Good bye =)");
        System.exit(0);
    }

    public void launchGame() {

        Updater updater = new Updater();
        DownloadJob javaJob = new DownloadJob("Java",this.parent.getPanel());
        DownloadJob gameJob = new DownloadJob("game", this.parent.getPanel());
        updater.addJobToDownload(new DownloadManager(Constants.DOWNLOAD_JAVA_URL,javaJob,  fileManager.getRuntime()));
        updater.addJobToDownload(new DownloadManager(Constants.DOWNLOAD_GAME_URL, gameJob,  fileManager.getGame()));

        updater.setFileDeleter(true);
        new Thread(() -> {
            updater.start();
            gameRunner.launch();
            this.shutdown();
        }).start();

    }

    public JFrame getParent() {
        return parent;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public AzAuthenticator getAuthentication() {
        return authentication;
    }

    public Profile getProfile() {
        return profile;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public GameRunner getGameRunner() {
        return gameRunner;
    }

    public RamManager getRamManager() {
        return ramManager;
    }

    public UsernameSaver getSaver() {
        return saver;
    }
}
