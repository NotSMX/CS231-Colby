/**
* Author: Daniel Yu
* 
* To create the living environment of cells through an x by y grid
*/
package Lab02;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        landscape = new Cell[rows][columns];
        initialChance = 0;
        reset();
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        initialChance = chance;
        landscape = new Cell[rows][columns];
        reset();
    }

    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */
    public void reset() {
        Cell[][] temp = new Cell[landscape.length][landscape[0].length];
        landscape = temp;
        for (int r = 0; r < landscape.length; r++) {
            for (int c = 0; c < landscape[0].length; c++) {
                landscape[r][c] = new Cell(true);
                if (Math.random() >= initialChance) {
                    landscape[r][c].setAlive(false);
                }
            }
        }
    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return landscape.length;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        return landscape[0].length;
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */
    public Cell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        String temp = "";
        for (Cell[] row : landscape) {
            for (Cell col : row) {
                temp += (col + " ");
            }
            temp += "\n";
        }
        return temp;
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        for (int r = row - 1; r < row + 2; r++) {
            for (int c = col - 1; c < col + 2; c++) {
                if (r > -1 && r < this.getRows() && c > -1 && c < this.getCols()) {
                    if (r != row || c != col) {
                        // System.out.println("added " + r + " " + c + this.landscape[r][c].getAlive());
                        neighbors.add(this.landscape[r][c]);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Advances the current Landscape by one step.
     */
    public void advance() {
        // create temp grid
        Cell[][] temp = new Cell[landscape.length][landscape[0].length];
        for (int r = 0; r < temp.length; r++) {
            for (int c = 0; c < temp[0].length; c++) {
                temp[r][c] = new Cell(landscape[r][c].getAlive());
                // System.out.print(r + " " + c + " " + ": " + temp[r][c] + " has " + aliveCount
                // + " alive neighbors, beocoming ");
                temp[r][c].updateState(this.getNeighbors(r, c));
                // System.out.println(temp[r][c]);
            }
        }
        landscape = temp;
    }

    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray. a
     * zombie Cell is drawn green.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                // System.out.print(x + " " + y + " " + getCell(x, y).getAlive() + " ");
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                if (getCell(x, y).isZombie()) {
                    g.setColor(Color.GREEN);
                }
                g.fillOval(y * scale, x * scale, scale, scale);
            }
            // System.out.println();
        }
    }

    public static void main(String[] args) {
    }
}