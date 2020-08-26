package fr.arinonia.hexarion.launcher.updater;

/**
 * Created by Arinonia on 09/08/2020 inside the package - fr.arinonia.hexarion.launcher.updater
 */
public interface  DownloadListener {
    public void onDownloadJobFinished(DownloadJob job);
    public void onDownloadJobProgressChanged(DownloadJob job);
    public void onDownloadJobStarted(DownloadJob job);
}
