
/**
* Author: Daniel Yu
* 
* To simulate an actual game of Blackjack being played.
*/
import java.util.Scanner;

public class Blackjack {
    Scanner input = new Scanner(System.in);
    int reshuffle;
    Hand Player = new Hand();
    Hand Dealer = new Hand();
    Deck Deck = new Deck();

    // initialize Blackjack Object with adjustable reshuffleCutoff
    public Blackjack(int reshuffleCutoff) {
        reshuffle = reshuffleCutoff;
        Deck.shuffle();
    }

    // initialize Blackjack Object (default)
    public Blackjack() {
        reshuffle = 26;
        Deck.shuffle();
    }

    // reset the game, shuffle only ife th card count is under the reshuffleCutoff
    public void reset() {
        //reset both hands
        Player.reset();
        Dealer.reset();

        //check card count
        if (Deck.size() < reshuffle) {
            // System.out.println("Card count below the cutoff, SHUFFLING!");
            Deck.build();
            Deck.shuffle();
        }
    }

    // start of the game, both players get 2 cards
    public void deal() {
        Player.add(Deck.deal());
        Dealer.add(Deck.deal());
        Player.add(Deck.deal());
        Dealer.add(Deck.deal());
    }

    // player draws card until they have a value of over 16, then returns true or
    // false depending on if they busted or not
    public boolean playerTurn() {

        //keep adding until over 16
        while (Player.getTotalValue() < 16) {
            Player.add(Deck.deal());
        }

        //bust check
        if (Player.getTotalValue() > 21) {
            return false;
        } else {
            return true;
        }

    }

    // controllable player that can choose to draw or stand (Ace rule applies)
    public boolean playerTurnInteractive() {

        //tell player card current hand
        System.out.println("Your Hand is currently " + Player);
        System.out.println("What will you do? (stand or hit)");
        String answer = input.nextLine();

        //choosing to hit, stand, or bust
        while (!(answer.equalsIgnoreCase("stand")) && Player.getTotalValue() < 21) {
            while (!(answer.equalsIgnoreCase("hit") || answer.equalsIgnoreCase("stand"))) {
                System.out.println("That's not a valid command bozo (stand or hit)");
                answer = input.nextLine();
            }
            if (answer.equalsIgnoreCase("hit")) {
                Card a = Deck.deal();
                if (a.getValue() == 11 && a.getValue() + Player.getTotalValue() > 21) {
                    a = new Card(1);
                }
                Player.add(a);
                if (a.getValue() == 1) {
                    System.out.println(
                            "You drew an 11, which would've made you bust, but because Ace rules, it is now a 1. Your total is "
                                    + Player.getTotalValue());
                } else {
                    System.out.println("You drew a " + a.getValue() + ", which totals to " + Player.getTotalValue());
                }
                if (Player.getTotalValue() < 21) {
                    System.out.println("What will you do? (stand or hit)");
                    answer = input.nextLine();
                }
            }
        }
        // evaluating if the player busted or hit 21
        if (Player.getTotalValue() > 21) {
            System.out.println("Bummer you busted lol");
            return false;
        } else {
            if (Player.getTotalValue() == 21) {
                System.out.println("Congrats you got to 21!");
            }
            return true;
        }

    }

    // dealer draws card until they have a value of over 16, then returns true or
    // false depending on if they busted or not
    public boolean dealerTurn() {

        //keep adding until over 17
        while (Dealer.getTotalValue() < 17) {
            Dealer.add(Deck.deal());
        }

        //bust check
        if (Dealer.getTotalValue() > 21) {
            return false;
        } else {
            return true;
        }

    }

    // change how many cards the deck needs to fall below to shuffle
    public void setReshuffleCutoff(int cutoff) {
        reshuffle = cutoff;
    }

    // returns the value of the shuffle cut off
    public int getReshuffleCutoff() {
        return reshuffle;
    }

    // narrates the state of the game
    public String toString() {
        String Narration = "";

        //player bust check
        if (playerTurn() == false) {
            Narration += "Player Hand: " + Player;
            Narration += "\nPlayer busted, Dealer Wins!";
        } else {
            Narration += "Player Hand: " + Player;

            //dealer bust check
            if (dealerTurn() == false) {
                Narration += "\nDealer Hand: " + Dealer;
                Narration += "\nDealer busted, Player Wins!";
            } else {
                Narration += "\nDealer Hand: " + Dealer;

                //blackjack check
                if (Player.getTotalValue() == 21 && Dealer.getTotalValue() == 21) {
                    if (Player.size() == 2 && Dealer.size() == 2) {
                        Narration += "\nWOW. Both players got a Blackjack? Ok then. Push.";
                    } else {
                        if (Player.size() == 2) {
                            Narration += "\nEven though both players got 21, the Player hit a Blackjack, so they win!";

                        } else if (Dealer.size() == 2) {
                            Narration += "\nEven though both players got 21, the Dealer hit a Blackjack, so they win!";

                        } else {
                            Narration += "\nWow both players got to 21, that's crazy! That's a push tho.";

                        }
                    }
                } else {
                    //winning hand check
                    if (Player.getTotalValue() > Dealer.getTotalValue()) {
                        Narration += "\nYou were closer to 21, you win!";
                    } else if (Player.getTotalValue() == Dealer.getTotalValue()) {
                        Narration += "\nBoth Players were equally close to 21, no one wins!";

                    } else {
                        Narration += "\nDealer was closer to 21, Dealer wins!";
                    }
                }
            }
        }
        return Narration;
    }

    // narrates the state of the game for when the player has choice (ace rules
    // apply)
    public int Interactive() {
        
        //start of game
        reset();
        deal();
        System.out.println("GAME BEGIN");
        System.out.println("Your Initial Hand: " + Player);
        System.out.println("Dealer Initial Hand: " + Dealer);
        System.out.println("Your turn...");

        //player bust check
        if (playerTurnInteractive() == false) {
            System.out.println("Dealer Wins!");
            return -1;
        } else {
            System.out.println("Your Ending Hand: " + Player);
            System.out.println("You did not bust! That's crazy!");
            System.out.println("Dealer's turn...");
        }

        //dealer bust check
        if (dealerTurn() == false) {
            System.out.println("Dealer Ending Hand: " + Dealer);
            System.out.println("Dealer busted, You Win!");
            return 1;
        } else {
            System.out.println("Dealer Ending Hand: " + Dealer);
            System.out.println("Dealer did not bust! That's crazy!");

            //blackjack check
            if (Player.getTotalValue() == 21 && Dealer.getTotalValue() == 21) {
                if (Player.size() == 2 && Dealer.size() == 2) {
                    System.out.println("WOW. Both players got a Blackjack? Ok then. Push.");
                    return 0;
                } else {
                    if (Player.size() == 2) {
                        System.out.println("Even though both players got 21, the Player hit a Blackjack, so they win!");
                        return 1;
                    } else if (Dealer.size() == 2) {
                        System.out.println("Even though both players got 21, the Dealer hit a Blackjack, so they win!");
                        return -1;
                    } else {
                        System.out.println("Wow both players got to 21, that's crazy! That's a push tho.");
                        return 0;
                    }
                }
            } else {

                //winning hand check
                if (Player.getTotalValue() > Dealer.getTotalValue()) {
                    System.out.println("You were closer to 21, you win!");
                    return 1;
                } else if (Player.getTotalValue() == Dealer.getTotalValue()) {
                    System.out.println("Both Players were equally close to 21, no one wins!");
                    return 0;
                } else {
                    System.out.println("Dealer was closer to 21, Dealer wins!");
                    return -1;
                }
            }
        }

    }

    // plays a single game of Blackjack. resets at the start of each game. The game
    // method should return a -1 if the dealer wins, 0 in case of a push (tie), and
    // a 1 if the player wins. If the parameter verbose is true, then the game
    // method should print out the initial and final hands of the game and a
    // statement about the result (dealer/push/player).
    public int game(boolean verbose) {

        //start of game
        reset();
        deal();

        //narration if verbose is true
        if (verbose == true) {
            System.out.println("GAME BEGIN");
            System.out.println("Player Initial Hand: " + Player);
            System.out.println("Dealer Initial Hand: " + Dealer);
            System.out.println("Player Plays...");
        }

        //player bust check
        if (playerTurn() == false) {
            if (verbose == true) {
                System.out.println("Player Ending Hand: " + Player);
                System.out.println("Player busted, Dealer Wins!");
            }
            return -1;
        } else {

            //narration if verbose is true for player
            if (verbose == true) {
                System.out.println("Player Ending Hand: " + Player);
                System.out.println("Player did not bust! That's crazy!");
                System.out.println("Dealer Plays...");
            }

            //dealer bust check
            if (dealerTurn() == false) {
                if (verbose == true) {
                    System.out.println("Dealer Ending Hand: " + Dealer);
                    System.out.println("Dealer busted, Player Wins!");
                }
                return 1;
            } else {

                //narration if verbose is true for dealer
                if (verbose == true) {
                    System.out.println("Dealer Ending Hand: " + Dealer);
                    System.out.println("Dealer did not bust! That's crazy!");
                }

                //blackjack check
                if (Player.getTotalValue() == 21 && Dealer.getTotalValue() == 21) {
                    if (Player.size() == 2 && Dealer.size() == 2) {
                        if (verbose == true) {
                            System.out.println(
                                    "WOW. Both players got a Blackjack? Ok then. Push.");
                        }
                        return 0;
                    } else {
                        if (Player.size() == 2) {
                            if (verbose == true) {
                                System.out.println(
                                        "Even though both players got 21, the Player hit a Blackjack, so they win!");
                            }
                            return 1;
                        } else if (Dealer.size() == 2) {
                            if (verbose == true) {
                                System.out.println(
                                        "Even though both players got 21, the Dealer hit a Blackjack, so they win!");
                            }
                            return -1;
                        } else {
                            if (verbose == true) {
                                System.out.println("Wow both players got to 21, that's crazy! That's a push tho.");
                            }
                            return 0;
                        }
                    }

                } else {

                    //winning hand check
                    if (Player.getTotalValue() > Dealer.getTotalValue()) {
                        if (verbose == true) {
                            System.out.println("Player was closer to 21, Player wins!");
                        }
                        return 1;
                    } else if (Player.getTotalValue() == Dealer.getTotalValue()) {
                        if (verbose == true) {
                            System.out.println("Both Players were equally close to 21, no one wins!");
                        }
                        return 0;
                    } else {
                        if (verbose == true) {
                            System.out.println("Dealer was closer to 21, Dealer wins!");
                        }
                        return -1;
                    }
                }
            }
        }

    }

    //playing multiple games to test all methods
    public static void main(String[] args) {
        Blackjack a = new Blackjack(47);
        for (int i = 0; i < 10; i++) {
            System.out.println("GAME " + (i + 1));
            System.out.println("======");
            a.game(true);
            System.out.println();
        }

    }
}
