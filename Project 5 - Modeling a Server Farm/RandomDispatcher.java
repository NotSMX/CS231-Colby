package Lab05;
/**
 * Author: Daniel Yu
 * 
 * picks a random Server
 */

public class RandomDispatcher extends JobDispatcher {

    public RandomDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        return super.arr.get((int) (Math.random() * arr.size()));
    }

}
