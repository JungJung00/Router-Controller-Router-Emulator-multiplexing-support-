package routerAmulator;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Created by Jeong Taegyun on 2017-07-17.
 */
public class ClientArrayListWrapper{
    ArrayList<SocketChannel> clientList;
    Selector reader;

    public ClientArrayListWrapper(Selector _reader){
        clientList = new ArrayList<>();
        reader = _reader;
    }

    public void add(SocketChannel client){
        clientList.add(client);
        // reader.select() returns value immediately to register reader to new client socket channel
        reader.wakeup();
    }

    public SocketChannel get(int index){
        return clientList.get(index);
    }

    public void remove(int index){
        clientList.remove(index);
    }

    public int size(){
        return clientList.size();
    }

    // Use this method to share selector which used in to check client is readable
    public void setReader(Selector _reader){
        _reader = reader;
    }
}
