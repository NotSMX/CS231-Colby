/**
* Author: Daniel Yu
* 
* To create a living environment of agents on an area w x h
*/

package Lab03;

import java.awt.Graphics;

public class Landscape {
    // a constructor that sets the width and height fields, and initializes the
    // agent list.
    int width, height;
    LinkedList<Agent> agents;

    public Landscape(int w, int h) {
        width = w;
        height = h;
        agents = new LinkedList<>();
    }

    // returns the height.
    public int getHeight() {
        return height;
    }

    // returns the width.
    public int getWidth() {
        return width;
    }

    // inserts an agent at the beginning of its list of agents.
    public void addAgent(Agent a) {
        agents.add(a);
    }

    // returns a String representing the Landscape. It can be as simple as
    // indicating the number of Agents on the Landscape.
    public String toString() {
        return agents.size() + "";

    }

    // returns a list of the Agents within radius distance of the location x0, y0.
    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
        LinkedList<Agent> neighbors = new LinkedList<>();
        for (int x = 0; x < agents.size(); x++) {
            // to not count itself as a neighbor
            if (agents.get(x).getX() == x0 && agents.get(x).getY() == y0) {
                continue;
            }

            // conditionals to be added to the list
            if (agents.get(x).getX() <= x0 + radius && agents.get(x).getY() <= y0 + radius
                    && agents.get(x).getX() >= x0 - radius && agents.get(x).getY() >= y0 - radius) {
                neighbors.add(agents.get(x));
            }
        }
        return neighbors;
    }

    // Calls the draw method of all the agents on the Landscape.
    public void draw(Graphics g) {
        for (Agent a : agents) {
            a.draw(g);
        }
    }

    public void updateAgents() {
        for (Agent a : agents) {
            a.updateState(this);
        }
    }
}
