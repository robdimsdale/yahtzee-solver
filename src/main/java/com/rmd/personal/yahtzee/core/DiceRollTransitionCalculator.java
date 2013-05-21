package com.rmd.personal.yahtzee.core;

import static java.lang.Math.*;

public final class DiceRollTransitionCalculator {

    private static final double P = 1 / (double) Rules.getDieFaceCount();
    private static final int X = Rules.getDieFaceCount();

    private static final DiceRollTransitionCalculator INSTANCE = new DiceRollTransitionCalculator();

    private DiceRollTransitionCalculator() {
    }

    public static DiceRollTransitionCalculator getInstance() {
        return INSTANCE;
    }

    public double getTransitionProbability(DiceRoll current, DiceRoll next, int remainingRolls) {
        final int maxN = countDifferent(current, next);

        if (maxN == 0) {
            return 1.0;
        }

        if (remainingRolls == 0) {
            return 0.0;
        }

        double summedProbability;
        if (Rules.getDiceRollsCount() == 1) {
            summedProbability = 1.0;
        } else {
            final int currentDepth = 0;
            summedProbability = sumProbabilityAtCurrentBranch(maxN, remainingRolls, currentDepth);
        }

        return summedProbability * pow(P, maxN);
    }

    private double sumProbabilityAtCurrentBranch(int maxN, int remainingRolls, int currentDepth) {
        currentDepth++;
        double returnValue = 0.0;

        if (currentDepth >= remainingRolls) {
            return 1.0;
        }

        for (int i = 0; i <= maxN; i++) {
            double factor = pow(1 - P, i) * binomialCoefficient(maxN, i);
            double subSum = sumProbabilityAtCurrentBranch(i, remainingRolls, currentDepth);
            returnValue += factor * subSum;
        }

        return returnValue;
    }

    private double probabilityForTwoRemaining(int maxN) {
        double returnValue = 0.0;
        for (int i = 0; i <= maxN; i++) {
            double factor = pow(1 - P, i) * binomialCoefficient(maxN, i);
            returnValue += factor;
        }
        return returnValue;
    }

    private double probabilityForThreeRemaining(int maxN) {
        double returnValue = 0.0;
        for (int i = 0; i <= maxN; i++) {
            double factorI = pow(1 - P, i) * binomialCoefficient(maxN, i);
            double sumJ = 0.0;
            for (int j = 0; j <= i; j++) {
                double factorJ = pow(1 - P, j) * binomialCoefficient(i, j);
                sumJ += factorJ;
            }
            returnValue += factorI * sumJ;
        }
        return returnValue;
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
