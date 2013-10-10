package com.rmd.personal.yahtzee.core.score;

public enum ScoreType {

    ONES("Ones", "1s"),
    TWOS("Twos", "2s"),
    THREES("Threes", "3s"),
    FOURS("Fours", "4s"),
    FIVES("Fives", "5s"),
    SIXES("Sixes", "6s"),

    THREE_OF_A_KIND("Three of a kind", "3X"),
    FULL_HOUSE("Full house", "FH"),
    FOUR_OF_A_KIND("Four of a kind", "4X"),
    SHORT_STRAIGHT("Short straight", "SS"),
    LONG_STRAIGHT("Long straight", "LS"),
    YAHTZEE("Yahtzee", "5X"),

    CHANCE("Chance", "CH");

    private ScoreType(String displayName, String shortDisplayName) {
        this.displayName = displayName;
        this.shortDisplayName = shortDisplayName;
    }

    private String displayName;
    private String shortDisplayName;

    public String displayName() {
        return displayName;
    }

    public String shortDisplayName() {
        return shortDisplayName;
    }

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
