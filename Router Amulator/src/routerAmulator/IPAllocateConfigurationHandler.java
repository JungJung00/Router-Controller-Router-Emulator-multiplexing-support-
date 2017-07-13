package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */

public class IPAllocateConfigurationHandler {
    public ResultSocketStructure handleIPAllocateConfigurationOperation(OperationSocketStructure operation){
        ResultSocketStructure result = null;

        switch(operation.getDetailOperationCode()){
            case GET_IPALLOCATE_CONFIGURATION:
                result = handleGetIPAllocateConfigurationOperation(operation);
                break;

            case SET_IPALLOCATE_CONFIGURATION:
                result = handleSetIPAllocateConfigurationOperation(operation);
                break;
        }

        return result;
    }

    private ResultSocketStructure handleGetIPAllocateConfigurationOperation(OperationSocketStructure operation){
        return new ResultSocketStructure("IP Allocate Configuration Information - GET");
    }

    private ResultSocketStructure handleSetIPAllocateConfigurationOperation(OperationSocketStructure operation){
        return new ResultSocketStructure("IP Allocate Configuration Information - SET");
    }
}
