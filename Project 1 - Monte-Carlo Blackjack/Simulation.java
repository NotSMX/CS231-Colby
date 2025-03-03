/**
 * Author: Daniel Yu
 * 
 * To execute and tally the outcomes of 1000 Blackjack games
 */

public class Simulation {

    public static void simulation() {

        //initialize winning tally variables
        int pw = 0, dw = 0, p = 0;

        //create blackjack object
        Blackjack bj1 = new Blackjack();

        //runs game 1000 times
        for (int n = 0; n < 1000; n++) {
            int gameend = bj1.game(false);
            // System.out.println(gameend + " pw: " + pw + " dw: " + dw + " p: " + p );
            switch (gameend + 2) {
                case 1:
                    dw++;
                    break;
                case 2:
                    p++;
                    break;
                case 3:
                    pw++;
                    break;
            }
        }

        //calculate and print total wins and their percentages
        System.out.println("Player Wins: " + pw + " ---> " + pw / 1000.0 * 100 + "%");
        System.out.println("Dealer Wins: " + dw + " ---> " + dw / 1000.0 * 100 + "%");
        System.out.println("Pushes: " + p + " ---> " + p / 1000.0 * 100 + "%");
    }

    public static void main(String[] args) {

        simulation();
    }
}
