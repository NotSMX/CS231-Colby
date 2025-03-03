
/*
file name:      LandscapeTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea LandscapeTests
*/
package Lab02;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing reset()
        {
            // set up
            Landscape l1 = new Landscape(1, 5);
            Landscape l2 = new Landscape(10, 10, .5);
            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);
            System.out.println("\n");
            l1.reset();
            l2.reset();
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::reset()";
            assert l2 != null : "Error in Landscape::reset()";
        }

        // case 3: testing getRows()
        {
            // set up
            Landscape l = new Landscape(7, 5, .5);
            // verify
            System.out.println(l);
            // test
            assert l != null : "Error in Landscape::getRows()";
            assert l.getRows() == 7 : "Error in Landscape::getRows()";
        }

        // case 4: testing getCols()
        {
            // set up
            Landscape l = new Landscape(7, 5, .5);
            // verify
            System.out.println(l);
            // test
            assert l != null : "Error in Landscape::getCols()";
            assert l.getCols() == 5 : "Error in Landscape::getCols()";
        }

        // case 5: testing getCell(int, int)
        {
            // set up
            Landscape l = new Landscape(3, 3, .5);
            // verify
            System.out.println(l);
            // test
            assert l != null : "Error in Landscape::getCell(int, int)";
            assert l.getCell(1, 1) != null : "Error in Landscape::getCell(int, int)";

        }

        // case 6: testing getNeighbors()
        {
            // set up
            Landscape l = new Landscape(3, 3, .5);
            // verify
            System.out.println(l);
            // test
            assert l != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(1, 1) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(1, 1).size() == 8 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(0, 0) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(0, 0).size() == 3 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(1, 0) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(1, 0).size() == 5 : "Error in Landscape::getNeighbors()";
        }

        // case 7: testing advance()
        {
            // set up
            Landscape l = new Landscape(3, 3);
            // verify
            l.advance();
            System.out.println(l.getCell(0, 0) + " == 0");
            // test
            assert l != null : "Error in Landscape::advance()";
            assert l.getCell(0, 0).getAlive() == false : "Error in Landscape::advance()";
        }

        System.out.println("*** Done testing Landscape! ***\n");
    }

    public static void main(String[] args) {

        landscapeTests();
    }
}