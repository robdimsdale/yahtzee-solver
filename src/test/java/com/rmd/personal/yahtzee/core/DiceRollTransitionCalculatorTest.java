package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import static java.lang.Math.*;
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
    public void getTransitionProbabilityReturnsCorrectlyForN0R0() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN0R1() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN0R2() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN0R3() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN0R4() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        final int remainingRolls = 4;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R0() throws Exception {
        // Arrange
        double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R1() throws Exception {
        // Arrange
        double expected = 1 / pow(6, 1); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R2() throws Exception {
        // Arrange
        double expected = 11 / pow(6, 2); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R3() throws Exception {
        // Arrange
        double expected = 91 / pow(6, 3); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R4() throws Exception {
        // Arrange
        double expected = 671 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 4;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN1R5() throws Exception {
        // Arrange
        double expected = 4651 / pow(6, 5); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 5;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN2R0() throws Exception {
        // Arrange
        double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN2R1() throws Exception {
        // Arrange
        double expected = 1 / pow(6, 2); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN2R2() throws Exception {
        // Arrange
        double expected = 121 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN2R3() throws Exception {
        // Arrange
        double expected = 8281 / pow(6, 6); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN3R0() throws Exception {
        // Arrange
        double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN3R1() throws Exception {
        // Arrange
        double expected = 1 / pow(6, 3); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN3R2() throws Exception {
        // Arrange
        double expected = 1331 / pow(6, 6); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN3R3() throws Exception {
        // Arrange
        double expected = 753571 / pow(6, 9); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN4R0() throws Exception {
        // Arrange
        double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN4R1() throws Exception {
        // Arrange
        double expected = 1 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN4R2() throws Exception {
        // Arrange
        double expected = 14641 / pow(6, 8); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN4R3() throws Exception {
        // Arrange
        double expected = 68574961 / pow(6, 12); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN5R0() throws Exception {
        // Arrange
        double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN5R1() throws Exception {
        // Arrange
        double expected = 1 / pow(6, 5); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturnsCorrectlyForN5R2() throws Exception {
        // Arrange
        double expected = 161051 / pow(6, 10); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }
}
