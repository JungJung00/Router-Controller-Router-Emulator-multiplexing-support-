package routerControlManager;

import common.DetailOperationCode;
import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONObject;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */
public class ErrorManager {
    public JSONObject handleError(JSONObject operation){
        // return new ResultSocketStructure("ERROR HAPPENED WHEN OPERATING " + operation.get("operation")
        //        + "(" + operation.get("result") + ")");
        return JsonOperationManager.generateJsonResult("ERROR", null);
    }
}
