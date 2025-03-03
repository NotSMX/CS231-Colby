package Lab04;
import java.util.Stack;

/**
 * Author: Daniel Yu
 * 
 * To create an algorithm the solves a sudoku board, given a perfect square as a size.
 */

public class SudokuFlex {
    private BoardFlex b;
    private LandscapeDisplayFlex ld;

    //with just size
    public SudokuFlex(int size) {
        b = new BoardFlex(size);
        ld = new LandscapeDisplayFlex(b);
    }

    //with size and number of initial numbers
    public SudokuFlex(int size, int num) {
        b = new BoardFlex(size, num);
        ld = new LandscapeDisplayFlex(b);
    }

    // backtracking
    public int findNextValue(CellFlex c) {
        for (int a = c.getValue() + 1; a < b.SIZE + 1; a++) {
            if (b.validValue(c.getRow(), c.getCol(), a)) {
                return a;
            }
        }
        return 0;
    }

    // find the next cell to go to and find an appropriate value for it
    public CellFlex findNextCell() {
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
        Stack<CellFlex> s = new Stack<CellFlex>();
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
            CellFlex next = this.findNextCell();
            while (next == null && !s.isEmpty()) {
                CellFlex temp = s.pop();
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
        SudokuFlex s = new SudokuFlex(1);
        System.out.println(s.b);
        s.solve(0);
        SudokuFlex s2 = new SudokuFlex(4);
        System.out.println(s.b);
        s2.solve(0);
        SudokuFlex s3 = new SudokuFlex(9);
        System.out.println(s.b);
        s3.solve(0);
        SudokuFlex s4 = new SudokuFlex(16);
        System.out.println(s.b);
        s4.solve(0);
        SudokuFlex s5 = new SudokuFlex(25);
        System.out.println(s.b);
        s5.solve(0);

    }

}
