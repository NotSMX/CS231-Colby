package Lab04;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Author: Daniel Yu
 * 
 * To create a sudoku board that stores numbers on a a x a square grid
 */

public class BoardFlex {
    private CellFlex[][] board;
    public int SIZE;
    public boolean finished;

    // default
    public BoardFlex(int size) {
        SIZE = size;
        // initialize all Cells as 0
        board = new CellFlex[SIZE][SIZE];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new CellFlex(row, col, 0);
            }
        }
    }

    // make a board with a random amount of boxes with starting values
    public BoardFlex(int size, int lockedCount) {
        int count = 0;
        SIZE = size;
        Random ran = new Random();
        this.board = new CellFlex[SIZE][SIZE];
        // initialize all Cells as 0
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                this.board[x][y] = new CellFlex(x, y, 0);
            }
        }
        // selecting random values to have random but valid starting values
        while (count < lockedCount) {

            // System.out.println(count);
            // pick a random cell
            CellFlex cell = this.board[ran.nextInt(SIZE)][ran.nextInt(SIZE)];

            // pick a random value
            int val = ran.nextInt(SIZE) + 1;

            // if this cell doesnt already have a value and if this value is valid for it
            // currently
            if (cell.getValue() == 0 && validValue(cell.getRow(), cell.getCol(), val)) {
                // give it this value
                cell.setValue(val);
                cell.setLocked(true);

                // to increase count
                count++;
            }
        }
    }

    // - returns the number of columns
    public int getCols() {
        return board[0].length;
    }

    // - returns the number of rows
    public int getRows() {
        return board.length;
    }

    // - returns whether the Cell at r, c, is locked.
    public boolean isLocked(int r, int c) {
        return get(r, c).isLocked();
    }

    // - returns the number of locked Cells on the board.
    public int numLocked() {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (isLocked(row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    // - returns the value at Cell r, c.
    public int value(int r, int c) {
        return board[r][c].getValue();
    }

    // - sets the value and locked fields of the Cell at r, c.
    public void set(int r, int c, int value, boolean locked) {
        board[r][c].setValue(value);
        board[r][c].setLocked(locked);
    }

    // : this returns the Cell at the given row and col
    public CellFlex get(int row, int col) {
        return board[row][col];
    }

    // : this sets the Cell at the given row and col to the given value
    public void set(int row, int col, int value) {
        board[row][col].setValue(value);
    }

    // : this sets whether the Cell at the given row and col is locked
    public void set(int row, int col, boolean locked) {
        board[row][col].setLocked(locked);
    }

    public String toString() {
        String temp = "";
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                temp += board[row][col];
                // to seperate perfect squares from each other horizontally
                if ((col + 1) % Math.sqrt(SIZE) == 0) {
                    temp += "  ";
                }
            }
            // to seperate perfect squares from each other vertically
            if ((row + 1) % Math.sqrt(SIZE) == 0) {
                temp += "\n";
            }
            temp += "\n";
        }
        return temp;
    }

    // tests if the given value is a valid value at the given row and column of the
    // board. It should make sure the value is unique in its row, in its column, and
    // in its local 3x3 square.
    public boolean validValue(int row, int col, int value) {
        // 1-n
        if (value > SIZE || value < 1) {
            return false;
        }
        // System.out.println("between 1-9!");
        // check row
        for (int c = 0; c < board[0].length; c++) {
            if (board[row][c].getValue() == value) {
                if (!(c == col)) {
                    return false;
                }
            }
        }
        // System.out.println("between 1-9 in row!");
        // check col
        for (int r = 0; r < board.length; r++) {
            if (board[r][col].getValue() == value) {
                if (!(r == row)) {
                    return false;
                }
            }
        }
        // System.out.println("between 1-9 in col!");
        // check nxn
        int rown = row / (int) Math.sqrt(SIZE);
        int coln = col / (int) Math.sqrt(SIZE);
        for (int r = rown * (int) Math.sqrt(SIZE); r < (rown * (int) Math.sqrt(SIZE) + (int) Math.sqrt(SIZE)); r++) {
            for (int c = coln * (int) Math.sqrt(SIZE); c < (coln * (int) Math.sqrt(SIZE)
                    + (int) Math.sqrt(SIZE)); c++) {
                if (board[r][c].getValue() == value) {
                    if (!(r == row && c == col)) {
                        return false;
                    }
                }
            }
        }
        // System.out.println("wahoo");
        return true;
    }

    public boolean validSolution() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // System.out.println(validValue(row, col, board[row][col].getValue()));
                if (validValue(row, col, board[row][col].getValue()) == false) {
                    return false;
                }
            }
        }
        return true;
    }

     //loop over the board and call the draw method of each Cell, with lines seperating each individual square
    public void draw(Graphics g, int scale) {
        // lines
        for (int horizontal = 0; horizontal < Math.sqrt(SIZE); horizontal++) {
            g.drawLine((int) (Math.sqrt(SIZE) * (horizontal + 1) * scale - 7), 0,
                    (int) (Math.sqrt(SIZE) * (horizontal + 1) * scale - 7), SIZE * scale);
        }

        for (int vertical = 0; vertical < Math.sqrt(SIZE); vertical++) {
            g.drawLine(0, (int) (Math.sqrt(SIZE) * (vertical + 1) * scale - 7), SIZE * scale,
                    (int) (Math.sqrt(SIZE) * (vertical + 1) * scale - 7));
        }

        // nums
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
            }
        }
        if (finished) {
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * (int) (Math.sqrt(SIZE)),
                        scale * (int) (SIZE + 1) + 10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * (int) (Math.sqrt(SIZE)),
                        scale * (int) (SIZE + 1) + 10);
            }
        }
    }

    public static void main(String[] args) {
        BoardFlex b = new BoardFlex(16);
        System.out.println(b);
        // b.read("board1.txt");
        // b.read("board2.txt");

    }
}
