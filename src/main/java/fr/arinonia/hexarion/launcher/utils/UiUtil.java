package fr.arinonia.hexarion.launcher.utils;

import fr.arinonia.hexarion.launcher.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.utils
 */
public class UiUtil {

    public static BufferedImage getImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Main.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon getIconImage(String path){
        URL url = Main.class.getResource(path);

        if (url == null){
            System.err.println("Unable to load resource " + path);
            return null;
        }
        return new ImageIcon(url);
    }

}
