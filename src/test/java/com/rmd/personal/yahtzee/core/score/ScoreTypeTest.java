package com.rmd.personal.yahtzee.core.score;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTypeTest {

    @Test
    public void getFromIntThrowsExceptionForZero() {
        try {
            ScoreType.getSingleCountFromInt(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getSingleCountFromIntThrowsExceptionForSeven() {
        try {
            ScoreType.getSingleCountFromInt(7); // SUPPRESS CHECKSTYLE magicNumber
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getXOfAKindFromIntThrowsExceptionFor2() {
        try {
            ScoreType.getXOfAKindFromInt(2);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getXOfAKindFromIntThrowsExceptionFor6() {
        try {
            ScoreType.getXOfAKindFromInt(6); // SUPPRESS CHECKSTYLE magicNumber
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getXOfAKindFromIntReturnsYahtzeeCorrectly() {
        // Act & Assert
        assertEquals(ScoreType.YAHTZEE, ScoreType.getXOfAKindFromInt(5)); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void displayNameReturnsNonEmptyValueForAllTypes() throws Exception {
        // Act & Assert
        for (ScoreType scoreType : ScoreType.values()) {
            String displayName = scoreType.displayName();
            assertNotNull(displayName);
            assertTrue(displayName.length() > 0);
        }
    }

    @Test
    public void shortDisplayNameReturnsNonEmptyValueForAllTypes() throws Exception {
        // Act & Assert
        for (ScoreType scoreType : ScoreType.values()) {
            String shortDisplayName = scoreType.shortDisplayName();
            assertNotNull(shortDisplayName);
            assertTrue(shortDisplayName.length() > 0);
        }
    }
}
