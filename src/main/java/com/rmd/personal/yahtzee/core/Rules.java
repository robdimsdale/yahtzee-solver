package com.rmd.personal.yahtzee.core;

public final class Rules {

    private Rules() {
    }

    private static final int NUMBER_OF_DICE = 5;
    private static final int MAX_DIE_VALUE = 6;

    public static int getNumberOfDice() {
        return NUMBER_OF_DICE;
    }
    public static int getMaxDieValue() {
        return MAX_DIE_VALUE;
    }
}
