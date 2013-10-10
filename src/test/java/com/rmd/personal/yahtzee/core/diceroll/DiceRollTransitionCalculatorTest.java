package com.rmd.personal.yahtzee.core.diceroll;

import org.junit.Test;

import static java.lang.Math.*;
import static org.junit.Assert.*;

public class DiceRollTransitionCalculatorTest {

    // Set the delta for the tests to be extremely small (e.g. 1 in 1 million million).
    private final double delta = 1 / 1000000000000.0; // SUPPRESS CHECKSTYLE magicNumber

    private DiceRollTransitionCalculator diceRollTransitionCalculator = DiceRollTransitionCalculator.getInstance();

    private DiceRollTransitionCalculator getDiceRollTransitionCalculator() {
        return diceRollTransitionCalculator;
    }

    @Test
    public void getTransitionProbabilityReturns1ForN0R0() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);

        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1ForN0R1() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1ForN0R2() throws Exception {
        // Arrange
        final double expected = 1;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1ForN0R3() throws Exception {
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
    public void getTransitionProbabilityReturns1ForN0R4() throws Exception {
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
    public void getTransitionProbabilityReturns0ForN1R0() throws Exception {
        // Arrange
        final double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1over6ForN1R1() throws Exception {
        // Arrange
        final double expected = 1 / pow(6, 1); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns11over36ForN1R2() throws Exception {
        // Arrange
        final double expected = 11 / pow(6, 2); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 2});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns91over216ForN1R3() throws Exception {
        // Arrange
        final double expected = 91 / pow(6, 3); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns671over1296ForN1R4() throws Exception {
        // Arrange
        final double expected = 671 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns4561over7776ForN1R5() throws Exception {
        // Arrange
        final double expected = 4651 / pow(6, 5); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns0ForN2R0() throws Exception {
        // Arrange
        final double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1over36ForN2R1() throws Exception {
        // Arrange
        final double expected = 1 / pow(6, 2); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns121over1296ForN2R2() throws Exception {
        // Arrange
        final double expected = 121 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 1, 2, 2});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns8281over46656ForN2R3() throws Exception {
        // Arrange
        final double expected = 8281 / pow(6, 6); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns0ForN3R0() throws Exception {
        // Arrange
        final double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1over216ForN3R1() throws Exception {
        // Arrange
        final double expected = 1 / pow(6, 3); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1331over46656ForN3R2() throws Exception {
        // Arrange
        final double expected = 1331 / pow(6, 6); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 1, 2, 2, 2});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns753571over10077696ForN3R3() throws Exception {
        // Arrange
        final double expected = 753571 / pow(6, 9); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns0ForN4R0() throws Exception {
        // Arrange
        final double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1over1296ForN4R1() throws Exception {
        // Arrange
        final double expected = 1 / pow(6, 4); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns14641over1679616ForN4R2() throws Exception {
        // Arrange
        final double expected = 14641 / pow(6, 8); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{1, 2, 2, 2, 2});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns68574961over2176782336ForN4R3() throws Exception {
        // Arrange
        final double expected = 68574961 / pow(6, 12); // SUPPRESS CHECKSTYLE magicNumber
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
    public void getTransitionProbabilityReturns0ForN5R0() throws Exception {
        // Arrange
        final double expected = 0.0;
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        final int remainingRolls = 0;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns1over7776ForN5R1() throws Exception {
        // Arrange
        final double expected = 1 / pow(6, 5); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        final int remainingRolls = 1;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns161051over60466176ForN5R2() throws Exception {
        // Arrange
        final double expected = 161051 / pow(6, 10); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        final int remainingRolls = 2;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getTransitionProbabilityReturns161051over60466176ForN5R3() throws Exception {
        // Arrange
        final double expected = 6240321451L / pow(6, 15); // SUPPRESS CHECKSTYLE magicNumber
        DiceRoll currentDiceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        DiceRoll newDiceRoll = new DiceRoll(new int[]{2, 2, 2, 2, 2});
        final int remainingRolls = 3;

        // Act
        double actual = getDiceRollTransitionCalculator()
                .getTransitionProbability(currentDiceRoll, newDiceRoll, remainingRolls);


        // Assert
        assertEquals(expected, actual, delta);
    }
}
