package com.rmd.personal.yahtzee.core;

import static java.lang.Math.*;

public final class DiceRollTransitionCalculator {

    private static final DiceRollTransitionCalculator INSTANCE = new DiceRollTransitionCalculator();

    private DiceRollTransitionCalculator() {
    }

    public static DiceRollTransitionCalculator getInstance() {
        return INSTANCE;
    }

    public double getTransitionProbability(DiceRoll current, DiceRoll next) {
        double p = 1 / (double) Rules.getDieFaceCount();

        final int maxN = countDifferent(current, next);
        if (maxN == 0) {
            return 1;
        }

        double transitionProbability = sumProbabilityAtCurrentBranch(p, maxN);

        return transitionProbability * pow(p, maxN);
    }

    private double sumProbabilityAtCurrentBranch(double p, int maxN) {
        double transitionProbability = 0.0;
        for (int n = 0; n <= maxN; n++) {
            transitionProbability += pow((1 - p), n) * binomialCoefficient(maxN , n);
        }
        return transitionProbability;
    }

    private int countDifferent(DiceRoll current, DiceRoll next) {
        int different = 0;
        for (int i = 0; i < current.getDiceValues().length; i++) {
            if (current.getDiceValues()[i] != next.getDiceValues()[i]) {
                different++;
            }
        }
        return different;
    }

    private int binomialCoefficient(int n, int k) {
        int numerator = factorial(n);
        int denominator = factorial(k) * factorial(n - k);
        return numerator / denominator;
    }

    private int factorial(int i) {
        if (i == 0) {
            return 1;
        }

        int factorial = 1;
        while (i-- > 0) {
            factorial += factorial * i;
        }
        return factorial;
    }
}
