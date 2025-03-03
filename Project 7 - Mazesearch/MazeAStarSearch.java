
/**
 * Author: Daniel Yu
 * 
 * a PriorityQueue as the backbone of the algorithm and use our knowledge of where the target is to try to walk in the 'right direction' until we reach the target or exhaust all reachable Cells.
 */

import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {

    private Heap<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze) {
        super(maze);
        priorityQueue = new Heap<Cell>(new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                int priority1 = traceback(cell1).size() + calculateEstimatedDistance(cell1);
                int priority2 = traceback(cell2).size() + calculateEstimatedDistance(cell2);
                return Integer.compare(priority2, priority1);
            }
        });
    }

    private int calculateEstimatedDistance(Cell cell) {
        // Calculate Manhattan distance from the given cell to the target cell
        int targetRow = getTarget().getRow();
        int targetCol = getTarget().getCol();
        if (cell.getType() == CellType.MUD) {
            targetRow++;
            targetCol++;
        }
        if (cell.getType() == CellType.ICE) {
            targetRow--;
            targetCol--;
        }
        return Math.abs(cell.getRow() - targetRow) + Math.abs(cell.getCol() - targetCol);
    }

    @Override
    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    @Override
    public void addCell(Cell next) {
        priorityQueue.offer(next);
        // System.out.println("added " + next);
    }

    @Override
    public int numRemainingCells() {
        return priorityQueue.size();
    }
}
