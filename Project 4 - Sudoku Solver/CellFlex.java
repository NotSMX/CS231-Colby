package Lab04;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Author: Daniel Yu
 * 
 * To create a cell and store the data of its row, col, value, and lock status.
 */

public class CellFlex {
    int row, col, val;
    boolean lock;

    // - initialize all values to 0 or false;
    public CellFlex() {
        row = 0;
        col = 0;
        val = 0;
        lock = false;

    }

    // - initialize the row, column, and value fields to the given parameter values.
    // Set the locked field to false;
    public CellFlex(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.val = value;

    }

    // - initialize all of the Cell fields given the parameters.
    public CellFlex(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.val = value;
        lock = locked;

    }

    // - return the Cell's row index.
    public int getRow() {
        return row;
    }

    // - return the Cell's column index.
    public int getCol() {
        return col;
    }

    // - return the Cell's value.
    public int getValue() {
        return val;
    }

    // - set the Cell's value.
    public void setValue(int newval) {
        val = newval;
    }

    // - return the value of the locked field.
    public boolean isLocked() {
        return lock;
    }

    // - set the Cell's locked field to the new value.
    public void setLocked(boolean lock) {
        this.lock = lock;
    }

    public String toString() {
        return val + " ";
    }

     //draw the cell number
    public void draw(Graphics g, int x, int y, int scale) {
        String toDraw = "" + getValue();
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawString(toDraw, x, y);
    }

    public static void main(String[] args) {

    }
}
