/**
* Author: Daniel Yu
* 
* To create a type of Agent that will strategically avoid other agents and die when touched by one.
*/
package Lab03;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyAgent extends Agent {
    public boolean moved, alive;
    public int r;

    // The constructor, which calls the super class constructor and sets the radius
    // field.
    public EnemyAgent(double x0, double y0, int radius) {
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
            if (!moved) {
                g.setColor(new Color(0, 255, 0));
            } else {
                g.setColor(new Color(125, 255, 125));
            }
        } else {
            g.setColor(new Color(125, 125, 125));
        }
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }

    // for now, leave this blank.
    public void updateState(Landscape scape) {
        if (alive) {
            if (scape.getNeighbors(x, y, r).size() != 0) {
                if (scape.getNeighbors(x, y, 1).size() != 0) {
                    alive = false;
                } else {
                    double avgX = 0;
                    double avgY = 0;
                    for (Agent a : scape.getNeighbors(x, y, r)) {
                        avgX += a.getX();
                        avgY += a.getY();
                    }
                    avgX /= scape.getNeighbors(x, y, r).size();
                    avgY /= scape.getNeighbors(x, y, r).size();
                    double moveX = 0;
                    moveX = x + (-1 * (avgX - x));
                    if (moveX < 0) {
                        setX(0);
                    } else if (moveX > scape.width) {
                        setX(scape.width - 1);
                    } else {
                        setX(moveX);
                    }
                    double moveY = 0;
                    moveY = y + (-1 * (avgY - y));
                    if (moveY < 0) {
                        setY(0);
                    } else if (moveY > scape.height) {
                        setY(scape.height - 1);
                    } else {
                        setY(moveY);
                    }
                    moved = true;
                }
            } else {
                moved = false;
            }
        }
    }
}
