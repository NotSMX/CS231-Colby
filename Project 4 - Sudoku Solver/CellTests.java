package Lab04;

/**
 * Author: Daniel Yu
 * 
 * Testing Cell methods
 */

public class CellTests {

    public static void cellTests() {

        // case 1: testing Cell() and Cell(Bool)
        {
            // set up
            Cell c1 = new Cell();
            Cell c2 = new Cell(0, 0, 1);
            Cell c3 = new Cell(0, 0, 2, false);

            // verify
            System.out.println(c1 + " == 0");
            System.out.println(c2 + " == 1");
            System.out.println(c3 + " == 2");

            // test
            assert c1 != null : "Error in Cell::Cell()";
            assert c2 != null : "Error in Cell::Cell(row, col, value)";
            assert c3 != null : "Error in Cell::Cell(row, col, value, locked)";
        }

        // case 2: testing getRow() & getCol()
        {
            // set up
            Cell c1 = new Cell(1, 2, 0);

            // verify
            System.out.println(c1.getRow() + " == 1");
            System.out.println(c1.getCol() + " == 2");

            // test
            assert c1.getRow() == 1 : "Error in Cell::Cell() or Cell::getRow()";
            assert c1.getCol() == 2 : "Error in Cell::Cell() or Cell::getCol()";
        }

        // case 3: testing getValue(), setValue(), isLocked(), setLocked()
        {
            // set up
            Cell c1 = new Cell(0, 0, 0, false);

            // verify
            System.out.println(c1.getValue() + " == 0");
            System.out.println(c1.isLocked() + " == false");

            // test
            assert c1.getValue() == 0 : "Error in Cell::Cell() or Cell::getValue()";
            assert c1.isLocked() == false : "Error in Cell::Cell() or Cell::isLocked()";

            // set up
            c1.setValue(1);
            c1.setLocked(true);

            // verify
            System.out.println(c1.getValue() + " == 1");
            System.out.println(c1.isLocked() + " == true");

            // test
            assert c1.getValue() == 1 : "Error in Cell::Cell() or Cell::getValue()";
            assert c1.isLocked() == true : "Error in Cell::Cell() or Cell::isLocked()";

        }

        // no test for toString(), students could format string differently

        System.out.println("*** Done testing Cell! ***\n");
    }

    public static void main(String[] args) {

        cellTests();
    }
}