package routerAmulator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

import static common.Now.getTime;

/**
 * Created by Jeong Taegyun on 2017-07-16.
 */
public class ClientConnectionAccepter extends Thread{
    private int port;

    private ServerSocketChannel serverSocketChannel;
    private Selector acceptor;

    private ClientArrayListWrapper clientList;

    public ClientConnectionAccepter(ClientArrayListWrapper _clinetList, int _port){
        clientList = _clinetList;
        port = _port;

        // Initialize to be ready for accepting clients' connections
        try {
            acceptor = Selector.open();

            // Set non-blocking IO
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            // Resister selector(acceptor) to server channel to make clients' connection request event listener
            serverSocketChannel.register(acceptor, SelectionKey.OP_ACCEPT);

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(getTime() + "Server is listening on " + port);
    }

    @Override
    public void run() {
        try{
            while (true){
                int numKeys = acceptor.select();

                if (numKeys > 0){
                    Iterator iterator = acceptor.selectedKeys().iterator();

                    while (iterator.hasNext()){
                        SelectionKey key = (SelectionKey)iterator.next();
                        iterator.remove();

                        // SocketChannel client = serverSocketChannel.accept();
                        ServerSocketChannel readyChannel = (ServerSocketChannel) key.channel();
                        SocketChannel client = readyChannel.accept();

                        clientList.add(client);

                        System.out.println(getTime() + " " + client.socket().getInetAddress() + " is connected.");
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
