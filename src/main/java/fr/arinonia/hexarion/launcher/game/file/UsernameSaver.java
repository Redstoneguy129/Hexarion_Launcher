package fr.arinonia.hexarion.launcher.game.file;

import fr.arinonia.hexarion.launcher.Launcher;

import java.io.*;

/**
 * Created by Arinonia on 26/08/2020 inside the package - fr.arinonia.hexarion.launcher.game.file
 */
public class UsernameSaver {

    private final File file;

    public UsernameSaver(Launcher launcher) {
        this.file = new File(launcher.getFileManager().getGame(), "username.properties");
    }

    public String getUsername(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String username = br.readLine();
            br.close();
            return username;
        }catch (IOException e){
            System.err.println("Impossible to get `username`");
            return "";
        }
    }

    public void setUsername(String username){
        try{
            FileWriter fw = new FileWriter(this.file);
            fw.write(username);
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
