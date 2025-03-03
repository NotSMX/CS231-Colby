package Lab03;

import java.util.Random;

/**
 * Author: Daniel Yu
 * 
 * To simulate Agents and their interactions until they stop.
 */

public class AgentSimulation {

    public static void main(String[] args) throws InterruptedException {
        // to make sure the user has inputed the correct number of arguments
        if (args.length > 2 && args.length < 4) {
            // to make each input a command line argument
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            int iteration = Integer.parseInt(args[2]);

            // creates a landscape grid
            Landscape scape = new Landscape(width, height);

            // to display the grid
            LandscapeDisplay display = new LandscapeDisplay(scape);

            Random gen = new Random();

            // Creates N SocialAgents and N AntiSocialAgents
            for (int i = 0; i < iteration; i++) {
                scape.addAgent(new SocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(),
                        25));
                scape.addAgent(new AntiSocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(),
                        15));
            }

            // to see the simulation happening
            while(true)
            {
            Thread.sleep(10);
            scape.updateAgents();
            display.repaint();
            }

        } else
        // Since I did command-line arguments, the necessary arguments are needed to
        // function the simulation
        // therefore if the user does not put in the correct args, it won't run
        {
            System.out.println("USAGE: java SociaAgentSimulation.java <width> <height>" +
                    " <number of agents>");
        }

    }
}
