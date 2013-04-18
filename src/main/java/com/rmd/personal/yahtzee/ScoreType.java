package com.rmd.personal.yahtzee;

public enum ScoreType {

    ONES,
    TWOS,
    THREES,
    FOURS,
    FIVES,
    SIXES,

    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    YAHTZEE,
    SHORT_STRAIGHT,
    LONG_STRAIGHT;

    public static ScoreType getSingleCountFromInt(int intValue) {
        switch (intValue) {
            case 1:
                return ONES;
            case 2:
                return TWOS;
            case 3: // SUPPRESS CHECKSTYLE magicNumber
                return THREES;
            case 4: // SUPPRESS CHECKSTYLE magicNumber
                return FOURS;
            case 5: // SUPPRESS CHECKSTYLE magicNumber
                return FIVES;
            case 6: // SUPPRESS CHECKSTYLE magicNumber
                return SIXES;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static ScoreType getXOfAKindFromInt(int intValue) {
        switch (intValue) {
            case 3: // SUPPRESS CHECKSTYLE magicNumber
                return THREE_OF_A_KIND;
            case 4: // SUPPRESS CHECKSTYLE magicNumber
                return FOUR_OF_A_KIND;
            case 5: // SUPPRESS CHECKSTYLE magicNumber
                return YAHTZEE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
