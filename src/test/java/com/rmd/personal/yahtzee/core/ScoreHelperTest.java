package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreHelperTest {

    private ScoreHelper scoreHelper = ScoreHelper.getInstance();

    @Test
    public void isSingleton() {
        // Act
        ScoreHelper sameInstance = ScoreHelper.getInstance();

        // Assert
        assertEquals(scoreHelper, sameInstance);
    }

    @Test
    public void hasPopulatedPossibleDiceRollsMappedToFrequency() {
        // Act & Assert
        assertTrue(ScoreHelper.getPossibleDiceRollsMappedToFrequency().values().size() > 0);
    }

    @Test
    public void hasPopulatedScoreTable() {
        // Act & Assert
        assertTrue(ScoreHelper.getScoreTable().values().size() > 0);
    }

    @Test
    public void hasPopulatedAveragesTable() {
        // Act & Assert
        assertTrue(ScoreHelper.getAveragesTable().values().size() > 0);
    }
}
