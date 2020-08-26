package fr.arinonia.hexarion.launcher.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.utils
 */
public class WindowMover extends MouseAdapter {

    private Point click;
    private final JFrame window;

    public WindowMover(JFrame window) {
        this.window = window;
    }

    public void mouseDragged(MouseEvent e) {
        if (this.click != null) {
            Point draggedPoint = MouseInfo.getPointerInfo().getLocation();
            this.window.setLocation(new Point((int)draggedPoint.getX() - (int)this.click.getX(), (int)draggedPoint.getY() - (int)this.click.getY()));
        }
    }

    public void mousePressed(MouseEvent e) {
        this.click = e.getPoint();
    }
}
