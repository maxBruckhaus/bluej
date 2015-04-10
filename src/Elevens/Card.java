package Elevens;/*  Maxwell Bruckhaus, Period 3, March 18th, 2015
 *  This program took me approximately 20 minutes
 *  to complete in total. This program is easy so far
 *  since this step was simple. I like when we are assigned
 *  homework in steps like this. I'm hoping the rest of the
 *  project will go pretty smoothly. There isn't really
 *  anything that tripped me up in this assignment.
 */


/**
 * Elevens.Card.java
 *
 * <code>Elevens.Card</code> represents a playing card.
 */
public class Card {

    /**
     * String value that holds the suit of the card
     */
    private String suit;

    /**
     * String value that holds the rank of the card
     */
    private String rank;

    /**
     * int value that holds the point value.
     */
    private int pointValue;


    /**
     * Creates a new <code>Elevens.Card</code> instance.
     *
     * @param cardRank  a <code>String</code> value
     *                  containing the rank of the card
     * @param cardSuit  a <code>String</code> value
     *                  containing the suit of the card
     * @param cardPointValue an <code>int</code> value
     *                  containing the point value of the card
     */
    public Card(String cardRank, String cardSuit, int cardPointValue) {
        rank = cardRank;
		suit = cardSuit;
        pointValue = cardPointValue;
    }


    /**
     * Accesses this <code>Elevens.Card's</code> suit.
     * @return this <code>Elevens.Card's</code> suit.
     */
    public String suit() {
		return suit;
    }

    /**
     * Accesses this <code>Elevens.Card's</code> rank.
     * @return this <code>Elevens.Card's</code> rank.
     */
    public String rank() {
		return rank;
    }

    /**
     * Accesses this <code>Elevens.Card's</code> point value.
     * @return this <code>Elevens.Card's</code> point value.
     */
    public int pointValue() {
		return pointValue;
    }

    /** Compare this card with the argument.
     * @param otherCard the other card to compare to this
     * @return true if the rank, suit, and point value of this card
     *              are equal to those of the argument;
     *         false otherwise.
     */
    public boolean matches(Card otherCard) {
		return (suit.equals(otherCard.suit) && rank.equals(otherCard.rank) && pointValue== otherCard.pointValue);
    }

    /**
     * Converts the rank, suit, and point value into a string in the format
     *     "[Rank] of [Suit] (point value = [PointValue])".
     * This provides a useful way of printing the contents
     * of a <code>Elevens.Deck</code> in an easily readable format or performing
     * other similar functions.
     *
     * @return a <code>String</code> containing the rank, suit,
     *         and point value of the card.
     */
    @Override
    public String toString() {
		return (rank + " of " + suit + ". Point value is " + pointValue);
    }
}
