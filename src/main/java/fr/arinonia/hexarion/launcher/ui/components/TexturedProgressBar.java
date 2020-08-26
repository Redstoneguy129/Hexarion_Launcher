package fr.arinonia.hexarion.launcher.ui.components;

import fr.arinonia.hexarion.launcher.utils.UiUtil;
import fr.arinonia.hexarion.launcher.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui.components
 */
public class TexturedProgressBar extends JProgressBar {

    private final BufferedImage backgroundImage;
    private final BufferedImage foregroundImage;

    public TexturedProgressBar(BufferedImage backgroundImage, BufferedImage foregroundImage) {
       this.backgroundImage = backgroundImage;
       this.foregroundImage = foregroundImage;
       this.setBorder(BorderFactory.createEmptyBorder());
       this.setBackground(new Color(0,0,0,0));
       this.setOpaque(false);


    }

    public TexturedProgressBar(String backgroundPath, String foregroundPath) {
        this(UiUtil.getImage(backgroundPath), UiUtil.getImage(foregroundPath));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage,0,0,null);

        int fgSize = Util.crossMult(this.getValue(), this.getMaximum(), this.getWidth());

        if (fgSize > 0) {
            BufferedImage subForeground = this.foregroundImage.getSubimage(0, 0, fgSize, this.getHeight());
            g.drawImage(subForeground, 0, 0, subForeground.getWidth(), subForeground.getHeight(), this);
        }
    }
}
