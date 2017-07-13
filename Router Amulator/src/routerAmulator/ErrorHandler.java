package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONObject;

/**
 * Created by qwert on 2017-04-25.
 */
public class ErrorHandler {
    public JSONObject handleError(JSONObject operation) {
//        return new ResultSocketStructure("ERROR HAPPENED WHEN OPERATING " + operation.getOperationCode()
//                                                        + "(" + operation.getDetailOperationCode() + ")");
        String _operation = ((JSONObject)operation.get("body")).get("operation").toString();

        return JsonOperationManager.generateJsonResult(_operation, null, false);
    }
}
