package routerControlManager;

import common.DetailOperationCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by qwert on 2017-07-12.
 */
public class RouterManager {

    private Socket serverSocket;
    private String IP;
    private int PORT;

    private boolean connected = false;
    private boolean power = false;

    public boolean isConnected(){ return connected; }

    public boolean  isClosed(){ return serverSocket.isClosed(); }

    public boolean isPowered(){ return power; }

    public void setPower(int isPowered){ if(isPowered == 1) power = true; else power = false; }

    public String getIP() { return IP; }

    public int getPORT() { return PORT; }


    public Socket getServerSocket(){ return serverSocket; }

    public RouterManager(String _IP, int _PORT){
        IP = _IP;
        PORT = _PORT;
    }

    public boolean connectRouter(){
        try{
            serverSocket = new Socket(IP, PORT);    // Create connection to Router
        } catch (IOException e){
            e.printStackTrace();
        }

        if (serverSocket != null)
            connected = true;
        else
            connected = false;

        return connected;
    }

    public void disconnectRouter(){
        try{
            if (!serverSocket.isClosed())
                serverSocket.close();
            connected = false;
        } catch (IOException e){
            e.printStackTrace();
        }
        serverSocket = null;
    }

    public JSONObject onAPPower(){
        // OperationSocketStructure operation = new OperationSocketStructure(OperationCode.TURN_POWER, DetailOperationCode.TURN_ON_POWER);
        JSONArray parameter = new JSONArray();
        parameter.add(0, true);

        return JsonOperationManager.generateJsonOperation(DetailOperationCode.TURN_ON_POWER, parameter);
    }

    public JSONObject offAPPower(){
        // OperationSocketStructure operation = new OperationSocketStructure(OperationCode.TURN_POWER, DetailOperationCode.TURN_OFF_POWER);
        JSONArray parameter = new JSONArray();
        parameter.add(0, false);
        return JsonOperationManager.generateJsonOperation(DetailOperationCode.TURN_OFF_POWER, parameter);
    }
}
