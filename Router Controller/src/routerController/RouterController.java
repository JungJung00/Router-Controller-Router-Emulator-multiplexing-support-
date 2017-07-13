package routerController;

import common.DetailOperationCode;
import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import routerControlManager.*;

import static common.Now.getTime;


public class RouterController extends Equipment{
    /* Managers */
    private static ControllerSocketNetworkManager controllerSocketNetworkManager;
    private static ConnectedDeviceManager connectedDeviceManager;
    private static SecurityConfigurationManager securityConfigurationManager;
    private static DHCPManager dhcpManager;
    private static PortForwardingManager portForwardingManager;
    private static IPAllocationManager ipAllocationManager;

    public RouterController(){
        controllerSocketNetworkManager = ControllerSocketNetworkManager.getInstance();
        connectedDeviceManager = ConnectedDeviceManager.getInstance();
        dhcpManager = DHCPManager.getInstance();
        portForwardingManager = PortForwardingManager.getInstance();
        securityConfigurationManager = SecurityConfigurationManager.getInstance();
    }

    /* Power operation */
    @Override
    public JSONObject turnOnPower(RouterManager router) {
        // Allocate new Socket to serverSocket variable
        System.out.println("Turn on power");

        JSONObject operation = router.onAPPower();
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        // if result is 1(success) power is true, or is false
        router.setPower(Integer.parseInt(((JSONObject)result.get("header")).get("result").toString()));

        return result;
    }
    @Override
    public JSONObject turnOffPower(RouterManager router) {
        // Close socket and allocate null to serverSocket variable
        System.out.println("Turn off power");

        JSONObject operation = router.offAPPower();
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        // if result is 1(success) power is true, or is false
        router.setPower(Integer.parseInt(((JSONObject)result.get("header")).get("result").toString()));

        return result;
    }

    /* Connected device operation */
    public JSONObject getConnectedDevices(RouterManager router){
        // OperationSocketStructure operation = connectedDeviceManager.connectedDeviceOperation();
        JSONObject operation = connectedDeviceManager.connectedDeviceOperation();
        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }

    /* DHCP operation */
    public JSONObject getDHCPConfiguration(RouterManager router){
        // OperationSocketStructure operation = dhcpManager.getDHCPOperation();
        JSONObject operation = dhcpManager.getDHCPOperation();
        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }
    public JSONObject setDHCPConfiguration(RouterManager router, String min, String max, int time){
        // OperationSocketStructure operation = dhcpManager.setDHCPOperation(min, max, time);
        JSONArray parameter = new JSONArray();
        parameter.add(0, min);
        parameter.add(1, max);
        parameter.add(2, time);
        JSONObject operation = dhcpManager.setDHCPOperation(parameter);

        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result").toString());

        return result;
    }

    /* Port Forwarding operation */
    public JSONObject getPortForwardingConfiguration(RouterManager router){
        // OperationSocketStructure operation = portForwardingManager.getPortForwardingOperation();
        JSONObject operation = portForwardingManager.getPortForwardingOperation();

        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }
    public JSONObject setPortForwardingConfiguration(RouterManager router, String name, String innerIP, int outerP, int innerP){
        // OperationSocketStructure operation = portForwardingManager.setPortForwardingOperation(name, innerIP, outerP, innerP);
        JSONArray parameter = new JSONArray();
        parameter.add(0, name);
        parameter.add(1, innerIP);
        parameter.add(2, innerP);
        parameter.add(3, outerP);
        JSONObject operation = portForwardingManager.setPortForwardingOperation(parameter);

        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }

    /* Security operation */
    public JSONObject getSecurityConfiguration(RouterManager router){
        // OperationSocketStructure operation = securityConfigurationManager.getSecurityConfiguration();
        JSONObject operation = securityConfigurationManager.getSecurityConfiguration();

        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }
    public JSONObject setSecurityConfiguration(RouterManager router, String SSID, String password){
        // OperationSocketStructure operation = securityConfigurationManager.setSecurityConfiguration(SSID, password);
        JSONArray parameter = new JSONArray();
        parameter.add(0, SSID);
        parameter.add(1, password);
        JSONObject operation = securityConfigurationManager.setSecurityConfiguration(parameter);

        // ResultSocketStructure result = controllerSocketNetworkManager.operationExecute(operation);
        JSONObject result = controllerSocketNetworkManager.operationExecute(operation, router);

        System.out.println(getTime() + " " + ((JSONObject)result.get("header")).get("result"));

        return result;
    }
}
