package routerAmulator;

import common.OperationSocketStructure;
import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import static common.Now.getTime;


/**
 * Created by Jeong Taegyun on 2017-04-19.
 */

public class RouterAmulatorHandler extends Thread{
    private String IP = "127.0.0.1";
    private short PORT = 3000;

    private boolean power = false;

    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    private ServerSocket serverSocket;
    private Selector selector;
    private Socket clientSocket;

    private static ErrorHandler errorHandler;
    private static ConnectedDeviceHandler connectedDeviceHandler;
    private static SecurityConfigurationHandler securityConfigurationHandler;
    private static IPAllocateConfigurationHandler ipAllocateConfigurationHandler;
    private static PortforwardingConfigurationHandler portforwardingConfigurationHandler;
    private static DHCPConfigurationHandler dhcpConfigurationHandler;

    public RouterAmulatorHandler() throws IOException{
        inputStream = null;
        objectInputStream = null;
        outputStream = null;
        objectOutputStream = null;

        errorHandler = new ErrorHandler();
        connectedDeviceHandler = new ConnectedDeviceHandler();
        securityConfigurationHandler = new SecurityConfigurationHandler();
        ipAllocateConfigurationHandler = new IPAllocateConfigurationHandler();
        portforwardingConfigurationHandler = new PortforwardingConfigurationHandler();
        dhcpConfigurationHandler = new DHCPConfigurationHandler();

        selector = Selector.open();

        // Create server socket
        ServerSocketChannel channel = ServerSocketChannel.open();
        serverSocket = channel.socket();
    }

    @Override
    public void run(){
        // server sockect binding
        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println(getTime() + "Server is listening on " + PORT);

        } catch(IOException e){
            e.printStackTrace();
        }

        Socket clientSocket;

        // handle router controller's operation
        while (true){
            try{
                System.out.println(getTime() + "Waiting for connecting...");

                clientSocket = serverSocket.accept();
                System.out.println(getTime() + " " + clientSocket.getInetAddress() + " is connected.");

                // OperationSocketStructure operation;
                JSONObject operation;

                /* Get objectInputStream to receive object(OperationSocketStructure) from router controller */
                inputStream = clientSocket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);

                // operation = (OperationSocketStructure) objectInputStream.readObject();   // Receive operation socket
                operation = (JSONObject) objectInputStream.readObject();

                // ResultSocketStructure result;
                JSONObject result;

                switch (((JSONObject)operation.get("body")).get("operation").toString()){
                    case "GET_CONNECTED_DEVICE":
                        result = connectedDeviceHandler.handleConnectedDeviceOperation("GET_CONNECTED_DEVICE");
                        break;

                    case "GET_AP_SETTING":
                    case "SET_AP_SETTING":
                        result = securityConfigurationHandler.handleSecurityConfigurationOperation(operation);
                        break;

                    case "GET_PORTFORWARD":
                    case "ADD_PORTFORWARD":
                        result = portforwardingConfigurationHandler.handlePortforwardingConfigurationOperation(operation);
                        break;

                    case "GET_DHCP_SETTING":
                    case "ADD_DHCP_SETTING":
                        result = dhcpConfigurationHandler.handleDHCPConfigurationOperation(operation);
                        break;

                    case "SET_AP_POWER":
                    case "GET_AP_POWER":
                        result = handlePowerOperation(operation);
                        break;

                    default:
                        result = errorHandler.handleError(operation);
                }

                /* TODO : Save router configurations to txt file */

                /* Get objectOutputStream to send object(ResultSocketStructure) to router controller */
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);

                /* Send result socket to router controller */
                objectOutputStream.reset();
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();

                /* Close streams and sockets*/
                try{
                    inputStream.close();
                    outputStream.close();
                    objectInputStream.close();
                    objectOutputStream.close();

                    clientSocket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public JSONObject handlePowerOperation(JSONObject operation){
        // ResultSocketStructure result = null;
        JSONObject result = null;

        String _operation = ((JSONObject)operation.get("body")).get("operation").toString();
        JSONArray parameter = (JSONArray) ((JSONObject)operation.get("body")).get("subValues");

        switch (_operation){
            case "SET_AP_POWER":
                if ((boolean)parameter.get(0))
                    result = handleTurnOnPowerOperation(_operation);
                else
                    result = handleTurnOffPowerOperation(_operation);
                break;

            case "GET_AP_POWER":
                result = handleGetPowerOperation(_operation);
                break;
        }

        return result;
    }

    private JSONObject handleTurnOnPowerOperation(String code){
        // return new ResultSocketStructure("Turn on power");
        // TODO : Turn on the LED
        power = true;

        return JsonOperationManager.generateJsonResult(code, null, true);
    }

    private JSONObject handleTurnOffPowerOperation(String code){
        // return new ResultSocketStructure("Turn off power");
        // TODO : Turn off the LED
        power = false;

        return JsonOperationManager.generateJsonResult(code, null, true);
    }

    private JSONObject handleGetPowerOperation(String code){
        JSONArray parameter = new JSONArray();
        parameter.add(0, power);

        return JsonOperationManager.generateJsonResult(code, parameter, true);
    }
}
