/**
* Author: Daniel Yu
* 
* Creating an abitrary Card that can be assigned a value and be easily duplicated 
*/	

public class Card {

    /**
     * The value of the card.
     */
    private int value;

    /**
     * Constructs a card with the specified value.
     * @param val
     */
    public Card(int val) {
        value = val;
    }

    /**
     * Returns the value of the card.
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns a string representation of this card.
     * @return a string representation of this card
     */
    public String toString() {
        return value + "";
    }
}