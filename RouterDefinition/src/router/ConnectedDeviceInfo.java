package router;

import java.io.Serializable;

public class ConnectedDeviceInfo implements Serializable{
    String deviceName;

    public ConnectedDeviceInfo(){}

    public ConnectedDeviceInfo(String name){
        deviceName = name;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
