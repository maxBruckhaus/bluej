/*  Maxwell Bruckhaus, Period 3, March 29th, 2015
 *  This program took me approximately 30 minutes to
 *  complete in total. There wasn't really any place
 *  where I was having problems, the tasks weren't
 *  too difficult for me.
 */


package Elevens;

import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indexes of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */

        return(containsJQK(selectedCards) || containsPairSum11(selectedCards));

    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        //have to use cardIndexes because here, a list of indexes isn't given in the parameter
        return(containsJQK(cardIndexes())) || containsPairSum11(cardIndexes());
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
			/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */

        for(int i = 0; i < selectedCards.size(); i++){
            for(int j = i+1; j < selectedCards.size(); j++){
                //int a = cardsAt(selectedCards.get(i)).pointValue();
                //int b = cardsAt(selectedCards.get(j)).pointValue();
                //return(a + b == 11);
            }
        }

        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        int j = 0;
        int k = 0;
        int q = 0;
        for(int i = 0; i < selectedCards.size(); i++){
            //int a = cardsAt(selectedCards.get(i)).pointValue();
            if (a == 11){
                j++;
            }
            if(a == 12){
                q++;
            }
            if(a == 13){
                k++;
            }
        }

        if( j >= 1 && k >= 1 && q >= 1)
            return true;

        return false;
    }
}