
/**
 * Author: Daniel Yu
 * 
 * a Stack as the backbone of the algorithm and continually dive down a particular solution until we reach the target or exhaust all reachable Cells.
*/

import java.util.Stack;

public class MazeDepthFirstSearch extends AbstractMazeSearch {
    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        stack = new Stack<>();
    }

    @Override
    public Cell findNextCell() {
        return stack.pop();
    }

    @Override
    public void addCell(Cell next) {
        stack.push(next);
    }

    @Override
    public int numRemainingCells() {
        return stack.size();
    }
}