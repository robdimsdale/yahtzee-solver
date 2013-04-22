package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.Score;
import com.rmd.personal.yahtzee.core.ScoreCalculator;
import com.rmd.personal.yahtzee.core.ScoreType;

import java.text.DecimalFormat;
import java.util.List;

public final class YahtzeeMain {

    private static final DecimalFormat TWO_DP_DECIMAL_FORMAT = new DecimalFormat("#.##");

    private ScoreCalculator scoreCalculator;
    private MainScoreHelper scoreHelper;

    private YahtzeeMain() {
        scoreCalculator = ScoreCalculator.getInstance();
        scoreHelper = MainScoreHelper.getInstance();
    }

    private ScoreCalculator getScoreCalculator() {
        return scoreCalculator;
    }

    private MainScoreHelper getScoreHelper() {
        return scoreHelper;
    }

    public static void main(String[] args) {
        YahtzeeMain yahtzeeMain = new YahtzeeMain();
        yahtzeeMain.printScoreTable();

        System.out.println();

        yahtzeeMain.printAverages();
    }

    private void printScoreTable() {
        printScoreTableHeader();
        printScoreTableBody();
        printScoreTableRowDivider();
        printScoreTableHeaderRowTwo();
        printScoreTableRowDivider();
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
        for (int i = 0; i < ScoreType.values().length; i++) {
            stringBuilder.append(tabAsDashes);
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
            int diceRolls = MainScoreHelper.getPossibleDiceRollsMappedToFrequency().get(diceRoll);

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
                Score score = getScoreHelper().getScoreByTypeFromScoreList(scores, scoreType);
                if (score != null) {
                    stringBuilder.append(score.getScoreValue());
                } else {
                    stringBuilder.append("");
                }
            }

            if (i < MainScoreHelper.getPossibleDiceRollsMappedToFrequency().size() - 1) {
                stringBuilder.append("\n");
            }
            i++;
        }
        System.out.println(stringBuilder.toString());
    }

    private void printAverages() {
        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(getScoreHelper().getAverageForScoreType(scoreType, false)));
        }

        System.out.println("\nExcluding zero-scoring rolls:");
        for (ScoreType scoreType : ScoreType.values()) {
            System.out.println(scoreType.displayName() + " average: "
                    + TWO_DP_DECIMAL_FORMAT.format(getScoreHelper().getAverageForScoreType(scoreType, true)));
        }
    }
}
