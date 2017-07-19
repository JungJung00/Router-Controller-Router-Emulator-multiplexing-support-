package routerAmulator;

import java.io.IOException;
import java.nio.channels.Selector;

public class RouterAmulator {
	private ClientConnectionAccepter clientConnectionAccepter;
	private RouterAmulatorHandler routerAmulatorHandler;

	private ClientArrayListWrapper clientList;
	private Selector reader;

	private static final int PORT = 3000;

	/* Temp Configuration Information*/


	public RouterAmulator(){
		try{
			reader = Selector.open();
		} catch (IOException e){
			e.printStackTrace();
		}

		clientList = new ClientArrayListWrapper(reader);

		clientConnectionAccepter = new ClientConnectionAccepter(clientList, PORT);
	    // routerAmulatorHandler = new RouterAmulatorHandler(clientList);
		routerAmulatorHandler = new RouterAmulatorHandler();
    }

    public void start(){
		// clientConnectionAccepter.start();
		routerAmulatorHandler.start();
    }
}
