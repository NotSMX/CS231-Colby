package Lab05;

import java.util.Random;

public class MatrixSearch {

    /**
     * Returns {@code true} if the given target is within the given matrix;
     * otherwise returns {@code false}.
     * 
     * @param matrix the matrix to search within
     * @param target the value to search for
     * @return {@code true} if the given target is within the given matrix;
     *         otherwise returns {@code false}.
     */
    public static boolean search(int[][] matrix, int target) {
        return search(matrix, target, 0, matrix.length, 0, matrix[0].length);
    }

    /**
     * Returns {@code true} if the given target is within the given bounds of the
     * given matrix; otherwise returns {@code false}.
     * 
     * @param matrix   the matrix to search within
     * @param target   the value to search for
     * @param startRow the starting row to search from
     * @param endRow   the first row after {@code startRow} to not include in the
     *                 search
     * @param startCol the starting column to search from
     * @param endCol   the first column after {@code startCol} to not include in the
     *                 search
     * @return {@code true} if the given target is within the given bounds of the
     *         given matrix; otherwise returns {@code false}.
     */
    private static boolean search(int[][] matrix, int target, int startRow, int endRow, int startCol, int endCol) {
        for (int[] row : matrix)
            for (int entry : row)
                if (entry == target)
                    return true;
        return false;
    }

    /**
     * Returns {@code true} if the given target is an entry of the given sorted
     * matrix; otherwise, returns {@code false}.
     * 
     * This method assumes that {@code sortedMatrix} is a square matrix; that is,
     * the number of rows is equal to the number of columns.
     * 
     * @param sortedMatrix the sorted matrix to search within
     * @param target       the value to find
     * @return {@code true} if the given target is an entry of the given sorted
     *         matrix; otherwise, returns {@code false}.
     */
    public static boolean binarySearch(int[][] sortedMatrix, int target) {
        return binarySearch(sortedMatrix, target, 0, sortedMatrix.length, 0, sortedMatrix[0].length);
    }

    /**
     * Returns {@code true} if the given target is an entry of the given sorted
     * matrix within the bounds specified by the given rows and columns.
     * 
     * This method assumes that {@code sortedMatrix} is a square matrix; that is,
     * the number of rows is equal to the number of columns.
     * 
     * Specifically, this method searches for the target only in the rows
     * {@code startRow}, {@code startRow + 1}, {@code startRow + 2}, ...,
     * {@code endRow - 1} and only in the columns {@code startCol},
     * {@code startCol + 1}, {@code startCol + 2}, ..., {@code endCol - 1}.
     * 
     * @param sortedMatrix the matrix to search within
     * @param target       the value to search for
     * @param startRow     the first row to be considered
     * @param endRow       the first row after startRow not to be considered
     * @param startCol     the first column to be considered
     * @param endCol       the first column after startCol not to be considered
     * @return {@code true} if the given target is an entry of the given sorted
     *         matrix within the bounds specified by the given rows and columns.
     */
    private static boolean binarySearch(int[][] sortedMatrix, int target, int startRow, int endRow, int startCol,
            int endCol) {
        while (startRow < endRow && startCol < endCol) {
            int midRow = startRow + (endRow - startRow) / 2;
            int midCol = startCol + (endCol - startCol) / 2;
            int midValue = sortedMatrix[midRow][midCol];
            if (midValue == target) {
                return true;
            } else if (startCol + 1 == endCol && startRow + 1 == endRow) {
                break;
            } else if (midValue < target) {
                return binarySearch(sortedMatrix, target, midRow, endRow, midCol, endCol)
                        || binarySearch(sortedMatrix, target, midRow, endRow, startCol, midCol)
                        || binarySearch(sortedMatrix, target, startRow, midRow, midCol, endCol);
            } else {
                return binarySearch(sortedMatrix, target, startRow, midRow, startCol, midCol)
                        || binarySearch(sortedMatrix, target, midRow, endRow, startCol, midCol)
                        || binarySearch(sortedMatrix, target, startRow, midRow, midCol, endCol);
            }

        }
        return false;
    }

    /**
     * Produces a square nxn sorted matrix. Values are picked to be either 1 or 2
     * greater than the minimal acceptable value, ensuring some values are likely
     * skipped.
     * 
     * @param n the dimension of the matrix to be produces
     * @return a square nxn sorted matrix.
     */
    public static int[][] randomSortedMatrix(int n) {
        Random rand = new Random();
        int[][] out = new int[n][n];

        for (int i = 1; i < n; i++) {
            out[i][0] = out[i - 1][0] + rand.nextInt(2) + 1;
            out[0][i] = out[0][i - 1] + rand.nextInt(2) + 1;
            for (int j = 1; j < n; j++) {
                out[i][j] = Math.max(out[i - 1][j], out[i][j - 1]) + rand.nextInt(2) + 1;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int n = 5;
        Random rand = new Random();
        // The following is a reasonable test to see if your code is working:
        for (int i = 0; i < 100; i++) {
            // pick a random matrix and target
            int[][] matrix = randomSortedMatrix(n);
            int target = rand.nextInt(4 * n);

            // determine two results
            boolean slowResult = search(matrix, target);
            boolean quickResult = binarySearch(matrix, target);

            // if they don't match, since we know that slowResult was right it
            // must be that quickResult is wrong, ie there's an error with your
            // binarySearch method
            if (slowResult != quickResult) {
                System.out.println("Failure");
                System.out.println("target: " + target);
                for (int[] row : matrix) {
                    for (int col : row)
                        System.out.print(col + " ");
                    System.out.println();
                }
                System.out.println(slowResult + " : " + quickResult);
            }
        }
    }
}