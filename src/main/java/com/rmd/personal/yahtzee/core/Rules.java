package com.rmd.personal.yahtzee.core;

public final class Rules {

    private static final int YAHTZEE_INITIAL_SCORE_VALUE = 50;
    private static final int FULL_HOUSE_SCORE_VALUE = 25;
    private static final int SHORT_STRAIGHT_SCORE_VALUE = 30;
    private static final int LONG_STRAIGHT_SCORE_VALUE = 40;
    private static final int NUMBER_OF_DICE_REQUIRED_FOR_THREE_OF_A_KIND = 3;
    private static final int NUMBER_OF_DICE_REQUIRED_FOR_FOUR_OF_A_KIND = 4;
    private static final int NUMBER_OF_DICE_REQUIRED_FOR_YAHTZEE = 5;

    private static final int NUMBER_OF_DICE = 5;
    private static final int DIE_FACE_COUNT = 6;
    private static final int DICE_ROLLS_COUNT = 3; // The game will break if this value is < 1.

    private Rules() {
    }

    public static int getNumberOfDice() {
        return NUMBER_OF_DICE;
    }

    public static int getDieFaceCount() {
        return DIE_FACE_COUNT;
    }

    public static int getDiceRollsCount() {
        return DICE_ROLLS_COUNT;
    }

    public static int getYahtzeeInitialScoreValue() {
        return YAHTZEE_INITIAL_SCORE_VALUE;
    }

    public static int getFullHouseScoreValue() {
        return FULL_HOUSE_SCORE_VALUE;
    }

    public static int getShortStraightScoreValue() {
        return SHORT_STRAIGHT_SCORE_VALUE;
    }

    public static int getLongStraightScoreValue() {
        return LONG_STRAIGHT_SCORE_VALUE;
    }

    public static int getNumberOfDiceRequiredForThreeOfAKind() {
        return NUMBER_OF_DICE_REQUIRED_FOR_THREE_OF_A_KIND;
    }

    public static int getNumberOfDiceRequiredForFourOfAKind() {
        return NUMBER_OF_DICE_REQUIRED_FOR_FOUR_OF_A_KIND;
    }

    public static int getNumberOfDiceRequiredForYahtzee() {
        return NUMBER_OF_DICE_REQUIRED_FOR_YAHTZEE;
    }
}
