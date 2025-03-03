package Lab02;
/**
* Author: Daniel Yu
* 
* To create a cell and store the data of its alive status.
*/

import java.util.ArrayList;

public class Cell {

    /**
     * The status of the Cell.
     */
    private boolean alive;
    private boolean zombie;

    /**
     * Constructs a dead cell.
     */
    public Cell() {
        alive = false;
        zombie = false;
    }

    /**
     * Constructs a cell with the specified status.
     * 
     * @param status a boolean to specify if the Cell is initially alive
     */
    public Cell(boolean status) {
        alive = status;
        zombie = false;
    }

    /**
     * Returns whether the cell is currently alive.
     * 
     * @return whether the cell is currently alive
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * Returns whether the cell is a zombie.
     * 
     * @return whether the cell is a zombie.
     */
    public boolean isZombie() {
        return zombie;
    }

    /**
     * Sets the current status of the cell to the specified status.
     * 
     * @param status a boolean to specify if the Cell is alive or dead
     */
    public void setAlive(boolean status) {
        alive = status;
    }

    /**
     * Updates the state of the Cell.
     * 
     * If this Cell is alive and if there are 2 or 3 alive neighbors,
     * this Cell stays alive. Otherwise, it dies.
     * 
     * If this Cell is dead and there are 3 alive neighbors,
     * this Cell comes back to life. Otherwise, it stays dead.
     * 
     * Zombies:
     * When a Cell dies, it has a 1% chance of becoming a zombie.
     * Zombies will live so long as there is at least alive neighbor.
     * Otherwise, it dies.
     * Alive Cells will become Zombies if there is a Zombie Neighbor.
     * 
     * @param neighbors An ArrayList of Cells
     */
    public void updateState(ArrayList<Cell> neighbors) {
        int aliveCount = 0;
        for (Cell a : neighbors) {
            if (a.alive == true) {
                aliveCount++;
            }
        }
        int zombieCount = 0;
        for (Cell a : neighbors) {
            if (a.zombie == true) {
                zombieCount++;
            }
        }
        if (alive == true) {
            if (zombie == true) {
                if (aliveCount > 0) {
                    alive = true;
                } else {
                    alive = false;
                    zombie = false;
                }
            } else if (zombieCount > 0) {
                alive = false;
                zombie = true;
            } else if ((aliveCount == 2 || aliveCount == 3)) {
                alive = true;
            } else {
                if (Math.random() < .01) {
                    alive = false;
                    zombie = true;
                } else {
                    alive = false;
                }

            }
        } else {
            if (alive == false) {
                if ((aliveCount == 3)) {
                    alive = true;
                } else {
                    alive = false;
                }
            }
        }
    }

    /**
     * Returns a String representation of this Cell.
     * 
     * @return 1 if this Cell is alive, otherwise 0.
     */
    public String toString() {
        if (alive) {
            return "1";
        } else {
            return "0";
        }
    }

    public static void main(String[] args) {
        Cell a = new Cell();
        ArrayList<Cell> neighbors = new ArrayList<>();
        neighbors.add(new Cell(true));
        neighbors.add(new Cell(true));
        neighbors.add(new Cell(true));
        System.out.println(a);
        a.updateState(neighbors);
        System.out.println(a);
        Cell b = new Cell(true);
        System.out.println(b);
        ArrayList<Cell> neighbors2 = new ArrayList<>();
        b.updateState(neighbors2);
        System.out.println(b);
    }
}
