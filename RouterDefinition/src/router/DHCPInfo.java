package router;

import java.io.Serializable;

public class DHCPInfo implements Serializable{
    private String minDHCPAddressRange;
    private String maxDHCPAddressRange;
    private String ipAllocationTime;

    public DHCPInfo(){}
    public DHCPInfo(String min, String max, String time){
        minDHCPAddressRange = min;
        maxDHCPAddressRange = max;
        ipAllocationTime = time;
    }

    /* Configuration getter and setter */
    public String getMinDHCPAddressRange() {
        return minDHCPAddressRange;
    }
    public void setMinDHCPAddressRange(String minDHCPAddressRange) {
        this.minDHCPAddressRange = minDHCPAddressRange;
    }
    public String getMaxDHCPAddressRange() {
        return maxDHCPAddressRange;
    }
    public void setMaxDHCPAddressRange(String maxDHCPAddressRange) {
        this.maxDHCPAddressRange = maxDHCPAddressRange;
    }
    public String getIpAllocationTime() {
        return ipAllocationTime;
    }
    public void setIpAllocationTime(String ipAllocationTime) {
        this.ipAllocationTime = ipAllocationTime;
    }
}
