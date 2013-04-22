package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.ScoreCalculator;
import com.rmd.personal.yahtzee.core.ScoreHelper;

public final class YahtzeeMain {

    private PrintHelper printHelper;

    private YahtzeeMain() {
        // Eagerly instantiate the singleton classes.
        ScoreCalculator.getInstance();
        ScoreHelper.getInstance();
        printHelper = new PrintHelper();
    }

    public static void main(String[] args) {
        new YahtzeeMain().run();
    }

    private void run() {
        printHelper.printScoreTable();
        System.out.println();

        printHelper.printScoreTypeAverages();
        System.out.println();

        printHelper.printAveragesTable();
    }
}
