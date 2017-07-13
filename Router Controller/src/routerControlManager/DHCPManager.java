package routerControlManager;

import common.DetailOperationCode;
import common.OperationCode;
import common.OperationSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.DHCPInfo;

public class DHCPManager {
    private static class Singleton{
        private static final DHCPManager instance = new DHCPManager();
    }

    public static DHCPManager getInstance(){
        return DHCPManager.Singleton.instance;
    }

    public JSONObject getDHCPOperation(){
        // return new OperationSocketStructure(OperationCode.DHCP_CONFIGURATION, DetailOperationCode.GET_DHCP_CONFIGURATION);
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.GET_DHCP_CONFIGURATION, null);
    }

    public JSONObject setDHCPOperation(JSONArray parameter){
        // OperationSocketStructure operation = new OperationSocketStructure(OperationCode.DHCP_CONFIGURATION, DetailOperationCode.SET_DHCP_CONFIGURATION);
        // operation.setDhcpSetting(new DHCPInfo(min, max, time));
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.SET_DHCP_CONFIGURATION, parameter);
    }
}
