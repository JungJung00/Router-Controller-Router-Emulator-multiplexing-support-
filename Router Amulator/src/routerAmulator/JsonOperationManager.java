package routerAmulator;

/**
 * Created by Jeong Taegyun on 2017-07-04.
 */

import com.sun.istack.internal.Nullable;
import common.DetailOperationCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonOperationManager {
    public static JSONObject generateJsonOperation(DetailOperationCode code, @Nullable JSONArray parameters){
        JSONObject operation = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("src", "localhost");
        header.put("dest", "localhost");
        header.put("type", "req");

        JSONObject seq = new JSONObject();
        seq.put("cur", 0);
        seq.put("end", 0);

        JSONObject body = new JSONObject();
        JSONArray subValues= new JSONArray();

        operation.put("header", header);
        operation.put("seq", seq);

        // TODO : Error handling needed
        switch(code){
            case TURN_ON_POWER:
                body.put("operation", "SET_AP_POWER");
                subValues.add(0, true);
                break;

            case TURN_OFF_POWER:
                body.put("operation", "SET_AP_POWER");
                subValues.add(0, false);
                break;

            case GET_CONNECTED_DEVICES:
                body.put("operation", "GET_CONNECTED_DEVICE");
                break;

            case GET_DHCP_CONFIGURATION:
                body.put("operation", "GET_DHCP_SETTING");
                break;

            case SET_DHCP_CONFIGURATION:
                if (parameters == null || parameters.size() != 3){
                    break;
                }
                else{
                    body.put("operation", "ADD_DHCP_SETTING");
                    subValues.add(0, parameters.get(0));    // Start IP
                    subValues.add(1, parameters.get(1));    // End IP
                    subValues.add(2, parameters.get(2));    // IP allocation time
                    break;
                }

            case GET_IPALLOCATE_CONFIGURATION:
                body.put("operation", "GET_DHCP_SETTING");
                break;

            case SET_IPALLOCATE_CONFIGURATION:
                if (parameters == null || parameters.size() != 3){

                    break;
                }
                else{
                    body.put("operation", "ADD_DHCP_SETTING");
                    subValues.add(0, parameters.get(0));    // Start IP
                    subValues.add(1, parameters.get(1));    // End IP
                    subValues.add(2, parameters.get(2));    // IP allocation time
                    break;
                }

            case GET_PORTFORWARDING_CONFIGURATION:
                body.put("operation", "GET_PORTFOWARD");
                break;

            case SET_PORTFORWARDING_CONFIGURATION:
                if (parameters == null || parameters.size() != 4){

                    break;
                }
                else{
                    body.put("operation", "ADD_PORTFOWARD");
                    subValues.add(0, parameters.get(0));    // Name
                    subValues.add(1, parameters.get(1));    // IP
                    subValues.add(2, parameters.get(2));    // Inner port
                    subValues.add(3, parameters.get(3));    // Outer Port
                    break;
                }

            case GET_SECURITY_CONFIGURATION:
                body.put("operation", "GET_AP_SETTING");
                break;

            case SET_SECURITY_CONFIGURATION:
                if (parameters == null || parameters.size() != 2){

                    break;
                }
                else {
                    body.put("operation", "SET_AP_SETTING");
                    subValues.add(0, parameters.get(0));    // SSID
                    subValues.add(1, parameters.get(1));    // Password
                    break;
                }

            default:
                body.put("operation", "UNKNOWN");
                break;
        }

        body.put("subValues", subValues);

        operation.put("body", body);

        return operation;
    }

    public static JSONObject generateJsonResult(String code, @Nullable JSONArray parameters, boolean status){
        JSONObject operation = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("src", "localhost");
        header.put("dest", "localhost");
        header.put("type", "res");

        if (status)
            header.put("result", 1);    // success : 1, fail : 0
        else
            header.put("result", 0);

        JSONObject seq = new JSONObject();
        seq.put("cur", 0);
        seq.put("end", 0);

        JSONObject body = new JSONObject();
        body.put("operation", code);
        body.put("subValues", parameters);

        operation.put("header", header);
        operation.put("seq", seq);
        operation.put("body", body);

        return operation;
    }
}