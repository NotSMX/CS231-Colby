import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractMazeSearch {
    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;
    private MazeSearchDisplay a;

    // initializes the fields. In particular, saves the given Maze to the relevant
    // field and sets cur, start, and target to be null.
    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    // just returns the underlying Maze.
    public Maze getMaze() {
        return maze;
    }

    // sets the given target to be the target of the search.
    public void setTarget(Cell target) {
        this.target = target;
    }

    // returns the target of the search.
    public Cell getTarget() {
        return target;
    }

    // sets the given cell to be the current location of the search.
    public void setCur(Cell cell) {
        this.cur = cell;
    }

    // returns the current Cell location of the search.
    public Cell getCur() {
        return cur;
    }

    // sets the given start to be the start of the search. Additionally, set start's
    // prev to be itself - the reason for this will make sense a bit later.
    public void setStart(Cell start) {
        this.start = start;
        start.setPrev(start);
    }

    // returns the start of the search.
    public Cell getStart() {
        return start;
    }

    // sets the current, start, and target Cells to be null.
    public void reset() {
        cur = null;
        start = null;
        target = null;
    }

    // finds a path from the start to the specified cell by repeatedly following the
    // prev path. Returns the path if found, otherwise returns null.:
    public LinkedList<Cell> traceback(Cell cell) {
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();

        while (curCell != null) {
            if (curCell.getType() == CellType.MUD) {
                path.addFirst(curCell);
            } else if (curCell.getType() == CellType.ICE) {

            } else {
                path.addFirst(curCell);
            }
            if (curCell == start) {
                return path; // we've completed the path from the start to the specified cell
            }
            curCell = curCell.getPrev();
        }
        return null; // we weren't able to find a path, so we return null
    }

    // the most important method of the class. This is the method that actually does
    // the searching. Despite that we don't event know exactly which search
    // algorithm we'll use, we can use the abstract methods to organize the search
    // regardless. The LinkedList we return will be the path our search algorithm
    // finds to reach the target from the starting Cell. If one isn't found, we'll
    // return null. To aid you, consider the following pseudo-code:
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {
        setStart(start);
        setTarget(target);
        setCur(start);
        addCell(start);

        LinkedList<Cell> cellsToExplore = new LinkedList<>();
        cellsToExplore.add(start);

        if (display) {
            a = new MazeSearchDisplay(this, 75);
        }

        while (numRemainingCells() != 0) {
            if (display) {
                try {
                    Thread.sleep(delay);
                    a.repaint();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            setCur(findNextCell());
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null) {
                    if (cur.getType() == CellType.MUD && !cur.getMudToggle()) {
                        cur.setMudToggle(true);
                    } else {
                        neighbor.setPrev(cur);
                        cellsToExplore.add(neighbor);
                        addCell(neighbor);
                    }
                    // System.out.println(neighbor);

                    if (neighbor.getRow() == target.getRow() && neighbor.getCol() == target.getCol()) {
                        target.setPrev(cur);
                        // System.out.println("iwjwojeojf");
                        return traceback(neighbor); // we found the target, we're done
                    }
                }
            }
        }

        return null; // we couldn't find the target, but we're done
    }

    // this method returns the next Cell to explore.
    public abstract Cell findNextCell();

    // this method adds the given Cell to whatever structure is storing the future
    // Cells to explore.
    public abstract void addCell(Cell next);

    // this method returns the number of future Cells to explore (so just the size
    // of whatever structure is storing the future Cells).
    public abstract int numRemainingCells();

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);

        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }

    public static void main(String[] args) {

        Maze maz = new Maze(10, 10, 0, .5, 0);
        // AbstractMazeSearch temp = new MazeDepthFirstSearch(maz);
        AbstractMazeSearch temp = new MazeBreadthFirstSearch(maz);
        // AbstractMazeSearch temp = new MazeAStarSearch(maz);
        temp.search(new Cell(0, 0, CellType.FREE), new Cell(9, 9, CellType.FREE),
                true, 100);

        // double length = 0;
        // double success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeDepthFirstSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += a.size();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE LENGTH FOR MazeDepthFirstSearch: " + (length /
        // success));

        // length = 0;
        // success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeBreadthFirstSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += a.size();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE LENGTH FOR MazeBreadthFirstSearch: " + (length /
        // success));

        // length = 0;
        // success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeAStarSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += a.size();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE LENGTH FOR MazeAStarSearch: " + (length /
        // success));

        // length = 0;
        // success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeDepthFirstSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += 100 - temp.numRemainingCells();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE CELLS EXPLORED FOR MazeDepthFirstSearch: " +
        // (length / success));

        // length = 0;
        // success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeBreadthFirstSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += 100 - temp.numRemainingCells();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE CELLS EXPLORED FOR MazeBreadthFirstSearch: " +
        // (length / success));

        // length = 0;
        // success = 0;
        // for (int y = 0; y < 100; y++) {
        // Maze maz = new Maze(10, 10, .1);
        // AbstractMazeSearch temp = new MazeAStarSearch(maz);
        // LinkedList<Cell> a = temp.search(new Cell(0, 0, CellType.FREE), new Cell(9,
        // 9, CellType.FREE), false, 0);
        // if (a != null) {
        // length += 100 - temp.numRemainingCells();
        // success++;
        // }
        // }
        // System.out.println("AVERAGE CELLS EXPLORED FOR MazeAStarSearch: " + (length /
        // success));
    }
}