package fr.arinonia.hexarion.launcher.ui;

import fr.arinonia.hexarion.launcher.Constants;
import fr.arinonia.hexarion.launcher.Launcher;
import fr.arinonia.hexarion.launcher.utils.UiUtil;
import fr.arinonia.hexarion.launcher.utils.WindowMover;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui
 */
public class LauncherFrame extends JFrame {

    private LauncherPanel panel;

    public LauncherFrame(Launcher launcher) {
        this.setTitle(Constants.LAUNCHER_NAME);
        this.setSize(897,1035);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setIconImage(UiUtil.getImage("/images/icon.png"));
        this.setLocationRelativeTo(null);

        WindowMover mover = new WindowMover(this);
        this.addMouseListener(mover);
        this.addMouseMotionListener(mover);

        // Init Launcher Panel
        this.setContentPane(panel = new LauncherPanel(launcher));
    }

    public LauncherPanel getPanel() {
        return panel;
    }
}
