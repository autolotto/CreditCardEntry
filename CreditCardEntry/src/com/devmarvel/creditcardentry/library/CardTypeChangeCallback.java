package com.devmarvel.creditcardentry.library;

/**
 * Created by iDevelopers on 11/11/2016.
 */

public interface CardTypeChangeCallback {
    /**
     * called when card type is changed
     * @param type the card
     */
    void changedCardType(CardType type);
}
