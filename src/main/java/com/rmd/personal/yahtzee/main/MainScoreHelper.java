package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.Score;
import com.rmd.personal.yahtzee.core.ScoreType;

import java.util.List;
import java.util.Map;

public final class MainScoreHelper {

    public static final int MAX_DIE_VALUE = 6;

    private MainScoreHelper() {
        // private constructor to prevent instantiation.
    }

    protected static Score getScoreByTypeFromScoreList(List<Score> scoreList, ScoreType scoreType) {
        for (Score score : scoreList) {
            if (score.getScoreType() == scoreType) {
                return score;
            }
        }
        return null;
    }

    protected static int[] getNextDiceValues(int[] diceValues) {
        return getDiceValuesFromInt(getDiceValuesAsInt(diceValues) + 1);
    }

    protected static int getDiceValuesAsInt(int[] diceValues) {
        final int n = diceValues.length;
        int diceValueAsInt = 0;
        for (int i = 0; i < n; i++) {
            diceValueAsInt += (diceValues[i]) * Math.pow(MAX_DIE_VALUE, (n - i - 1));
        }
        return diceValueAsInt;
    }

    private static int[] getDiceValuesFromInt(int diceValuesAsInt) {
        diceValuesAsInt -= MainScoreHelper.minimumValue();
        final int n = DiceRoll.NUMBER_OF_DICE;
        int[] diceValues = new int[n];

        for (int i = 0; i < n; i++) {
            double index = Math.pow(MAX_DIE_VALUE, (n - i - 1));
            diceValues[i] = (int) Math.floor((double) diceValuesAsInt / index);
            diceValuesAsInt -= diceValues[i] * index;
            diceValues[i] += 1;
        }
        return diceValues;
    }

    protected static double getAverageForScoreType(ScoreType scoreType,
                                                   boolean excludeZeroScore,
                                                   Map<DiceRoll, Integer> possibleDiceRolls,
                                                   ScoreTable scoreTable) {
        int total = 0;
        int allRolls = 0;
        int nonZeroScoringRolls = 0;
        for (DiceRoll diceRoll : possibleDiceRolls.keySet()) {
            ScoreTableKey key = new ScoreTableKey(diceRoll, scoreType);
            int rollsOfType = possibleDiceRolls.get(key.getDiceRoll());
            Integer value = scoreTable.get(key);
            if (value != null && value != 0) {
                total += scoreTable.get(key) * rollsOfType;
                nonZeroScoringRolls += rollsOfType;
            }
            allRolls += rollsOfType;
        }
        if (excludeZeroScore) {
            return total / (double) nonZeroScoringRolls;
        } else {
            return total / (double) allRolls;
        }
    }

    private static int minimumValue() {
        int[] diceValues = new int[DiceRoll.NUMBER_OF_DICE];
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = 1;
        }
        return getDiceValuesAsInt(diceValues);
    }
}
