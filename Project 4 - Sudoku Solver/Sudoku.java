package Lab04;
import java.util.Stack;

/**
 * Author: Daniel Yu
 * 
 * To create an algorithm the solves a sudoku board
 */

public class Sudoku {
    private Board b;
    private LandscapeDisplay ld;

    // default constructor
    public Sudoku() {
        b = new Board(8);
        ld = new LandscapeDisplay(b);
    }

    // constructor with initial values
    public Sudoku(int num) {
        b = new Board(num);
        ld = new LandscapeDisplay(b);
    }

    // backtracking
    public int findNextValue(Cell c) {
        for (int a = c.getValue() + 1; a < 10; a++) {
            if (b.validValue(c.getRow(), c.getCol(), a)) {
                return a;
            }
        }
        return 0;
    }

    // find the next cell to go to and find an appropriate value for it
    public Cell findNextCell() {
        for (int row = 0; row < b.getRows(); row++) {
            for (int col = 0; col < b.getCols(); col++) {
                if (b.get(row, col).getValue() == 0) {
                    if (findNextValue((b.get(row, col))) != 0) {
                        b.get(row, col).setValue(findNextValue((b.get(row, col))));
                        return b.get(row, col);
                    }
                    return null;
                }
            }
        }
        return null;
    }

    //uses a stack to keep track of the solution and allow backtracking when it gets stuck
    public boolean solve(int delay) throws InterruptedException {
        // Allocate a stack, initially empty
        Stack<Cell> s = new Stack<Cell>();
        int unspecified = 0;
        // count unspecified cells
        for (int row = 0; row < b.getRows(); row++) {
            for (int col = 0; col < b.getCols(); col++) {
                if (b.isLocked(row, col) == false) {
                    unspecified++;
                }
            }
        }
        // while the stack size is less than the number of unspecified cells
        while (s.size() < unspecified) {
            if (delay > 0)
                Thread.sleep(delay);
            if (ld != null)
                ld.repaint();
            Cell next = this.findNextCell();
            while (next == null && !s.isEmpty()) {
                Cell temp = s.pop();
                temp.setValue(findNextValue(temp));
                if (temp.getValue() != 0) {
                    next = temp;
                }
            }
            if (next == null) {
                b.finished = true;
                return false;
            } else {
                s.push(next);
            }
        }
        b.finished = true;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        // Sudoku s = new Sudoku(0);
        // System.out.println(s.b);
        // s.solve(10);
        // // System.out.println(s.b);

        // Sudoku s2 = new Sudoku(10);
        // System.out.println(s2.b);
        // s2.solve(10);
        // // System.out.println(s2.b);

        for (int a = 0; a < 5; a++) {
            // Sudoku ten = new Sudoku(10);
            // ten.solve(0);
            // Sudoku twenty = new Sudoku(20);
            // twenty.solve(0);
            // Sudoku thirty = new Sudoku(30);
            // thirty.solve(0);
            Sudoku fourty = new Sudoku(40);
            fourty.solve(0);
        }
    }

}
