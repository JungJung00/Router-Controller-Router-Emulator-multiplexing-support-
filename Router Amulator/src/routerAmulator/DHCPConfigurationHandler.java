package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.DHCPInfo;

import static common.Now.getTime;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */

public class DHCPConfigurationHandler {
    // private DHCPInfo dhcpInfo;

    private String startIP;
    private String endIP;
    private int rentalTime;

    public DHCPConfigurationHandler(){
        // dhcpInfo = new DHCPInfo("127.0.0.1", "127.0.0.50", "60");
        startIP = "127.0.0.1";
        endIP = "127.0.0.1";
        rentalTime = 60;
    }

    public JSONObject handleDHCPConfigurationOperation(JSONObject operation){
        JSONObject result = null;

        JSONArray parameter = (JSONArray)((JSONObject)operation.get("body")).get("subValues");
        String _operation = ((JSONObject)operation.get("body")).get("operation").toString();

        switch(_operation){
            case "GET_DHCP_SETTING":
                result = handleGetDHCPConfigurationOperation(_operation);
                break;

            case "ADD_DHCP_SETTING":
                result = handleSetDHCPConfigurationOperation(_operation, parameter);
                break;
        }

        return result;
    }

    private JSONObject handleGetDHCPConfigurationOperation(String code){
        // ResultSocketStructure result = new ResultSocketStructure("DHCP Configuration Information - GET");
        // result.setDhcpConfigurations(dhcpInfo);

        JSONObject values = new JSONObject();
        values.put("start", startIP);
        values.put("end", endIP);
        values.put("rental", rentalTime);

        JSONArray parameter = new JSONArray();
        parameter.add(0, values);

        JSONObject result = JsonOperationManager.generateJsonResult(code, parameter, true);

        return result;
    }

    private JSONObject handleSetDHCPConfigurationOperation(String code, JSONArray parameter){
        // dhcpInfo = operation.getDhcpSetting();
        startIP = parameter.get(0).toString();
        endIP = parameter.get(1).toString();
        rentalTime = Integer.parseInt(parameter.get(2).toString());

        System.out.println(getTime() + " DHCP operation : " + parameter.get(0).toString() +
                " ~ " + parameter.get(1).toString() + " ..." + parameter.get(2).toString() + " minutes");

        // return new ResultSocketStructure("DHCP Configuration Information - SET");
        return JsonOperationManager.generateJsonResult(code, null, true);
    }
}
