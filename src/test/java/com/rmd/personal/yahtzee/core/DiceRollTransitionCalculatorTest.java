package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceRollTransitionCalculatorTest {

    // Set the delta for the tests to be extremely small (e.g. 1 in 1 million million).
    private final double delta = 1 / 1000000000000.0; // SUPPRESS CHECKSTYLE magicNumber
    private final double p = 1 /6; // SUPPRESS CHECKSTYLE magicNumber

    private DiceRollTransitionCalculator diceRollTransitionCalculator = DiceRollTransitionCalculator.getInstance();

    private DiceRollTransitionCalculator getDiceRollTransitionCalculator() {
        return diceRollTransitionCalculator;
    }

    @Test
    public void testGetTransitionProbabilityForZeroDifferent() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testGetTransitionProbabilityForOneDifferent() throws Exception {
        // Arrange
        final double expected = 11 / 36.0; // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testGetTransitionProbabilityForTwoDifferent() throws Exception {
        // Arrange
        final double expected = 121 / 1296.0; // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testGetTransitionProbabilityForThreeDifferent() throws Exception {
        // Arrange
        final double expected = 1331 / 46656.0; // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testGetTransitionProbabilityForFourDifferent() throws Exception {
        // Arrange
        final double expected = 14641 / 1679616.0; // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testGetTransitionProbabilityForFiveDifferent() throws Exception {
        // Arrange
        final double expected = 161051 / 60466176.0; // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});

        // Act
        double actual = getDiceRollTransitionCalculator().getTransitionProbability(currentDiceRoll, newDiceRoll);

        // Assert
        assertEquals(expected, actual, delta);
    }
}
