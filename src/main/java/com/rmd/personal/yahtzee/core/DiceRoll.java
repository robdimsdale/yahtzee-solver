package com.rmd.personal.yahtzee.core;

import java.util.Arrays;

public class DiceRoll {

    private int[] diceValues = new int[Rules.getNumberOfDice()];

    public DiceRoll(int[] diceValues) {
        this.setDiceValues(diceValues);
    }

    public int[] getDiceValues() {
        return Arrays.copyOf(diceValues, diceValues.length);
    }

    private void setDiceValues(int[] diceValues) {
        this.diceValues = Arrays.copyOf(diceValues, diceValues.length);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof DiceRoll)) {
            return false;
        }

        DiceRoll otherDiceRoll = (DiceRoll) other;
        int[] otherValues = otherDiceRoll.getDiceValues();
        Arrays.sort(otherValues);

        int[] thisValues = this.getDiceValues();
        Arrays.sort(thisValues);

        return Arrays.equals(thisValues, otherValues);
    }

    @Override
    public int hashCode() {
        final int effectiveHashMultiplier = 37;
        int hashCode = 0;
        for (int diceValue : getDiceValues()) {
            hashCode += effectiveHashMultiplier * diceValue;
        }
        return hashCode;
    }
}
