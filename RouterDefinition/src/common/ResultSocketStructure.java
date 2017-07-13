package common;

import router.ConnectedDeviceInfo;
import router.DHCPInfo;
import router.PortForwardingInfo;
import router.SecurityConfigurationInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jeong Taegyun on 2017-04-23.
 */

public class ResultSocketStructure implements Serializable{
    String message;

    ArrayList<ConnectedDeviceInfo> connectedDevices;
    DHCPInfo dhcpConfigurations;
    ArrayList<PortForwardingInfo> portForwardingConfigurations;
    SecurityConfigurationInfo securityConfigurationInfo;

    /* Constructor */
    public ResultSocketStructure(){}
    public ResultSocketStructure(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ConnectedDeviceInfo> getConnectedDevices() {
        return connectedDevices;
    }

    public void setConnectedDevices(ArrayList<ConnectedDeviceInfo> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }

    public DHCPInfo getDhcpConfigurations() {
        return dhcpConfigurations;
    }

    public void setDhcpConfigurations(DHCPInfo dhcpConfigurations) {
        this.dhcpConfigurations = dhcpConfigurations;
    }

    public ArrayList<PortForwardingInfo> getPortForwardingConfigurations() {
        return portForwardingConfigurations;
    }

    public void setPortForwardingConfigurations(ArrayList<PortForwardingInfo> portForwardingConfigurations) {
        this.portForwardingConfigurations = portForwardingConfigurations;
    }

    public SecurityConfigurationInfo getSecurityConfigurationInfo() {
        return securityConfigurationInfo;
    }

    public void setSecurityConfigurationInfo(SecurityConfigurationInfo securityConfigurationInfo) {
        this.securityConfigurationInfo = securityConfigurationInfo;
    }
}
