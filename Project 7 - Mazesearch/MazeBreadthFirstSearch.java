
/**
 * Author: Daniel Yu
 * 
 * a Queue as the backbone of the algorithm and simultaneously work on 'every solution' by working on all directions at once until we reach the target or exhaust all reachable Cells.
*/

import java.util.LinkedList;
import java.util.Queue;

public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        queue = new LinkedList<>();
    }

    @Override
    public Cell findNextCell() {
        return queue.poll();
    }

    @Override
    public void addCell(Cell next) {
        queue.add(next);
    }

    @Override
    public int numRemainingCells() {
        return queue.size();
    }
}