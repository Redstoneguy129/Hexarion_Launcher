package fr.arinonia.hexarion.launcher.ui;

import com.azuriom.azauth.model.User;
import fr.arinonia.hexarion.launcher.Launcher;
import fr.arinonia.hexarion.launcher.game.file.UsernameSaver;
import fr.arinonia.hexarion.launcher.ui.components.IconButton;
import fr.arinonia.hexarion.launcher.ui.components.TexturedProgressBar;
import fr.arinonia.hexarion.launcher.ui.ram.RamFrame;
import fr.arinonia.hexarion.launcher.updater.DownloadJob;
import fr.arinonia.hexarion.launcher.updater.DownloadListener;
import fr.arinonia.hexarion.launcher.utils.Callback;
import fr.arinonia.hexarion.launcher.utils.UiUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui
 */
public class LauncherPanel extends JPanel implements DownloadListener {

    private final Launcher launcher;

    private final JButton closeButton = new IconButton("/images/controls/buttons/close.png", "/images/controls/buttons/close_hover.png");
    private final JButton playButton = new IconButton("/images/controls/buttons/play.png", "/images/controls/buttons/play_hover.png");
    private final JButton settingsButton = new IconButton("/images/controls/buttons/settings.png", "/images/controls/buttons/settings_hover.png");
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField("");
    private final JProgressBar progressBar = new TexturedProgressBar("/images/controls/bar/air.png", "/images/controls/bar/bar.png");

    public LauncherPanel(Launcher launcher) {
        super(null);
        this.launcher = launcher;

        this.setBackground(new Color(0,0,0,0));

        this.closeButton.setBounds(614, 408, 57, 57);
        this.add(closeButton);

        this.playButton.setBounds(385, 710, 90, 144);
        this.add(playButton);

        this.settingsButton.setBounds(34, 352, 102, 125);
        this.add(settingsButton);

        this.usernameField.setBounds(300, 507, 290, 51);
        this.usernameField.setBorder(BorderFactory.createEmptyBorder());
        this.usernameField.setCaretColor(new Color(0, 111, 255));
        this.usernameField.setOpaque(false);
        this.usernameField.setForeground(new Color(48, 145, 255));
        this.usernameField.setFont(new Font("Arial", Font.BOLD, 26));
        this.usernameField.setText(launcher.getSaver().getUsername());
        this.add(this.usernameField);

        this.passwordField.setBounds(300, 605, 290, 51);
        this.passwordField.setBorder(BorderFactory.createEmptyBorder());
        this.passwordField.setCaretColor(new Color(0, 111, 255));
        this.passwordField.setOpaque(false);
        this.passwordField.setForeground(new Color(48, 145, 255));
        this.passwordField.setFont(new Font("Arial", Font.BOLD, 26));
        this.add(this.passwordField);

        this.progressBar.setBounds(153, 940, 533, 30);
        this.add(this.progressBar);

        this.setupButtonListeners();

        System.out.println("Display: '" + this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1) + "'");
    }

    private void setupButtonListeners() {
        this.closeButton.addActionListener(e -> this.launcher.shutdown());
        this.playButton.addActionListener(e -> {
            tryLogin();
        });
        this.settingsButton.addActionListener(e -> {
            RamFrame ramFrame = new RamFrame(this.launcher);
            if (this.launcher.getParent().isVisible()){
                this.launcher.getParent().setVisible(false);
                ramFrame.setVisible(true);
            }
        });
        this.usernameField.addActionListener(e -> {
            tryLogin();
        });
        this.passwordField.addActionListener(e -> {
            tryLogin();
        });
    }

    private void tryLogin() {
        final String username = this.usernameField.getText();
        final String password = String.valueOf(this.passwordField.getPassword());

       if (username.isEmpty()) {
           this.usernameField.requestFocus();
           return;
       }
       else if (password.isEmpty()) {
           this.passwordField.requestFocus();
           return;
       }

       setEnableForm(false);
       this.passwordField.setText("");

       auth(username, password, this.launcher::launchGame);
    }

    private void auth(final String username, final String password, final Callback callback) {
        this.launcher.getExecutorService().submit(() -> {
            try {
                User user = this.launcher.getAuthentication().authenticate(username, password);

                this.launcher.getProfile().initAuth(user);
                this.launcher.getProfile().setPassword(password);
                this.launcher.getSaver().setUsername(username);
                callback.callback();
            } catch (Exception ex) {
                setEnableForm(true);
                System.err.println("An error as occurred when you try to login.");
                System.err.println(ex);
                JOptionPane.showMessageDialog(null,"Une erreur est survenue durant la connexion","Hexarion-error login", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void setEnableForm(boolean enabled) {
        this.usernameField.setEnabled(enabled);
        this.passwordField.setEnabled(enabled);
        this.playButton.setEnabled(enabled);
        this.settingsButton.setEnabled(enabled);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(UiUtil.getImage("/images/overlay.png"), 0,0,null);
    }

    @Override
    public void onDownloadJobFinished(DownloadJob job) {
        System.out.println(job.getName() + " is finished");
    }

    @Override
    public void onDownloadJobProgressChanged(DownloadJob job) {
        System.out.println("Download " + job.getName() + ": " + job.getRemainingFiles().size() + " files remaining");
        progressBar.setValue(job.getAllFiles().size() - job.getRemainingFiles().size());
    }

    @Override
    public void onDownloadJobStarted(DownloadJob job) {
        System.out.println("'" + job.getName() + '"' +" job started to download");
        progressBar.setMaximum(job.getRemainingFiles().size());
    }
}
