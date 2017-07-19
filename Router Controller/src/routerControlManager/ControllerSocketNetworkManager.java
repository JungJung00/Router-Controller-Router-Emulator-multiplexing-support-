package routerControlManager;

import common.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;

/**
 * Created by Jeong Taegyun on 2017-04-19.
 */

public class ControllerSocketNetworkManager {
    // private String IP = "127.0.0.1";
    // private short PORT = 3000;

    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    // private static Socket serverSocket;

    // private boolean connected = false;

    private ErrorManager errorManager;

    public ControllerSocketNetworkManager(){
        inputStream = null;
        objectInputStream = null;
        outputStream = null;
        objectOutputStream = null;
        // serverSocket = null;

        errorManager = new ErrorManager();
    }

    private static class Singleton{
        private static final ControllerSocketNetworkManager instance = new ControllerSocketNetworkManager();
    }

    public static ControllerSocketNetworkManager getInstance(){
        return Singleton.instance;
    }

    public JSONObject operationExecute(JSONObject operation, RouterManager router){
        JSONObject result = null;

        // TODO : Multiplexing support

        // Send operation
        try{
            /* First, turn on the router */
//            if (serverSocket == null){
//                System.out.println("Router is off");
//                return errorManager.handleError(operation);
//            }
//
//            if (serverSocket.isClosed()){
//                serverSocket = new Socket(IP, PORT);
//            }
            if (!router.isConnected()){
                System.out.println("Router is off");
                return errorManager.handleError(operation);
            }

            if (router.isClosed()) {
                // System.out.println("You should never reached here. Because it means there are some exception handling leaks.\nAnyway process is ongoing.");
                router.connectRouter();
            }

            /* Get objectStreams to send socket(OperationSocket) */
            outputStream = router.getServerSocket().getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            /* Send operation socket to router emulator */
            sendOperation(operation);

            /* Get objectStreams to receive socket(ResultSocket) */
            inputStream = router.getServerSocket().getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);

            /* Receive result socket from router emulator */
            result = receiveResult();

            /* Close streams and sockets*/
            inputStream.close();
            outputStream.close();
            objectInputStream.close();
            objectOutputStream.close();

        } catch (IOException e){
                    e.printStackTrace();
        }

        return result;
    }

    // Send operation socket to router amulator
    public void sendOperation(JSONObject operation){
        try{
        objectOutputStream.reset();
        objectOutputStream.writeObject(operation);
        objectOutputStream.flush(); // Send object

    } catch (IOException e){
        e.printStackTrace();
    }
    }

    // Receive result socket from router emulator
    public JSONObject receiveResult(){
        JSONObject result = null;
        try{
            result = (JSONObject) objectInputStream.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return result;
    }

//    public boolean isConnected(){
//        return connected;
//    }
}
