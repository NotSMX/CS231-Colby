public class BlackjackTests {

    public static void blackjackTests() {

        // case 1: testing Blackjack() and Blackjack(i)
        {
            // set up
            Blackjack bj1 = new Blackjack();
            Blackjack bj2 = new Blackjack(10); // shuffle

            // verify
            assert bj1 != null : "Error in Blackjack::Blackjack()";
            assert bj2 != null : "Error in Blackjack::Blackjack()";
            bj1.game(true);
            boolean playerResult = bj1.playerTurn();
            // testing playerTurn()
            if (bj1.Player.getTotalValue() > 21) {
                assert playerResult == false;
                System.out.println(playerResult + " == false");
            } else {
                assert playerResult == true;
                System.out.println(playerResult + " == true");
            }
            System.out.println("*** Player Turn Works! ***\n");

            // testing dealerTurn()
            boolean dealerResult = bj1.dealerTurn();
            if (bj1.Dealer.getTotalValue() > 21) {
                assert dealerResult == false;
                System.out.println(dealerResult + " == false");
            } else {
                assert dealerResult == true;
                System.out.println(dealerResult + " == true");
            }
            System.out.println("*** Dealer Turn Works! ***\n");

            // testing reset()
            bj2.reset();
            assert bj2.Player.Hand == null;
            assert bj2.Dealer.Hand == null;
            System.out.println(bj2.Player.Hand + " == []");
            System.out.println(bj2.Dealer.Hand + " == []");
            System.out.println("*** Reset Works! ***\n");

            // testing deal()
            // System.out.println(bj2.Deck);
            bj2.deal();

            assert bj2.Player.Hand.size() == 2;
            assert bj2.Dealer.Hand.size() == 2;
            System.out.println(bj2.Player.Hand.size() + " == 2");
            System.out.println(bj2.Dealer.Hand.size() + " == 2");

            System.out.println("*** Deal Works! ***\n");

            // testing the shufflecutoff()
            while (bj2.Deck.size() > bj2.getReshuffleCutoff()) {
                bj2.game(false);
                System.out.println(bj2.Deck.size());
            }
            bj2.reset();
            System.out.println(bj2.Deck.size());
            assert bj2.Deck.size() > 5;
            System.out.println("*** Cutoff Works! ***\n");

        }

        System.out.println("*** Done testing Blackjack! ***\n");
    }

    public static void main(String[] args) {

        blackjackTests();
    }
}