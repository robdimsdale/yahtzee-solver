package com.rmd.personal.yahtzee.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ScoreCalculator {

    private static final ScoreCalculator INSTANCE = new ScoreCalculator();

    private ScoreCalculator() {
    }

    public static ScoreCalculator getInstance() {
        return INSTANCE;
    }

    public List<Score> calculateScoreValues(int... diceValues) {
        if (diceValues == null || diceValues.length != Rules.getNumberOfDice()) {
            throw new IllegalArgumentException("must have " + Rules.getNumberOfDice() + " dice values!");
        }

        List<Score> scores = new ArrayList<Score>();

        scores.add(xOfAKindScore(Rules.getNumberOfDiceRequiredForThreeOfAKind(), diceValues));
        scores.add(xOfAKindScore(Rules.getNumberOfDiceRequiredForFourOfAKind(), diceValues));
        scores.add(xOfAKindScore(Rules.getNumberOfDiceRequiredForYahtzee(), diceValues));

        scores.add(longStraightScore(diceValues));
        scores.add(shortStraightScore(diceValues));

        scores.add(fullHouseScore(diceValues));

        for (int i = 1; i <= Rules.getDieFaceCount(); i++) {
            scores.add(summedSingleValueScore(i, diceValues));
        }

        scores.add(chanceScore(diceValues));

        List<Score> prunedScores = pruneEmptyScores(scores);

        Collections.sort(prunedScores);
        Collections.reverse(prunedScores);
        return prunedScores;
    }

    private List<Score> pruneEmptyScores(List<Score> scores) {
        List<Score> prunedScores = new ArrayList<Score>();
        for (Score score : scores) {
            if (score.getScoreValue() > 0) {
                prunedScores.add(score);
            }
        }
        return prunedScores;
    }

    private Score xOfAKindScore(int numberOfDiceRequired, int[] diceValues) {
        Score score = new Score(ScoreType.getXOfAKindFromInt(numberOfDiceRequired), 0);

        Map<Integer, Integer> diceValueToCountMap = diceValueCountMap(diceValues);

        for (Integer count : diceValueToCountMap.values()) {
            if (count >= numberOfDiceRequired) {
                if (numberOfDiceRequired == Rules.getNumberOfDiceRequiredForYahtzee()) {
                    score.setScoreValue(Rules.getYahtzeeInitialScoreValue());
                } else {
                    score.setScoreValue(sumAllDiceValues(diceValues));
                }
            }
        }
        return score;
    }

    private Map<Integer, Integer> diceValueCountMap(int[] diceValues) {
        Map<Integer, Integer> diceValueToCountMap = new HashMap<Integer, Integer>();

        for (int diceValue : diceValues) {
            if (diceValueToCountMap.containsKey(diceValue)) {
                diceValueToCountMap.put(diceValue, diceValueToCountMap.get(diceValue) + 1);
            } else {
                diceValueToCountMap.put(diceValue, 1);
            }
        }
        return diceValueToCountMap;
    }

    private int sumAllDiceValues(int[] diceValues) {
        int sum = 0;
        for (int diceValue : diceValues) {
            sum += diceValue;
        }
        return sum;
    }

    private Score fullHouseScore(int[] diceValues) {
        Score score = new Score(ScoreType.FULL_HOUSE, 0);

        Map<Integer, Integer> diceValueCountMap = diceValueCountMap(diceValues);

        final Set<Integer> fullHouseCountSet = new HashSet<Integer>();
        fullHouseCountSet.add(2);
        fullHouseCountSet.add(3); // SUPPRESS CHECKSTYLE magicNumber
        if (diceValueCountMap.keySet().size() == 2) {
            if (diceValueCountMap.values().containsAll(fullHouseCountSet)) {
                score.setScoreValue(Rules.getFullHouseScoreValue());
            }
        }
        return score;
    }

    private Score longStraightScore(int[] diceValues) {
        Score score = new Score(ScoreType.LONG_STRAIGHT, 0);
        int[] strippedArray = stripDuplicates(diceValues);
        Arrays.sort(strippedArray);

        final int minimumDiceRequiredForLongStraight = 5;
        if (strippedArray.length < minimumDiceRequiredForLongStraight) {
            return score;
        }

        for (int i = 1; i < strippedArray.length; i++) {
            if (strippedArray[i] != strippedArray[i - 1] + 1) {
                return score;
            }
        }
        score.setScoreValue(Rules.getLongStraightScoreValue());
        return score;
    }

    private Score shortStraightScore(int[] diceValues) {
        Score score = new Score(ScoreType.SHORT_STRAIGHT, 0);
        int[] strippedArray = stripDuplicates(diceValues);
        Arrays.sort(strippedArray);

        final int minimumDiceRequiredForShortStraight = 4;
        if (strippedArray.length < minimumDiceRequiredForShortStraight) {
            return score;
        }

        boolean isShortStraight1 = true;
        for (int i = 1; i < strippedArray.length - 1; i++) {
            if (strippedArray[i] != strippedArray[i - 1] + 1) {
                isShortStraight1 = false;
                break;
            }
        }

        boolean isShortStraight2 = true;
        for (int i = 2; i < strippedArray.length; i++) {
            if (strippedArray[i] != strippedArray[i - 1] + 1) {
                isShortStraight2 = false;
                break;
            }
        }

        if (isShortStraight1 || isShortStraight2) {
            score.setScoreValue(Rules.getShortStraightScoreValue());
        }
        return score;
    }

    private int[] stripDuplicates(int[] diceValues) {
        List<Integer> strippedList = new ArrayList<Integer>();
        for (int value : diceValues) {
            if (!strippedList.contains(value)) {
                strippedList.add(value);
            }
        }
        int[] strippedArray = new int[strippedList.size()];
        for (int i = 0; i < strippedList.size(); i++) {
            strippedArray[i] = strippedList.get(i);
        }
        return strippedArray;
    }

    private Score summedSingleValueScore(int valueOfInterest, int[] diceValues) {
        int sum = 0;
        for (int diceValue : diceValues) {
            if (diceValue == valueOfInterest) {
                sum += diceValue;
            }
        }
        return new Score(ScoreType.getSingleCountFromInt(valueOfInterest), sum);
    }

    private Score chanceScore(int[] diceValues) {
        return new Score(ScoreType.CHANCE, sumAllDiceValues(diceValues));
    }
}
