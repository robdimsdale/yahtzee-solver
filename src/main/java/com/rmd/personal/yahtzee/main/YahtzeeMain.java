package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.Score;
import com.rmd.personal.yahtzee.core.ScoreCalculator;
import com.rmd.personal.yahtzee.core.ScoreType;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class YahtzeeMain {

    private Map<DiceRoll, Integer> possibleDiceRollsFrequency = new LinkedHashMap<DiceRoll, Integer>();
    private ScoreTable scoreTable = new ScoreTable();

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    private static final DecimalFormat TWO_DP_DECIMAL_FORMAT = new DecimalFormat("#.##");

    private YahtzeeMain() {
        populatePossibleDiceValues();
    }

    private Map<DiceRoll, Integer> getPossibleDiceRollsFrequency() {
        return possibleDiceRollsFrequency;
    }

    private ScoreCalculator getScoreCalculator() {
        return scoreCalculator;
    }

    private ScoreTable getScoreTable() {
        return scoreTable;
    }

    public static void main(String[] args) {
        YahtzeeMain yahtzeeMain = new YahtzeeMain();
        yahtzeeMain.printScoreTableHeader();
        yahtzeeMain.printScoreTable();
        yahtzeeMain.printScoreTableRowDivider();
        yahtzeeMain.printScoreTableHeaderRowTwo();
        yahtzeeMain.printScoreTableRowDivider();

        System.out.println();

        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(
                            MainScoreHelper.getAverageForScoreType(
                                    scoreType,
                                    false,
                                    yahtzeeMain.getPossibleDiceRollsFrequency(),
                                    yahtzeeMain.getScoreTable())));
        }

        System.out.println("\nExcluding zero-scoring rolls:");
        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(
                            MainScoreHelper.getAverageForScoreType(
                                    scoreType,
                                    true,
                                    yahtzeeMain.getPossibleDiceRollsFrequency(),
                                    yahtzeeMain.getScoreTable())));
        }
    }

    private void printScoreTableHeader() {
        printScoreTableHeaderRowOne();
        printScoreTableHeaderRowTwo();
        printScoreTableRowDivider();
    }

    private void printScoreTableHeaderRowOne() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\tDice (count)");
        for (int i = 0; i < DiceRoll.NUMBER_OF_DICE - 1; i++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("|\tScore");
        System.out.println(stringBuilder.toString());

    }

    private void printScoreTableHeaderRowTwo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        for (int i = 0; i < DiceRoll.NUMBER_OF_DICE; i++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("\t\t|\t");
        for (ScoreType scoreType : ScoreType.values()) {
            stringBuilder.append(scoreType.shortDisplayName());
            stringBuilder.append("\t");
        }
        System.out.println(stringBuilder.toString());
    }

    private void printScoreTableRowDivider() {
        final String tabAsDashes = "----";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tabAsDashes);
        for (int i = 0; i < DiceRoll.NUMBER_OF_DICE; i++) {
            stringBuilder.append(tabAsDashes);
        }
        stringBuilder.append(tabAsDashes);
        stringBuilder.append(tabAsDashes);
        stringBuilder.append("|---");
        stringBuilder.append("-------------------------------------------------");
        System.out.println(stringBuilder.toString());
    }

    private void printScoreTable() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (DiceRoll diceRoll : getPossibleDiceRollsFrequency().keySet()) {
            for (int diceValue : diceRoll.getDiceValues()) {
                stringBuilder.append("\t");
                stringBuilder.append(diceValue);
            }
            stringBuilder.append("\t");
            int diceRolls = getPossibleDiceRollsFrequency().get(diceRoll);

            final int tripleDigits = 100;
            if (diceRolls < tripleDigits) {
                stringBuilder.append(" ");
            }

            final int doubleDigits = 10;
            if (diceRolls < doubleDigits) {
                stringBuilder.append(" ");
            }

            stringBuilder.append("(");
            stringBuilder.append(diceRolls);
            stringBuilder.append(")");

            stringBuilder.append("\t|");

            List<Score> scores = getScoreCalculator().calculateScoreValues(diceRoll.getDiceValues());
            for (ScoreType scoreType : ScoreType.values()) {
                stringBuilder.append("\t");
                Score score = MainScoreHelper.getScoreByTypeFromScoreList(scores, scoreType);
                if (score != null) {
                    stringBuilder.append(score.getScoreValue());
                    getScoreTable().put(new ScoreTableKey(diceRoll, score.getScoreType()), score.getScoreValue());
                } else {
                    stringBuilder.append("");
                }
            }

            if (i < getPossibleDiceRollsFrequency().size() - 1) {
                stringBuilder.append("\n");
            }
            i++;
        }
        System.out.println(stringBuilder.toString());
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
        while (MainScoreHelper.getDiceValuesAsInt(diceValues) <= MainScoreHelper.getDiceValuesAsInt(endDiceValues)) {
            DiceRoll diceRoll = new DiceRoll(diceValues);
            if (getPossibleDiceRollsFrequency().get(diceRoll) == null) {
                getPossibleDiceRollsFrequency().put(diceRoll, 1);
            } else {
                getPossibleDiceRollsFrequency().put(diceRoll, getPossibleDiceRollsFrequency().get(diceRoll) + 1);
            }
            diceValues = MainScoreHelper.getNextDiceValues(diceValues);
        }
    }
}
