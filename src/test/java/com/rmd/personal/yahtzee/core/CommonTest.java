package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonTest {

    @Test
    public void binomialCoefficientThrowsExceptionForNegativeN() {
        // Arrange
        final int n = -1;
        final int k = 1;

        // Act & Assert
        try {
            Common.binomialCoefficient(n, k);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("n"));
        }
    }

    @Test
    public void binomialCoefficientThrowsExceptionForNLessThanK() {
        // Arrange
        final int n = 1;
        final int k = 2;

        // Act & Assert
        try {
            Common.binomialCoefficient(n, k);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("n"));
            assertTrue(e.getMessage().toLowerCase().contains("k"));
        }
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor0choose0() {
        // Arrange
        final int n = 0;
        final int k = 0;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor1choose0() {
        // Arrange
        final int n = 1;
        final int k = 0;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor1choose1() {
        // Arrange
        final int n = 1;
        final int k = 1;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor2choose0() {
        // Arrange
        final int n = 2;
        final int k = 0;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor2choose1() {
        // Arrange
        final int n = 2;
        final int k = 1;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 2;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor2choose2() {
        // Arrange
        final int n = 2;
        final int k = 2;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose0() {
        // Arrange
        final int n = 5;
        final int k = 0;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose1() {
        // Arrange
        final int n = 5;
        final int k = 1;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 5;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose2() {
        // Arrange
        final int n = 5;
        final int k = 2;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 10;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose3() {
        // Arrange
        final int n = 5;
        final int k = 3;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 10;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose4() {
        // Arrange
        final int n = 5;
        final int k = 4;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 5;
        assertEquals(expected, returned);
    }

    @Test
    public void binomialCoefficientReturnsCorrectlyFor5choose5() {
        // Arrange
        final int n = 5;
        final int k = 5;

        // Act
        int returned = Common.binomialCoefficient(n, k);

        // Assert
        final int expected = 1;
        assertEquals(expected, returned);
    }
}
