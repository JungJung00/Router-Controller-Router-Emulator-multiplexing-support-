package router;

import java.io.Serializable;

public class SecurityConfigurationInfo implements Serializable{
    private String SSID;
    private String password;

    /* Constructor */
    public SecurityConfigurationInfo(){}
    public SecurityConfigurationInfo(String SSID, String password){
        this.SSID = SSID;
        this.password = password;
    }

    public String getSSID() {
        return SSID;
    }
    public void setSSID(String SSID) {
        this.SSID = SSID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
