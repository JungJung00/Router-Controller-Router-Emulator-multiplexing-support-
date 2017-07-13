package router;


import java.util.ArrayList;

/**
 * Created by qwert on 2017-07-13.
 */
public class ControllableRouterList {
    private ArrayList<Integer> controllableRouterLists;

    public ControllableRouterList(){
        controllableRouterLists = new ArrayList<>();

        for (int i = 0; i < 19; i++){
            int idential = 30100 +  (i + 1);
            controllableRouterLists.add(idential);
        }
    }

    public  ArrayList getControllableRouterLists(){ return controllableRouterLists; }
}
