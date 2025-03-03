
/**
* Author: Daniel Yu
* 
* Making a class that can store Cards from a Deck to play Blackjack, since Blackjack only requires using the cards that have been drawn.
*/

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> Hand = new ArrayList<>();

    /**
     * Creates an empty hand as an ArrayList of Cards.
     */
    public Hand() {
        Hand = new ArrayList<>();
    }

    /**
     * Removes any cards currently in the hand.
     */
    public void reset() {
        Hand.clear();
    }

    /**
     * Adds the specified card to the hand.
     * 
     * @param card the card to be added to the hand
     */

    public void add(Card card) {
        if (card.getValue() == 11) {
            if (getTotalValue() + card.getValue() > 21) {
                card = new Card(1);
                Hand.add(card);
            } else {
                Hand.add(card);
            }
        } else {
            Hand.add(card);
        }

    }

    /**
     * Returns the number of cards in the hand.
     * 
     * @return the number of cards in the hand
     */
    public int size() {
        return Hand.size();
    }

    /**
     * Returns the card in the hand specified by the given index.
     * 
     * @param index the index of the card in the hand.
     * @return the card in the hand at the specified index.
     */
    public Card getCard(int index) {
        return Hand.get(index);
    }

    /**
     * Returns the summed value over all cards in the hand.
     * 
     * @return the summed value over all cards in the hand
     */
    public int getTotalValue() {
        int sum = 0;
        for (Card a : Hand) {
            sum += a.getValue();
        }
        return sum;
    }

    /**
     * Returns a string representation of the hand.
     * 
     * @return a string representation of the hand
     */
    public String toString() {
        return Hand + " : " + getTotalValue();
    }
}