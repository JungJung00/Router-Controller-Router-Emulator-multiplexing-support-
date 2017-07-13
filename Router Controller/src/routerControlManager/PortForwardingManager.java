package routerControlManager;

import common.DetailOperationCode;
import common.OperationCode;
import common.OperationSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.PortForwardingInfo;

public class PortForwardingManager {
    private static class Singleton{
        private static final PortForwardingManager instance = new PortForwardingManager();
    }

    public static PortForwardingManager getInstance(){
        return PortForwardingManager.Singleton.instance;
    }

    public JSONObject getPortForwardingOperation(){
        // return new OperationSocketStructure(OperationCode.PORTFORWARDING_CONFIGURATION, DetailOperationCode.GET_PORTFORWARDING_CONFIGURATION);
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.GET_PORTFORWARDING_CONFIGURATION, null);
    }

    public JSONObject setPortForwardingOperation(JSONArray parameter){
        // OperationSocketStructure operation = new OperationSocketStructure(OperationCode.PORTFORWARDING_CONFIGURATION, DetailOperationCode.SET_PORTFORWARDING_CONFIGURATION);
        //operation.setPortForwardingSetting(new PortForwardingInfo(name, innerIP, outerP, innerP));

        return JsonOperationManager.generateJsonOperation(DetailOperationCode.SET_PORTFORWARDING_CONFIGURATION, parameter);
    }
}
