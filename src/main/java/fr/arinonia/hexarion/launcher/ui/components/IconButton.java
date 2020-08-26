package fr.arinonia.hexarion.launcher.ui.components;

import fr.arinonia.hexarion.launcher.utils.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.ui.components
 */
public class IconButton extends JButton {

    private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);

    public IconButton(ImageIcon icon) {
        super(icon);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setCursor(HAND);
    }

    public IconButton(String iconPath) {
        this(UiUtil.getIconImage(iconPath));
    }

    public IconButton(ImageIcon icon, ImageIcon hoverIcon) {
        this(icon);

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setIcon(hoverIcon);
            }

            public void mouseExited(MouseEvent evt) {
                setIcon(icon);
            }
        });
    }

    public IconButton(String iconPath, String iconHoverPath) {
        this(UiUtil.getIconImage(iconPath), UiUtil.getIconImage(iconHoverPath));
    }
}
