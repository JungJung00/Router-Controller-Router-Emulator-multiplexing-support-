package routerAmulator;

public class RouterAmulator {
	private RouterAmulatorHandler routerAmulatorHandler;

	/* Temp Configuration Information*/


	public RouterAmulator(){
	    routerAmulatorHandler = new RouterAmulatorHandler();
    }

    public void test(){
	    routerAmulatorHandler.run();
    }
}
