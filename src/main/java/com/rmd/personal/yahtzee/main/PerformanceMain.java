package com.rmd.personal.yahtzee.main;

import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.diceroll.DiceRollTransitionCalculator;

import java.util.Date;

public final class PerformanceMain {

    private PerformanceMain() {
    }

    public static void main(String[] args) {
        new PerformanceMain().run();
    }

    private void run() {
        final int minIterations = 1;
        final int maxIterations = 5;
        for (int iterations = minIterations; iterations <= maxIterations; iterations++) {
            long startTime = new Date().getTime();
            for (int i = 1; i <= iterations; i++) {
                final int maxDice = 6;
                for (int d1 = 1; d1 < maxDice; d1++) {
                    for (int d2 = 1; d2 < maxDice; d2++) {
                        for (int d3 = 1; d3 < maxDice; d3++) {
                            for (int d4 = 1; d4 < maxDice; d4++) {
                                for (int d5 = 1; d5 < maxDice; d5++) {
                                    DiceRoll current = new DiceRoll(new int[]{d1, d2, d3, d4, d5});
                                    DiceRoll next = new DiceRoll(new int[]{2, 2, 2, 2, 2});
                                    final int remainingRolls = 3;
                                    DiceRollTransitionCalculator.getInstance()
                                            .getTransitionProbability(current, next, remainingRolls);
                                }
                            }
                        }
                    }
                }
            }
            long endTime = new Date().getTime();

            long duration = endTime - startTime;
            System.out.println("" + iterations + " iterations took " + duration + " ms");
        }
    }

}
