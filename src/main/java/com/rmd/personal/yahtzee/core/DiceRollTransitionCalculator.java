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
        double transitionProbability = 0.0;
        double p = 1 / (double) Rules.getDieFaceCount();

        int n = countDifferent(current, next);
        if (n == 0) {
            return 1;
        }
        for (int k = 0; k <= n; k++) {
            transitionProbability += pow(p, n - k) * pow(p * (1 - p), k) * binomialCoefficient(n , k);
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
