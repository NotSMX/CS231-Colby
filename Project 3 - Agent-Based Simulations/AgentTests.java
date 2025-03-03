/**
* Author: Daniel Yu
* 
* To test if the Agent classes are working
*/

package Lab03;

public class AgentTests {

    public static void agentTests() {

        // case 1: testing Cell() and Cell(Bool)
        {
            // set up
            SocialAgent a1 = new SocialAgent(1, 2, 3);
            AntiSocialAgent a2 = new AntiSocialAgent(4, 5, 6);

            // verify
            System.out.println(a1 + " == (1, 2)");
            System.out.println(a2 + " == (4, 5)");

            // test
            assert a1 != null : "Error in Agent::SocialAgent()";
            assert a2 != null : "Error in Agent::AntiSocialAgent()";
        }

        // case 2: testing getX(), getY() and getRadius()
        {
            // set up
            SocialAgent a1 = new SocialAgent(1, 2, 3);
            AntiSocialAgent a2 = new AntiSocialAgent(4, 5, 6);

            // verify
            System.out.println(a1.getX() + " == 1");
            System.out.println(a1.getY() + " == 2");
            System.out.println(a1.getRadius() + " == 3");
            System.out.println(a2.getX() + " == 4");
            System.out.println(a2.getY() + " == 5");
            System.out.println(a2.getRadius() + " == 6");

            // test
            assert a1.getX() == 1 : "Error in Agent::SocialAgent()::getX()";
            assert a2.getX() == 4 : "Error in Agent::AntiSocialAgent()::getX()";
            assert a1.getY() == 2 : "Error in Agent::SocialAgent()::getY()";
            assert a2.getY() == 5 : "Error in Agent::AntiSocialAgent()::getY()";
            assert a1.getRadius() == 3 : "Error in Agent::SocialAgent()::getRadius()";
            assert a2.getRadius() == 6 : "Error in Agent::AntiSocialAgent()::getRadius()";
        }

        // case 3: testing setX(), setY() and setRadius()
        {
            // set up
            SocialAgent a1 = new SocialAgent(0, 0, 0);
            AntiSocialAgent a2 = new AntiSocialAgent(0, 0, 0);

            a1.setX(1);
            a1.setY(2);
            a1.setRadius(3);
            a2.setX(4);
            a2.setY(5);
            a2.setRadius(6);

            // verify
            System.out.println(a1.getX() + " == 1");
            System.out.println(a1.getY() + " == 2");
            System.out.println(a1.getRadius() + " == 3");
            System.out.println(a2.getX() + " == 4");
            System.out.println(a2.getY() + " == 5");
            System.out.println(a2.getRadius() + " == 6");

            // test
            assert a1.getX() == 1 : "Error in Agent::SocialAgent()::setX()";
            assert a2.getX() == 4 : "Error in Agent::AntiSocialAgent()::setX()";
            assert a1.getY() == 2 : "Error in Agent::SocialAgent()::setY()";
            assert a2.getY() == 5 : "Error in Agent::AntiSocialAgent()::setY()";
            assert a1.getRadius() == 3 : "Error in Agent::SocialAgent()::setRadius()";
            assert a2.getRadius() == 6 : "Error in Agent::AntiSocialAgent()::setRadius()";
        }

        // case 4: testing updateState()
        {
            // set up
            SocialAgent a1 = new SocialAgent(0, 0, 1);
            AntiSocialAgent a2 = new AntiSocialAgent(0, 1, 1);
            SocialAgent a3 = new SocialAgent(9, 9, 5);
            SocialAgent a4 = new SocialAgent(9, 10, 5);
            SocialAgent a5 = new SocialAgent(10, 10, 5);
            SocialAgent a6 = new SocialAgent(10, 9, 5);
            AntiSocialAgent a7 = new AntiSocialAgent(8, 10, 2);

            Landscape scape = new Landscape(10, 10);
            scape.addAgent(a1);
            scape.addAgent(a2);
            scape.addAgent(a3);
            scape.addAgent(a4);
            scape.addAgent(a5);
            scape.addAgent(a6);

            // verify
            System.out.print(a1 + " found " + scape.getNeighbors(a1.x, a1.y, a1.r) + " neighbors and moved to ");
            a1.updateState(scape);
            System.out.println(" " + a1);

            System.out.print(a2 + " found " + scape.getNeighbors(a2.x, a2.y, a2.r) + " neighbors and stayed at ");
            a2.updateState(scape);
            System.out.println(" " + a2);

            System.out.print(a3 + " found " + scape.getNeighbors(a3.x, a3.y, a3.r) + " neighbors and stayed at ");
            a3.updateState(scape);
            System.out.println(" " + a3);

            System.out.print(a4 + " found " + scape.getNeighbors(a4.x, a4.y, a4.r) + " neighbors and stayed at ");
            a4.updateState(scape);
            System.out.println(" " + a4);

            System.out.print(a5 + " found " + scape.getNeighbors(a5.x, a5.y, a5.r) + " neighbors and stayed at ");
            a5.updateState(scape);
            System.out.println(" " + a5);

            System.out.print(a6 + " found " + scape.getNeighbors(a6.x, a6.y, a6.r) + " neighbors and stayed at ");
            a6.updateState(scape);
            System.out.println(" " + a6);

            System.out.print(a7 + " found " + scape.getNeighbors(a7.x, a7.y, a7.r) + " neighbors and moved to ");
            a7.updateState(scape);
            System.out.println(" " + a7);

            // test
            // assert c1.getAlive() == false : "Error in Cell::updateState()";
        }

        // no test for toString(), students could format string differently

        System.out.println("*** Done testing Cell! ***\n");
    }

    public static void main(String[] args) {

        agentTests();
    }
}
