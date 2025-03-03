/**
* Author: Daniel Yu
* 
* To test if the methods of Landscape are functioning
*/

package Lab03;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing getWidth()
        {
            // set up
            Landscape l = new Landscape(7, 5);
            // verify
            System.out.println(l.getWidth() + " == 7");
            // test
            assert l != null : "Error in Landscape::getWidth()";
            assert l.getWidth() == 7 : "Error in Landscape::getWidth()";
        }

        // case 3: testing getHeight()
        {
            // set up
            Landscape l = new Landscape(7, 5);
            // verify
            System.out.println(l.getHeight() + " == 5");
            // test
            assert l != null : "Error in Landscape::getCols()";
            assert l.getHeight() == 5 : "Error in Landscape::getCols()";
        }

        // case 4: testing addAgent(int, int)
        {
            // set up
            Landscape l = new Landscape(3, 3);
            // verify
            l.addAgent(new SocialAgent(0, 0, 1));
            System.out.println(l);
            // test
            assert l.agents != null : "Error in Landscape::addAgent(int, int)";

        }

        // case 5: testing getNeighbors()
        {
            // set up
            Landscape l = new Landscape(10, 10);
            // verify
            SocialAgent a1 = new SocialAgent(4, 5, 1);
            SocialAgent a2 = new SocialAgent(3, 5, 1);
            SocialAgent a3 = new SocialAgent(2, 5, 1);
            SocialAgent a4 = new SocialAgent(1, 5, 1);
            SocialAgent a5 = new SocialAgent(0, 5, 1);
            l.addAgent(a1);
            l.addAgent(a2);
            l.addAgent(a3);
            l.addAgent(a4);
            l.addAgent(a5);
            System.out.println(l);
            // test
            assert l != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 1) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 1).size() == 1 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 2) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 2).size() == 2 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 3) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 3).size() == 3 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 4) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 4).size() == 4 : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 5) != null : "Error in Landscape::getNeighbors()";
            assert l.getNeighbors(5, 5, 5).size() == 5 : "Error in Landscape::getNeighbors()";
        }

        System.out.println("*** Done testing Landscape! ***\n");
    }

    public static void main(String[] args) {

        landscapeTests();
    }
}