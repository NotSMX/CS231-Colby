package Lab04;
/**
 * Author: Daniel Yu
 * 
 * Testing Board methods, for all possible situations
 */

public class BoardTests {
    public static void main(String[] args) {
        Board board = new Board();
        assert board.read("board1.txt") != false : "Error in Board:read()";

        // initial board
        System.out.println(board);

        // validValue()

        {
            // case 1: value is above 9
            {
                assert board.validValue(0, 0, 10) == false : "Error in Board:validValue():value > 10";
            }
            // case 2: value is below 1
            {
                assert board.validValue(0, 0, 0) == false : "Error in Board:validValue():value < 1";
            }
            // case 3: value is already in the row
            {
                for (int r = 0; r < board.getRows(); r++) {
                    for (int c = 1; c < board.getCols(); c++) {
                        // this for loop checks for all possible locations where a value can be in the
                        // same row as itself, each row, and each space in a column.
                        board.set(r, c, 1);
                        // System.out.println(board);
                        assert board.validValue(r, 0, 1) == false : "Error in Board:validValue():Row";
                        board.set(r, c, 0);
                    }
                }

            }
            // case 4: value is already in the col
            {
                for (int c = 0; c < board.getCols(); c++) {
                    for (int r = 1; r < board.getRows(); r++) {
                        // this for loop checks for all possible locations where a value can be in the
                        // same row as itself, each column, and each space in a row.
                        board.set(r, c, 1);
                        // System.out.println(board);
                        assert board.validValue(0, c, 1) == false : "Error in Board:validValue():Col";
                        board.set(r, c, 0);
                    }
                }

            }
            // case 5: value is already in the 3x3
            {
                // 3 rows of 3x3
                for (int row = 0; row < 3; row++) {
                    // 3 cols of 3x3
                    for (int col = 0; col < 3; col++) {
                        // set the first instance to 1
                        board.set(row * 3, col * 3, 1);
                        // check the other 8 entries within the 3x3
                        for (int r = row * 3; r < row * 3 + 3; r++) {
                            for (int c = col * 3; c < col * 3 + 3; c++) {
                                // to make sure it isnt checking the same entry as the temp value is on
                                if (!(r == row * 3 && c == col * 3)) {
                                    assert board.validValue(r, c, 1) == false : "Error in Board:validValue():3x3";
                                }
                            }
                        }
                        board.set(row * 3, col * 3, 0);
                    }
                }
            }
        }

        // validSolution()
        {
            // case 1: value at the farthest point of the grid is invalid
            board.set(8, 8, 10);
            assert board.validSolution() == false : "Error in Board:validSolution";
        }

    }
}
