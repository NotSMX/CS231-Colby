package Lab05;

/**
* Author: Daniel Yu
* 
* picks the Server with the least remaining processing time in its queue, with ties handled arbitrarily.
*/

import java.util.ArrayList;
import java.util.Collections;

public class LeastWorkDispatcher extends JobDispatcher {
    public LeastWorkDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        int minIndex = 0;
        ArrayList<Integer> eligibleServers = new ArrayList<>();
        for (int i = 0; i < super.arr.size(); i++) {
            if (super.arr.get(i).remainingWorkInQueue() < super.arr.get(minIndex).remainingWorkInQueue()) {
                minIndex = i;
                eligibleServers.clear();
                eligibleServers.add(i);
            } else if (super.arr.get(i).remainingWorkInQueue() == super.arr.get(minIndex).remainingWorkInQueue()) {
                // Handle ties arbitrarily by adding to the eligible servers list
                eligibleServers.add(i);
            }
        }
        Collections.shuffle(eligibleServers);
        return super.arr.get(eligibleServers.get(0));
    }
}
