package fr.arinonia.hexarion.launcher.ui.ram;

import fr.arinonia.hexarion.launcher.Launcher;
import fr.arinonia.hexarion.launcher.game.file.RamManager;
import fr.arinonia.hexarion.launcher.ui.components.IconButton;
import fr.arinonia.hexarion.launcher.utils.UiUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Arinonia on 26/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui.ram
 */
public class RamPanel extends JPanel {

    private final JButton closeButton = new IconButton("/images/controls/buttons/close.png", "/images/controls/buttons/close_hover.png");
    private final RamFrame ramFrame;
    private final Launcher launcher;
    public JSlider ramSlider = new JSlider();
    public final JLabel ramLabel;

    public RamPanel(RamFrame ramFrame, Launcher launcher) {
        super(null);

        this.ramFrame = ramFrame;
        this.launcher = launcher;

        this.setBackground(new Color(0,0,0,0));

        this.closeButton.setBounds(647, 400, 57, 57);
        this.add(closeButton);

        this.ramLabel = new JLabel(this.launcher.getFileManager().getRamFile().exists() ? (this.launcher.getRamManager().getRam() + "Go") : "1Go");
        this.ramLabel.setFont(new Font("Arial", Font.BOLD, 60));
        this.ramLabel.setForeground(new Color(48, 145, 255));
        this.ramLabel.setBounds(400, 620, 175, 48);
        add(this.ramLabel);

        this.ramSlider.setMinimum(1);
        this.ramSlider.setMaximum(10);
        this.ramSlider.setBackground(new Color(0,0,0,0));
        this.ramSlider.setOpaque(false);
        this.ramSlider.setBounds(280, 700, 340, 50);
        this.ramSlider.setFocusable(false);
        this.ramSlider.setBorder(BorderFactory.createEmptyBorder());
        this.ramSlider.addChangeListener(e -> {
            this.launcher.getRamManager().setRam(ramSlider.getValue() + "Go");
            this.ramLabel.setText(ramSlider.getValue() + "Go");
            this.launcher.getProfile().setVmArgs(ramSlider.getValue() + "G");
        });
        this.ramSlider.setValue(this.launcher.getFileManager().getRamFile().exists() ? Integer.parseInt(this.launcher.getRamManager().getRam()) : 1);
        add(this.ramSlider);
        this.setupActionListener();
    }

    private void setupActionListener(){
        closeButton.addActionListener(e -> {
            ramFrame.setVisible(false);
            this.launcher.getParent().setVisible(true);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(UiUtil.getImage("/images/ram.png"), 0,0,null);
    }
}
