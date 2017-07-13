package routerControlManager;

import common.DetailOperationCode;
import common.OperationCode;
import common.OperationSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.SecurityConfigurationInfo;

public class SecurityConfigurationManager {
    private static class Singleton{
        private static final SecurityConfigurationManager instance = new SecurityConfigurationManager();
    }

    public static SecurityConfigurationManager getInstance(){
        return SecurityConfigurationManager.Singleton.instance;
    }

    public JSONObject getSecurityConfiguration(){
        // return new OperationSocketStructure(OperationCode.SECURITY_CONFIGURATION, DetailOperationCode.GET_SECURITY_CONFIGURATION);
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.GET_SECURITY_CONFIGURATION, null);
    }

    public JSONObject setSecurityConfiguration(JSONArray parameter){
        // OperationSocketStructure operation = new OperationSocketStructure(OperationCode.SECURITY_CONFIGURATION, DetailOperationCode.SET_SECURITY_CONFIGURATION);
        // operation.setSecuritySetting(new SecurityConfigurationInfo(SSID, password));

        return JsonOperationManager.generateJsonOperation(DetailOperationCode.SET_SECURITY_CONFIGURATION, parameter);
    }
}
