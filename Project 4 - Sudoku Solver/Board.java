package Lab04;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Author: Daniel Yu
 * 
 * To create a sudoku board that stores numbers on a 9 x 9 square grid
 */

public class Board {
    private Cell[][] board;
    public static final int SIZE = 9;
    public boolean finished;

    // default
    public Board() {
        // initialize all Cells as 0
        board = new Cell[Board.SIZE][Board.SIZE];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Cell(row, col, 0);
            }
        }
    }

    // make a board with a random amount of boxes with starting values
    public Board(int lockedCount) {
        int count = 0;
        Random ran = new Random();
        this.board = new Cell[SIZE][SIZE];
        // initialize all Cells as 0
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                this.board[x][y] = new Cell(x, y, 0);
            }
        }
        // selecting random values to have random but valid starting values
        while (count < lockedCount) {

            // System.out.println(count);
            // pick a random cell
            Cell cell = this.board[ran.nextInt(SIZE)][ran.nextInt(SIZE)];

            // pick a random value
            int val = ran.nextInt(9) + 1;

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

    // import from file
    public Board(String filename) {
        board = new Cell[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                this.board[x][y] = new Cell(x, y, 0);
            }
        }
        read(filename);
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
    public Cell get(int row, int col) {
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

    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the
            // FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine
            // method of your BufferedReader object.
            String line = br.readLine();
            // start a while loop that loops while line isn't null
            while (line != null) {
                // print line
                System.out.println(line);
                // assign to an array of Strings the result of splitting the line up by spaces
                // (line.split("[ ]+"))
                String[] arr = line.split("[ ]+");
                // let's see what this array holds:
                System.out.println("the first item in arr: " + arr[0] + ", the second item in arr: " + arr[1]);
                // print the size of the String array (you can use .length)
                System.out.println(arr.length);
                // use the line to set various Cells of this Board accordingly
                // assign to line the result of calling the readLine method of your
                // BufferedReader object.
                line = br.readLine();
            }
            // call the close method of the BufferedReader
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public String toString() {
        String temp = "";
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                temp += board[row][col];
                // to seperate 3x3s from each other horizontally
                if (col == 2 || col == 5) {
                    temp += "  ";
                }
            }
            // to seperate 3x3s from each other vertically
            if (row == 2 || row == 5) {
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
        // 1-9
        if (value > 9 || value < 1) {
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
        // check 3x3
        int row3 = row / 3;
        int col3 = col / 3;
        for (int r = row3 * 3; r < row3 * 3 + 3; r++) {
            for (int c = col3 * 3; c < col3 * 3 + 3; c++) {
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
        g.drawLine(3 * scale - 7, 0, 3 * scale - 7, 9 * scale);
        g.drawLine(6 * scale - 7, 0, 6 * scale - 7, 9 * scale);
        g.drawLine(0, 3 * scale - 7, 9 * scale, 3 * scale - 7);
        g.drawLine(0, 6 * scale - 7, 9 * scale, 6 * scale - 7);

        // nums
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
            }
        }
        if (finished) {
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
            }
        }
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);
        // b.read("board1.txt");
        // b.read("board2.txt");

        // getRows + getCols()
        System.out.println(b);
        System.out.println(b.getRows());
        System.out.println(b.getCols());
        // set(int r, int c, int value)
        b.set(1, 3, 8);
        System.out.println(b);
        // get(int r, int c) + value(int r, int c)
        System.out.println(b.get(1, 3));
        System.out.println(b.value(1, 3));
        // set(int r, int c, int value, boolean locked) + isLocked(int r, int c) +
        // numLocked()
        System.out.println(b.numLocked());
        System.out.println(b.isLocked(0, 0));
        b.set(0, 0, 2, true);
        System.out.println(b);
        System.out.println(b.numLocked());
        System.out.println(b.isLocked(0, 0));

    }
}
