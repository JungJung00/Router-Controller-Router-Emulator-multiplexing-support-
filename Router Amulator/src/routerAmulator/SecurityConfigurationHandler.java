package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.SecurityConfigurationInfo;

import static common.Now.getTime;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */

public class SecurityConfigurationHandler {
    SecurityConfigurationInfo securityConfigurationInfo;

    private String SSID;
    private String PW;

    public SecurityConfigurationHandler(){
        // securityConfigurationInfo = new SecurityConfigurationInfo("TEST", "TESINGG");
        SSID = "";
        PW = "";
    }

    public JSONObject handleSecurityConfigurationOperation(JSONObject operation){
//        ResultSocketStructure result = null;
        JSONObject result = null;

        JSONArray parameter = (JSONArray)((JSONObject)operation.get("body")).get("subValues");
        String _operation = ((JSONObject)operation.get("body")).get("operation").toString();

        switch(_operation){
            case "GET_AP_SETTING":
                result = handleGetSecurityConfigurationOperation(_operation);
                break;

            case "SET_AP_SETTING":
                result = handleSetSecurityConfigurationOperation(_operation, parameter);
                break;
        }

        return result;
    }

    private JSONObject handleGetSecurityConfigurationOperation(String code){
        // ResultSocketStructure result = new ResultSocketStructure("Security Configuration Information - GET");
//        result.setSecurityConfigurationInfo(securityConfigurationInfo);

        JSONArray parameter = new JSONArray();
        parameter.add(0, SSID);
        parameter.add(1, PW);
        JSONObject result = JsonOperationManager.generateJsonResult(code, parameter, true);

        return result;
    }

    private JSONObject handleSetSecurityConfigurationOperation(String code, JSONArray paramter){
        SSID = paramter.get(0).toString();
        PW = paramter.get(1).toString();

        // securityConfigurationInfo = operation.getSecuritySetting();
        System.out.println(getTime() + " Security Operation : SSID " + paramter.get(0).toString() + " | PW " + paramter.get(1).toString());

        // return new ResultSocketStructure("Security Configuration Information - SET");
        return JsonOperationManager.generateJsonResult(code, null, true);
    }
}
