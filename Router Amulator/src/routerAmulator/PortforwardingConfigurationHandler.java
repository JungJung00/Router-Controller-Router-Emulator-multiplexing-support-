package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.PortForwardingInfo;

import java.util.ArrayList;

import static common.Now.getTime;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */

public class PortforwardingConfigurationHandler {
    // private  ArrayList<PortForwardingInfo> portForwardingInfo;
    private JSONArray portForwardingInfo;

    public PortforwardingConfigurationHandler(){
        portForwardingInfo = new JSONArray();
    }

    public JSONObject handlePortforwardingConfigurationOperation(JSONObject operation){
        // ResultSocketStructure result = null;
        JSONObject result = null;

        String _operation = ((JSONObject)operation.get("body")).get("operation").toString();
        JSONArray parameter = (JSONArray)((JSONObject)operation.get("body")).get("subValues");

        switch(_operation){
            case "GET_PORTFORWARD":
                result = handleGetPortforwardingConfigurationOperation(_operation);
                break;

            case "ADD_PORTFORWARD":
                result = handleSetPortforwardingConfigurationOperation(_operation, parameter);
                break;
        }

        return result;
    }

    private JSONObject handleGetPortforwardingConfigurationOperation(String code){
        // ResultSocketStructure result = new ResultSocketStructure("Portforwarding Configuration Information - GET");
        // result.setPortForwardingConfigurations(portForwardingInfo);

        JSONObject result = JsonOperationManager.generateJsonResult(code, portForwardingInfo, true);

        return result;
    }

    private JSONObject handleSetPortforwardingConfigurationOperation(String code, JSONArray parameter){
        // portForwardingInfo.add(operation.getPortForwardingSetting());

        // PortForwardingInfo pfi = portForwardingInfo.get(portForwardingInfo.size() - 1);
        JSONObject pfi = new JSONObject();

        pfi.put("name", parameter.get(0).toString());
        pfi.put("IP", parameter.get(1).toString());
        pfi.put("internalPort", parameter.get(2).toString());
        pfi.put("externalPort", parameter.get(3).toString());

        portForwardingInfo.add(portForwardingInfo.size(), pfi);

        System.out.println(getTime() + " PortForwarding Operation : " + parameter.get(0).toString() + " | Inner IP " + parameter.get(1).toString() +
                        " | Inner Port " + parameter.get(2).toString() + " | Outer Port " + parameter.get(3).toString());

        // return new ResultSocketStructure("Portforwarding Configuration Information - SET");
        return JsonOperationManager.generateJsonResult(code, null, true);
    }
}
