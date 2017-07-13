package common;

import router.DHCPInfo;
import router.PortForwardingInfo;
import router.SecurityConfigurationInfo;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Jeong Taegyun on 2017-04-21.
 */

/* define socket data structure for operation that control router */
public class OperationSocketStructure implements Serializable {
    private static final long serialVersionUID = 1L;

    private OperationCode operationCode;
    private DetailOperationCode detailOperationCode;

    private DHCPInfo dhcpSetting;
    private PortForwardingInfo portForwardingSetting;
    private SecurityConfigurationInfo securitySetting;

    /* Constructor */
    public OperationSocketStructure(){}
    public OperationSocketStructure(OperationCode operation){
        this.operationCode = operation;
    }
    public OperationSocketStructure(OperationCode commonOperation, DetailOperationCode detailOperation){
        this.operationCode = commonOperation;
        this.detailOperationCode = detailOperation;
    }

    /* Operation getter and setter */
    public OperationCode getOperationCode() {
        return operationCode;
    }
    public void setOperationCode(OperationCode operationCode) {
        this.operationCode = operationCode;
    }
    public DetailOperationCode getDetailOperationCode() {
        return detailOperationCode;
    }
    public void setDetailOperationCode(DetailOperationCode detailOperationCode) {
        this.detailOperationCode = detailOperationCode;
    }

    /* Configuration getter and setter */
    public DHCPInfo getDhcpSetting() {
        return dhcpSetting;
    }
    public void setDhcpSetting(DHCPInfo dhcpSetting) {
        this.dhcpSetting = dhcpSetting;
    }
    public PortForwardingInfo getPortForwardingSetting() {
        return portForwardingSetting;
    }
    public void setPortForwardingSetting(PortForwardingInfo portForwardingSetting) {
        this.portForwardingSetting = portForwardingSetting;
    }
    public SecurityConfigurationInfo getSecuritySetting() {
        return securitySetting;
    }
    public void setSecuritySetting(SecurityConfigurationInfo securitySetting) {
        this.securitySetting = securitySetting;
    }
}
