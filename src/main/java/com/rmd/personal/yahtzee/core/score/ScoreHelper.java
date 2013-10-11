package com.rmd.personal.yahtzee.core.score;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rmd.personal.yahtzee.core.Rules;
import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;

public final class ScoreHelper {

    private static final ScoreHelper INSTANCE;

    private static final Map<DiceRoll, Integer> POSSIBLE_DICE_ROLLS_MAPPED_TO_FREQUENCY;
    private static final ScoreTable SCORE_TABLE;
    private static final Map<ScoreType, Double> AVERAGES_TABLE;
    private static final Map<ScoreType, Double> AVERAGES_TABLE_EXCLUDING_ZERO_SCORES;

    static {
        POSSIBLE_DICE_ROLLS_MAPPED_TO_FREQUENCY = new LinkedHashMap<DiceRoll, Integer>();
        SCORE_TABLE = new ScoreTable();
        AVERAGES_TABLE = new HashMap<ScoreType, Double>();
        AVERAGES_TABLE_EXCLUDING_ZERO_SCORES = new HashMap<ScoreType, Double>();

        INSTANCE = new ScoreHelper();
    }

    private ScoreHelper() {
        populatePossibleDiceValues();
        populateScoreTable();
        populateAveragesTables();
    }

    public static ScoreHelper getInstance() {
        return INSTANCE;
    }

    private ScoreCalculator getScoreCalculator() {
        return new ScoreCalculator();
    }

    public static Map<DiceRoll, Integer> getPossibleDiceRollsMappedToFrequency() {
        return POSSIBLE_DICE_ROLLS_MAPPED_TO_FREQUENCY;
    }

    public static ScoreTable getScoreTable() {
        return SCORE_TABLE;
    }

    public static Map<ScoreType, Double> getAveragesTable() {
        return AVERAGES_TABLE;
    }

    public static Map<ScoreType, Double> getAveragesTableExcludingZeroScores() {
        return AVERAGES_TABLE_EXCLUDING_ZERO_SCORES;
    }

    private void populatePossibleDiceValues() {
        int[] startDiceValues = new int[Rules.getNumberOfDice()];
        for (int i = 0; i < startDiceValues.length; i++) {
            startDiceValues[i] = 1;
        }

        int[] endDiceValues = new int[Rules.getNumberOfDice()];
        for (int i = 0; i < endDiceValues.length; i++) {
            endDiceValues[i] = Rules.getDieFaceCount();
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
            List<Score> scores = getScoreCalculator().calculateScoreValues(diceRoll);
            for (ScoreType scoreType : ScoreType.values()) {
                Score score = getScoreByTypeFromScoreList(scores, scoreType);
                if (score != null) {
                    getScoreTable().put(new ScoreTableKey(diceRoll, score.getScoreType()), score.getScoreValue());
                }
            }
        }
    }

    private void populateAveragesTables() {
        for (ScoreType scoreType : ScoreType.values()) {
            getAveragesTableExcludingZeroScores().put(scoreType, getAverageForScoreType(scoreType, true));
            getAveragesTable().put(scoreType, getAverageForScoreType(scoreType, false));
        }
    }

    private Score getScoreByTypeFromScoreList(List<Score> scoreList, ScoreType scoreType) {
        for (Score score : scoreList) {
            if (score.getScoreType() == scoreType) {
                return score;
            }
        }
        return null;
    }

    private int[] getNextDiceValues(int[] diceValues) {
        return getDiceValuesFromInt(getDiceValuesAsInt(diceValues) + 1);
    }

    private int getDiceValuesAsInt(int[] diceValues) {
        final int n = diceValues.length;
        int diceValueAsInt = 0;
        for (int i = 0; i < n; i++) {
            diceValueAsInt += (diceValues[i]) * Math.pow(Rules.getDieFaceCount(), (n - i - 1));
        }
        return diceValueAsInt;
    }

    private int[] getDiceValuesFromInt(int diceValuesAsInt) {
        diceValuesAsInt -= minimumValue();
        final int n = Rules.getNumberOfDice();
        int[] diceValues = new int[n];

        for (int i = 0; i < n; i++) {
            double index = Math.pow(Rules.getDieFaceCount(), (n - i - 1));
            diceValues[i] = (int) Math.floor((double) diceValuesAsInt / index);
            diceValuesAsInt -= diceValues[i] * index;
            diceValues[i] += 1;
        }
        return diceValues;
    }

    private double getAverageForScoreType(ScoreType scoreType, boolean excludeZeroScore) {
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
        int[] diceValues = new int[Rules.getNumberOfDice()];
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = 1;
        }
        return getDiceValuesAsInt(diceValues);
    }
}
