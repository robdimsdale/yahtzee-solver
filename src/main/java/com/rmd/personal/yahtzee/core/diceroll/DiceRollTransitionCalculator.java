package com.rmd.personal.yahtzee.core.diceroll;

import com.rmd.personal.yahtzee.core.Rules;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public final class DiceRollTransitionCalculator {

    private static final double P = 1 / (double) Rules.getDieFaceCount();

    private static final DiceRollTransitionCalculator INSTANCE = new DiceRollTransitionCalculator();

    private static Map<DiceRollTransitionTableKey, Double> cachedTransitions;

    static {
        cachedTransitions = new HashMap<DiceRollTransitionTableKey, Double>();
    }

    private DiceRollTransitionCalculator() {
    }

    public static DiceRollTransitionCalculator getInstance() {
        return INSTANCE;
    }

    private static Map<DiceRollTransitionTableKey, Double> getCachedTransitions() {
        return cachedTransitions;
    }

    public double getTransitionProbability(DiceRoll current, DiceRoll next, int remainingRolls) {
        final int maxN = countDifferent(current, next);

        // We are already where we want to be so probability of getting this is 1.
        // (This wouldn't be true if you weren't allowed to 'skip' rolls
        // i.e. if you always had to roll the maximum number of times).
        if (maxN == 0) {
            return 1.0;
        }

        // If we have no rolls remaining then it's impossible to transition to anything else.
        if (remainingRolls == 0) {
            return 0.0;
        }

        double summedPaths = sumPathsAtCurrentBranch(maxN, remainingRolls);

        return summedPaths * pow(P, maxN);
    }

    private double sumPathsAtCurrentBranch(int maxN, int remainingRolls) {
        remainingRolls--;
        if (remainingRolls == 0 || maxN == 0) {
            return 1.0;
        }

        DiceRollTransitionTableKey key = new DiceRollTransitionTableKey(maxN, remainingRolls);
        if (getCachedTransitions().containsKey(key)) {
            return getCachedTransitions().get(key);
        }

        double returnValue = 0.0;

        for (int i = 0; i <= maxN; i++) {
            double factor = pow(1 - P, i) * binomialCoefficient(maxN, i);
            returnValue += factor * sumPathsAtCurrentBranch(i, remainingRolls);
        }

        getCachedTransitions().put(key, returnValue);

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
