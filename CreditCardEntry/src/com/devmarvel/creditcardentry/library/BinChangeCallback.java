package com.devmarvel.creditcardentry.library;

/**
 * Created by Developer on 1/13/2017.
 */

public interface BinChangeCallback {
    /**
     * called when bin is changed
     * @param bin the card
     */
    void changedBin(String bin);
}