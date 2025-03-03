/**
* Author: Daniel Yu
* 
* To instantiate a standard deck of 52 Card Objects, each with their own values 
*/	
import java.util.ArrayList;

public class Deck {
    ArrayList<Card> Deck = new ArrayList<>();

    /**
     * Creates the underlying deck as an ArrayList of Card objects.
     * Calls build() as a subroutine to build the deck itself.
     */
    public Deck() {
        Deck = new ArrayList<>();
        build();
    }

    /**
     * Builds the underlying deck as a standard 52 card deck.
     * Replaces any current deck stored.
     */
    public void build() {
        Deck.clear();
       for (int s = 0; s < 6; s++) {
            for (int a = 0; a < 4; a++) {
                for (int b = 2; b <= 9; b++) {
                    Deck.add(new Card(b));
                }
                Deck.add(new Card(11));
            }
            for (int a = 0; a < 16; a++) {
                Deck.add(new Card(10));
            }
        }

    }

    /**
     * Returns the number of cards left in the deck.
     * 
     * @return the number of cards left in the deck
     */
    public int size() {
        return Deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * 
     * @return the first card of the deck
     */
    public Card deal() {

        Card a = Deck.get(0);
        Deck.remove(0);
        return a;

    }

    /**
     * Shuffles the cards currently in the deck.
     */
    public void shuffle() {
        for (int i = Deck.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * i);
            Card temp = Deck.get(i);
            Deck.set(i, Deck.get(j));
            Deck.set(j, temp);
        }
    }

    /**
     * Returns a string representation of the deck.
     * 
     * @return a string representation of the deck
     */
    public String toString() {
        return Deck + "";
    }
}