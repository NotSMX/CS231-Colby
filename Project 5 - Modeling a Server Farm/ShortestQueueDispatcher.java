package Lab05;

/**
 * Author: Daniel Yu
 * 
 * picks whichever Server has the smallest-sized queue (the server currently assigned the fewest jobs), with ties handled arbitrarily.
 */

import java.util.ArrayList;
import java.util.Collections;

public class ShortestQueueDispatcher extends JobDispatcher {
    int select = -1;

    public ShortestQueueDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        int minIndex = 0;
        ArrayList<Integer> eligibleServers = new ArrayList<>();
        for (int i = 0; i < super.arr.size(); i++) {
            if (super.arr.get(i).size() < super.arr.get(minIndex).size()) {
                minIndex = i;
                eligibleServers.clear();
                eligibleServers.add(i);
            } else if (super.arr.get(i).size() == super.arr.get(minIndex).size()) {
                // Handle ties arbitrarily by adding to the eligible servers list
                eligibleServers.add(i);
            }
        }
        Collections.shuffle(eligibleServers);
        return super.arr.get(eligibleServers.get(0));
    }
}
