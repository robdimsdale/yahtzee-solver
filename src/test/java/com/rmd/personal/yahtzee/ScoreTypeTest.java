package com.rmd.personal.yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTypeTest {

    @Test
    public void getFromIntThrowsExceptionForZero() {
        try {
            ScoreType.getSingleCountFromInt(0);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void getSingleCountFromIntThrowsExceptionForSeven() {
        try {
            ScoreType.getSingleCountFromInt(7); // SUPPRESS CHECKSTYLE magicNumber
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void getXOfAKindFromIntThrowsExceptionFor2() {
        try {
            ScoreType.getXOfAKindFromInt(2);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void getXOfAKindFromIntThrowsExceptionFor6() {
        try {
            ScoreType.getXOfAKindFromInt(6); // SUPPRESS CHECKSTYLE magicNumber
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void getXOfAKindFromIntReturnsYahtzeeCorrectly() {
        // Act & Assert
        assertEquals(ScoreType.YAHTZEE, ScoreType.getXOfAKindFromInt(5)); // SUPPRESS CHECKSTYLE magicNumber
    }
}
