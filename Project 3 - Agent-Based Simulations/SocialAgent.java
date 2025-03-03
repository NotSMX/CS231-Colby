/**
* Author: Daniel Yu
* 
* To create a type of Agent that will not stop moving until there are at least 4 other agents within its radius.
*/
package Lab03;

import java.awt.Color;
import java.awt.Graphics;

public class SocialAgent extends Agent {
    public boolean moved;
    public int r;

    public SocialAgent(double x0, double y0, int radius) {
        super(x0, y0);
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

    // draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's
    // location. If the agent moved during the last updateState, it is drawn with a
    // lighter shade of blue, otherwise a darker shade of blue.
    public void draw(Graphics g) {
        if (!moved)
            g.setColor(new Color(0, 0, 255));
        else
            g.setColor(new Color(125, 125, 255));

        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }

    // for now, leave this blank.
    public void updateState(Landscape scape) {
        if (scape.getNeighbors(x, y, r).size() < 4) {
            double moveX = 0;
            moveX = x + (int) (Math.random() * 21) - 10;
            if (moveX < 0) {
                setX(0);
            } else if (moveX > scape.width) {
                setX(scape.width - 1);
            } else {
                setX(moveX);
            }
            double moveY = 0;
            moveY = y + (int) (Math.random() * 21) - 10;
            if (moveY < 0) {
                setY(0);
            } else if (moveY > scape.height) {
                setY(scape.height - 1);
            } else {
                setY(moveY);
            }
            moved = true;
        } else {
            moved = false;
        }
    }

}
