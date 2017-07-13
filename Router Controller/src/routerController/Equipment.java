package routerController;

import common.ResultSocketStructure;
import org.json.simple.JSONObject;
import routerControlManager.RouterManager;

abstract class Equipment {
	abstract public JSONObject turnOnPower(RouterManager router);
	
	abstract public JSONObject turnOffPower(RouterManager router);
}
