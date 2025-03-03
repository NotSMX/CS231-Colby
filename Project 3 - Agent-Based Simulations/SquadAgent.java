/**
* Author: Daniel Yu
* 
* To create a type of Agent that will deploy other agents as soon as an agent enters its radius.
*/
package Lab03;

import java.awt.Color;
import java.awt.Graphics;

public class SquadAgent extends Agent {
    public boolean alive;
    public int r;

    // The constructor, which calls the super class constructor and sets the radius
    // field.
    public SquadAgent(double x0, double y0, int radius) {
        super(x0, y0);
        alive = true;
        // remainder of constructor code
        r = radius;
    }

    // sets the cell's radius of sensitivity to the value of radius.
    public void setRadius(int radius) {
        r = radius;
    }

    // returns the cell's radius of sensitivity.
    public int getRadius() {
        return r;
    }

    /*
     * draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's
     * location. If the agent moved during the last updateState, it is drawn with a
     * lighter shade of green, otherwise a darker shade of green.
     */
    public void draw(Graphics g) {
        if (alive) {
            g.setColor(new Color(255, 0, 255));
        } else {
            g.setColor(new Color(125, 125, 125));
        }
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }

    // for now, leave this blank.
    public void updateState(Landscape scape) {
        if (alive) {
            if (scape.getNeighbors(x, y, r).size() != 0) {
                scape.addAgent(new AntiSocialAgent(x + 15, y + 15, 25));
                scape.addAgent(new AntiSocialAgent(x - 15, y + 15, 25));
                scape.addAgent(new AntiSocialAgent(x + 15, y - 15, 25));
                scape.addAgent(new AntiSocialAgent(x - 15, y - 15, 25));
                alive = false;
            }
        }
    }
}
