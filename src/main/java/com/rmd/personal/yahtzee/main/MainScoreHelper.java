package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.Score;
import com.rmd.personal.yahtzee.core.ScoreCalculator;
import com.rmd.personal.yahtzee.core.ScoreType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class MainScoreHelper {

    private static final MainScoreHelper INSTANCE = new MainScoreHelper();
    private static Map<DiceRoll, Integer> possibleDiceRollsMappedToFrequency;
    private static ScoreTable scoreTable;

    public static final int MAX_DIE_VALUE = 6;

    private MainScoreHelper() {
        initializeStaticFields();
        populatePossibleDiceValues();
        populateScoreTable();
    }

    private static void initializeStaticFields() {
        possibleDiceRollsMappedToFrequency = new LinkedHashMap<DiceRoll, Integer>();
        scoreTable = new ScoreTable();
    }

    public static MainScoreHelper getInstance() {
        return INSTANCE;
    }

    private ScoreCalculator getScoreCalculator() {
        return ScoreCalculator.getInstance();
    }

    public static Map<DiceRoll, Integer> getPossibleDiceRollsMappedToFrequency() {
        return possibleDiceRollsMappedToFrequency;
    }

    public static ScoreTable getScoreTable() {
        return scoreTable;
    }

    protected Score getScoreByTypeFromScoreList(List<Score> scoreList, ScoreType scoreType) {
        for (Score score : scoreList) {
            if (score.getScoreType() == scoreType) {
                return score;
            }
        }
        return null;
    }

    protected int[] getNextDiceValues(int[] diceValues) {
        return getDiceValuesFromInt(getDiceValuesAsInt(diceValues) + 1);
    }

    protected int getDiceValuesAsInt(int[] diceValues) {
        final int n = diceValues.length;
        int diceValueAsInt = 0;
        for (int i = 0; i < n; i++) {
            diceValueAsInt += (diceValues[i]) * Math.pow(MAX_DIE_VALUE, (n - i - 1));
        }
        return diceValueAsInt;
    }

    private int[] getDiceValuesFromInt(int diceValuesAsInt) {
        diceValuesAsInt -= minimumValue();
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

    protected double getAverageForScoreType(ScoreType scoreType,
                                                   boolean excludeZeroScore) {
        int total = 0;
        int allRolls = 0;
        int nonZeroScoringRolls = 0;
        for (DiceRoll diceRoll : getPossibleDiceRollsMappedToFrequency().keySet()) {
            ScoreTableKey key = new ScoreTableKey(diceRoll, scoreType);
            int rollsOfType = getPossibleDiceRollsMappedToFrequency().get(key.getDiceRoll());
            Integer value = getScoreTable().get(key);
            if (value != null && value != 0) {
                total += getScoreTable().get(key) * rollsOfType;
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

    private int minimumValue() {
        int[] diceValues = new int[DiceRoll.NUMBER_OF_DICE];
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = 1;
        }
        return getDiceValuesAsInt(diceValues);
    }

    private void populatePossibleDiceValues() {
        int[] startDiceValues = new int[DiceRoll.NUMBER_OF_DICE];
        for (int i = 0; i < startDiceValues.length; i++) {
            startDiceValues[i] = 1;
        }

        int[] endDiceValues = new int[DiceRoll.NUMBER_OF_DICE];
        for (int i = 0; i < endDiceValues.length; i++) {
            endDiceValues[i] = MainScoreHelper.MAX_DIE_VALUE;
        }

        int[] diceValues = startDiceValues;
        while (getDiceValuesAsInt(diceValues) <= getDiceValuesAsInt(endDiceValues)) {
            DiceRoll diceRoll = new DiceRoll(diceValues);
            if (getPossibleDiceRollsMappedToFrequency().get(diceRoll) == null) {
                getPossibleDiceRollsMappedToFrequency().put(diceRoll, 1);
            } else {
                getPossibleDiceRollsMappedToFrequency().put(
                        diceRoll, getPossibleDiceRollsMappedToFrequency().get(diceRoll) + 1);
            }
            diceValues = getNextDiceValues(diceValues);
        }
    }

    private void populateScoreTable() {
        for (DiceRoll diceRoll : getPossibleDiceRollsMappedToFrequency().keySet()) {
            List<Score> scores = getScoreCalculator().calculateScoreValues(diceRoll.getDiceValues());
            for (ScoreType scoreType : ScoreType.values()) {
                Score score = getScoreByTypeFromScoreList(scores, scoreType);
                if (score != null) {
                    getScoreTable().put(new ScoreTableKey(diceRoll, score.getScoreType()), score.getScoreValue());
                }
            }
        }
    }
}
