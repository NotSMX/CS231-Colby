package Lab05;
/**
 * Author: Daniel Yu
 * 
 * pick the Server in a round-robin process (so the first time pickServer() is
 * called, it returns the first, then the next time the second, etc.)
 */
public class RoundRobinDispatcher extends JobDispatcher {
    int select = -1;

    public RoundRobinDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        select++;
        return super.arr.get(select % super.arr.size());
    }
}
