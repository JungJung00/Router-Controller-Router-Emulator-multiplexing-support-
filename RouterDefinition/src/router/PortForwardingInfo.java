package router;

import java.io.Serializable;

public class PortForwardingInfo implements Serializable{
    private String ruleName;
    private String innerIPAddress;
    private String outerPort;
    private String innerPort;

    /* Constructor */
    public PortForwardingInfo(){}
    public PortForwardingInfo(String name, String innerIP, String outerP, String innerP){
        ruleName = name;
        innerIPAddress = innerIP;
        outerPort = outerP;
        innerPort = innerP;
    }

    /* Configuration getter and setter */
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getInnerIPAddress() {
        return innerIPAddress;
    }
    public void setInnerIPAddress(String innerIPAddress) {
        this.innerIPAddress = innerIPAddress;
    }
    public String getOuterPort() {
        return outerPort;
    }
    public void setOuterPort(String outerPort) {
        this.outerPort = outerPort;
    }
    public String getInnerPort() {
        return innerPort;
    }
    public void setInnerPort(String innerPort) {
        this.innerPort = innerPort;
    }
}
