package com.rmd.personal.yahtzee.core;

public class ScoreTableKey {
    private DiceRoll diceRoll;
    private ScoreType scoreType;

    public ScoreTableKey(DiceRoll diceRoll, ScoreType scoreType) {
        this.diceRoll = diceRoll;
        this.scoreType = scoreType;
    }

    public DiceRoll getDiceRoll() {
        return diceRoll;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof ScoreTableKey)) {
            return false;
        }

        ScoreTableKey otherKey = (ScoreTableKey) other;

        return otherKey.getDiceRoll().equals(getDiceRoll())
                && otherKey.getScoreType().equals(getScoreType());
    }

    @Override
    public int hashCode() {
        final int effectiveHashMultiplier = 37;
        return effectiveHashMultiplier * getScoreType().hashCode()
                + effectiveHashMultiplier * getDiceRoll().hashCode();
    }
}