package routerAmulator;



import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.ConnectedDeviceInfo;

import java.util.ArrayList;

/**
 * Created by Jeong Taegyun on 2017-04-24.
 */
public class ConnectedDeviceHandler {

    public JSONObject handleConnectedDeviceOperation(String code){
        // ArrayList<ConnectedDeviceInfo> connectedDevices = new ArrayList<>();
        JSONArray connectedDevices = new JSONArray();

        // TODO : Change data into real devices information
        for(int i = 0; i < 5; i++){
//            ConnectedDeviceInfo tempDevice = new ConnectedDeviceInfo(i + " Device");
//            connectedDevices.add(tempDevice);

            JSONObject device = new JSONObject();
            device.put("ip", "122.121.29." + (i + 1));
            device.put("mac", "ABC::" + (i + 1));

            connectedDevices.add(i, device);
        }

//        ResultSocketStructure result = new ResultSocketStructure("Connected Devices Information");
//        result.setConnectedDevices(connectedDevices);
        JSONObject result = JsonOperationManager.generateJsonResult(code, connectedDevices, true);

        return result;
    }
}
