package fr.arinonia.hexarion.launcher.profile;

import com.azuriom.azauth.model.User;

import java.util.UUID;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.profile
 */
public class Profile {
    private String displayName;
    private String accessToken;
    private String username;
    private String password;
    private UUID uuid;
    private String vmArgs;

    public void initAuth(User user) {
        this.displayName = user.getUsername();
        this.accessToken = user.getAccessToken();
        this.username = user.getEmail();
        this.uuid = user.getUuid();

    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getVmArgs() {
        return vmArgs;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setVmArgs(String vmArgs) {
        this.vmArgs = vmArgs;
    }
}
