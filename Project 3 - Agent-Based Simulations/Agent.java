/**
* Author: Daniel Yu
* 
* An abstract Agent class meant to be a building block for SocialAgent, AntiSocialAgent, etc.
*/
package Lab03;

import java.awt.Graphics;

public abstract class Agent {
    public double x, y;

    // a constructor that sets the position.
    public Agent(double x0, double y0) {
        x = x0;
        y = y0;
    }

    // returns the x position.
    public double getX() {
        return x;
    }

    // returns the y position.
    public double getY() {
        return y;
    }

    // sets the x position.
    public void setX(double newX) {
        x = newX;
    }

    // sets the y position.
    public void setY(double newY) {
        y = newY;
    }

    // returns a String containing the x and y positions, e.g. "(3.024, 4.245)".
    public String toString() {
        return "(" + x + ", " + y + ")";

    }

    // does nothing, for now.
public abstract void updateState( Landscape scape );

    // does nothing, for now.
public abstract void draw(Graphics g);
}
