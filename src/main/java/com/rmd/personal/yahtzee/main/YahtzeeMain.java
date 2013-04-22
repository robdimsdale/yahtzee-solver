package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.Rules;
import com.rmd.personal.yahtzee.core.ScoreCalculator;
import com.rmd.personal.yahtzee.core.ScoreTableKey;
import com.rmd.personal.yahtzee.core.ScoreType;

import java.text.DecimalFormat;

public final class YahtzeeMain {

    private static final DecimalFormat TWO_DP_DECIMAL_FORMAT = new DecimalFormat("#.##");

    private static final String TAB_AS_DASHES = "----";

    private YahtzeeMain() {
        // Eagerly instantiate the singleton classes.
        ScoreCalculator.getInstance();
        MainScoreHelper.getInstance();
    }

    public static void main(String[] args) {
        new YahtzeeMain().run();
    }

    private void run() {
        printScoreTable();
        System.out.println();

        printScoreTypeAverages();
        System.out.println();

        printAveragesTable();
    }

    private void printScoreTable() {
        printScoreTableHeader();
        printScoreTableBody();
        printScoreTableRowDivider();
        printScoreTableHeaderRowTwo();
        printScoreTableRowDivider();
    }

    private void printScoreTableHeader() {
        printScoreTableRowDivider();
        printScoreTableHeaderRowOne();
        printScoreTableHeaderRowTwo();
        printScoreTableRowDivider();
    }

    private void printScoreTableHeaderRowOne() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\tDice (count)");
        for (int i = 0; i < Rules.getNumberOfDice() - 1; i++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("|\tScore");
        System.out.println(stringBuilder.toString());

    }

    private void printScoreTableHeaderRowTwo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        for (int i = 0; i < Rules.getNumberOfDice(); i++) {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAB_AS_DASHES);
        for (int i = 0; i < Rules.getNumberOfDice(); i++) {
            stringBuilder.append(TAB_AS_DASHES);
        }
        stringBuilder.append(TAB_AS_DASHES);
        stringBuilder.append(TAB_AS_DASHES);
        stringBuilder.append("|---");
        for (int i = 0; i < ScoreType.values().length; i++) {
            stringBuilder.append(TAB_AS_DASHES);
        }
        System.out.println(stringBuilder.toString());
    }

    private void printScoreTableBody() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (DiceRoll diceRoll : MainScoreHelper.getPossibleDiceRollsMappedToFrequency().keySet()) {
            for (int diceValue : diceRoll.getDiceValues()) {
                stringBuilder.append("\t");
                stringBuilder.append(diceValue);
            }
            stringBuilder.append("\t");
            int possibleCount = MainScoreHelper.getPossibleDiceRollsMappedToFrequency().get(diceRoll);

            final int tripleDigits = 100;
            if (possibleCount < tripleDigits) {
                stringBuilder.append(" ");
            }

            final int doubleDigits = 10;
            if (possibleCount < doubleDigits) {
                stringBuilder.append(" ");
            }

            stringBuilder.append("(");
            stringBuilder.append(possibleCount);
            stringBuilder.append(")");

            stringBuilder.append("\t|");

            for (ScoreType scoreType : ScoreType.values()) {
                stringBuilder.append("\t");
                ScoreTableKey key = new ScoreTableKey(diceRoll, scoreType);
                Integer score = MainScoreHelper.getScoreTable().get(key);
                if (score != null) {
                    stringBuilder.append(score);
                }
            }

            if (i < MainScoreHelper.getPossibleDiceRollsMappedToFrequency().size() - 1) {
                stringBuilder.append("\n");
            }
            i++;
        }
        System.out.println(stringBuilder.toString());
    }

    private void printScoreTypeAverages() {
        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(MainScoreHelper.getAveragesTable().get(scoreType)));
        }

        System.out.println("\nExcluding zero-scoring rolls:");
        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(
                    MainScoreHelper.getAveragesTableExcludingZeroScores().get(scoreType)));
        }
    }

    private void printAveragesTable() {
        System.out.println("Difference from average (excluding zero-score):");
        printScoreTableHeader();
        printAveragesTableBody();
        printScoreTableRowDivider();
        printScoreTableHeaderRowTwo();
        printScoreTableRowDivider();
    }



    private void printAveragesTableBody() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (DiceRoll diceRoll : MainScoreHelper.getPossibleDiceRollsMappedToFrequency().keySet()) {
            for (int diceValue : diceRoll.getDiceValues()) {
                stringBuilder.append("\t");
                stringBuilder.append(diceValue);
            }
            stringBuilder.append("\t");
            int possibleCount = MainScoreHelper.getPossibleDiceRollsMappedToFrequency().get(diceRoll);

            final int tripleDigits = 100;
            if (possibleCount < tripleDigits) {
                stringBuilder.append(" ");
            }

            final int doubleDigits = 10;
            if (possibleCount < doubleDigits) {
                stringBuilder.append(" ");
            }

            stringBuilder.append("(");
            stringBuilder.append(possibleCount);
            stringBuilder.append(")");

            stringBuilder.append("\t|");

            for (ScoreType scoreType : ScoreType.values()) {
                stringBuilder.append("\t");
                ScoreTableKey key = new ScoreTableKey(diceRoll, scoreType);
                Integer score = MainScoreHelper.getScoreTable().get(key);
                if (score != null) {
                    long scoreDiff = Math.round(score - MainScoreHelper.getAveragesTable().get(scoreType));
                    if (scoreDiff >= 0) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(scoreDiff);
                }
            }

            if (i < MainScoreHelper.getPossibleDiceRollsMappedToFrequency().size() - 1) {
                stringBuilder.append("\n");
            }
            i++;
        }
        System.out.println(stringBuilder.toString());
    }
}
