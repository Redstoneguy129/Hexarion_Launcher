package fr.arinonia.hexarion.launcher.ui.ram;

import fr.arinonia.hexarion.launcher.Constants;
import fr.arinonia.hexarion.launcher.Launcher;
import fr.arinonia.hexarion.launcher.ui.LauncherPanel;
import fr.arinonia.hexarion.launcher.utils.UiUtil;
import fr.arinonia.hexarion.launcher.utils.WindowMover;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arinonia on 26/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui.ram
 */
public class RamFrame extends JFrame {

    private RamPanel panel;

    public RamFrame(Launcher launcher) throws HeadlessException {
        this.setTitle(Constants.LAUNCHER_NAME + "Settings");
        this.setSize(897,1035);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setIconImage(UiUtil.getImage("/images/icon.png"));
        this.setLocationRelativeTo(null);

        WindowMover mover = new WindowMover(this);
        this.addMouseListener(mover);
        this.addMouseMotionListener(mover);

        // Init Launcher Panel
        this.setContentPane(panel = new RamPanel(this, launcher));
    }
}
