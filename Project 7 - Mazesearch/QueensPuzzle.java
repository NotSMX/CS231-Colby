import Homework.ArrayList;

public class QueensPuzzle {

    static class Coord {
        final int row, col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "(" + row + ", " + col + ")";
        }

        public boolean equals(Object o) {
            if (!(o instanceof Coord)) {
                return false;
            }

            Coord otherCoord = (Coord) o;
            return row == otherCoord.row && col == otherCoord.col;
        }
    }

    /**
     * This method attempts to solve the Queen's Puzzle Game with {@code n} queens
     * on a board with {@code rows} rows and {@code cols} columns. It does so via
     * depth first search.
     * 
     * @param n    the number of queens to put on the board
     * @param rows the number of rows in the board
     * @param cols the number of cols in the board
     * @return an ArrayList of Coords reflecting the location of the queens in the
     *         solution of the problem specified by the parameters, or {@code null}
     *         if no such solution exists.
     */
    static ArrayList<Coord> solve(int n, int rows, int cols) {
        ArrayList<Coord> stack = new ArrayList<>();
        Coord last = new Coord(0, -1);
        while (stack.size() < n) {
            // try a new Coord!
            Coord newCoord = findNextCoord(stack, last, rows, cols);

            while (newCoord == null && stack.size() > 0) {
                last = stack.pop();

                newCoord = findNextCoord(stack, last, rows, cols);
            }

            if (newCoord == null) {
                return null; // no solution possible
            } else {
                stack.push(newCoord);
                last = newCoord;
            }
        }
        return stack;
    }

    static ArrayList<ArrayList<Coord>> solveAll(int n, int rows, int cols) {
        ArrayList<ArrayList<Coord>> solutions = new ArrayList<>();
        ArrayList<Coord> stack = new ArrayList<>();
        Coord last = new Coord(0, -1);
        Coord newCoord = findNextCoord(stack, last, rows, cols);
        stack.push(newCoord);
        last = newCoord;
        while (stack.size() != 0) {
            while (stack.size() < n) {
                // try a new Coord!
                newCoord = findNextCoord(stack, last, rows, cols);

                while (newCoord == null && stack.size() > 0) {
                    last = stack.pop();
                    newCoord = findNextCoord(stack, last, rows, cols);
                }

                if (newCoord == null) {
                    break;
                    // return null; // no solution possible
                } else {
                    stack.push(newCoord);
                    last = newCoord;
                }
            }
            if (stack.size() == n) {
                ArrayList<Coord> temp = new ArrayList<>();
                for (Coord a : stack) {
                    temp.push(a);
                }
                solutions.push(temp);
            }

            if (stack.size() != 0) {
                last = stack.pop();
                newCoord = findNextCoord(stack, last, rows, cols);
            }
            while (newCoord == null && stack.size() > 0) {
                last = stack.pop();
                newCoord = findNextCoord(stack, last, rows, cols);
            }

        }
        return solutions;
    }

    /**
     * Tests whether the given Coord {@code newCoord} can be placed on a board where
     * queens have already been placed at the locations specified in {@code coords}.
     * 
     * @param coords   the current location of a collection of queens
     * @param newCoord the new Coord to test
     * @param rows     the number of rows in the board
     * @param cols     the number of columns in the board
     * @return
     */
    private static boolean isValid(ArrayList<Coord> coords, Coord newCoord, int rows, int cols) {
        // first, test if any Coord in the same row as newCoord is already in coords
        for (int c = 0; c < cols; c++) {
            Coord testCoord = new Coord(newCoord.row, c);
            if (coords.contains(testCoord)) {
                return false;
            }
        }

        // first, test if any Coord in the same column as newCoord is already in coords
        for (int r = 0; r < rows; r++) {
            Coord testCoord = new Coord(r, newCoord.col);
            if (coords.contains(testCoord)) {
                return false;
            }
        }

        int[][] directions = new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
        for (int[] direction : directions) {
            Coord curCoord = new Coord(newCoord.row, newCoord.col);
            while (curCoord.row >= 0 && curCoord.col >= 0 && curCoord.row < rows && curCoord.col < cols) {
                if (coords.contains(curCoord)) {
                    return false;
                }

                curCoord = new Coord(curCoord.row + direction[0], curCoord.col + direction[1]);
            }
        }

        return true;
    }

    /**
     * Finds a valid Coord that a queen can be placed at in the board where queens
     * already exist at the locations specified in {@code coords}, going row by row
     * and column by column starting from the location specified by {@code last}.
     * 
     * @param coords the current location of a collection of queens
     * @param last   a coordinate to begin iterating from
     * @param rows   the number of rows in the board
     * @param cols   the number of columns in the board
     * @return a valid Coord that a new queen can be placed in the board.
     */
    private static Coord findNextCoord(ArrayList<Coord> coords, Coord last, int rows, int cols) {
        // first try every Coord in the row belonging to last, starting from 1 column
        // greater
        for (int c = last.col + 1; c < cols; c++) {
            Coord newCoord = new Coord(last.row, c);

            if (isValid(coords, newCoord, rows, cols)) {
                return newCoord;
            }
        }

        // now we try all remaining rows
        for (int r = last.row + 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Coord newCoord = new Coord(r, c);

                if (isValid(coords, newCoord, rows, cols)) {
                    return newCoord;
                }
            }
        }

        // if we reach here, couldn't find a valid coord to add
        return null;
    }

    public static void main(String[] args) {
        // for (int n = 1; n <= 14; n++) {
        // System.out.println(n + ": " + solve(n, n, n));
        // }

        for (int n = 1; n <= 8; n++) {
            System.out.println(n + "\n=======\n");
            for (ArrayList<Coord> solution : solveAll(n, n, n)) {
                System.out.println(solution);
            }
            System.out.println();
        }
    }
}