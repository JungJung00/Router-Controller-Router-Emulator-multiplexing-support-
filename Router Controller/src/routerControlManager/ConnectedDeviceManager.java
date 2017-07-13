package routerControlManager;

import com.sun.istack.internal.Nullable;
import common.DetailOperationCode;
import common.OperationCode;
import common.OperationSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectedDeviceManager {
    private static class Singleton{
        private static final ConnectedDeviceManager instance = new ConnectedDeviceManager();
    }

    public static ConnectedDeviceManager getInstance(){
        return ConnectedDeviceManager.Singleton.instance;
    }

    public JSONObject connectedDeviceOperation(){
        // return new OperationSocketStructure(OperationCode.CONNECTED_DEVICES);
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.GET_CONNECTED_DEVICES, null);
    }
}
