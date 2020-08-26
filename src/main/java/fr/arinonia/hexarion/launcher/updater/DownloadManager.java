package fr.arinonia.hexarion.launcher.updater;

import java.io.File;

/**
 * Created by Arinonia on 09/08/2020 inside the package - fr.arinonia.hexarion.launcher.updater
 */
public class DownloadManager {

    private final String url;
    private final DownloadJob job;
    private final File file;

    public DownloadManager(String url, DownloadJob job, File file) {
        this.url = url;
        this.job = job;
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public DownloadJob getJob() {
        return job;
    }

    public File getFile() {
        return file;
    }
}
