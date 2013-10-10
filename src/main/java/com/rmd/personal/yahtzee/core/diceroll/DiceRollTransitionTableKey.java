package com.rmd.personal.yahtzee.core.diceroll;

public class DiceRollTransitionTableKey {
    private int maxN;
    private int remainingRolls;

    public DiceRollTransitionTableKey(int maxN, int remainingRolls) {
        this.maxN = maxN;
        this.remainingRolls = remainingRolls;
    }

    public int getMaxN() {
        return maxN;
    }

    public int getRemainingRolls() {
        return remainingRolls;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof DiceRollTransitionTableKey)) {
            return false;
        }

        DiceRollTransitionTableKey otherKey = (DiceRollTransitionTableKey) other;

        return otherKey.getMaxN() == getMaxN()
                && otherKey.getRemainingRolls() == getRemainingRolls();
    }

    @Override
    public int hashCode() {
        final int effectiveHashMultiplier = 37;
        return effectiveHashMultiplier * getRemainingRolls()
                + effectiveHashMultiplier * getMaxN();
    }
}