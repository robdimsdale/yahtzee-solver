package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    private Score score = new Score(ScoreType.ONES, 2);

    @Test
    public void compareToThrowsNullPointerExceptionForNullOther() {
        try {
            score.compareTo(null);
        } catch (NullPointerException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void compareToReturnsZeroForEqualScoreValues() {
        // Arrange
        score.setScoreValue(2);

        // Act
        int result = score.compareTo(new Score(ScoreType.ONES, 2));

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void hashCodeReturnsSameForEqualObjects() {
        // Arrange
        Score otherScore = new Score(score.getScoreType(), score.getScoreValue());

        // Act & Assert
        assertEquals(score.hashCode(), otherScore.hashCode());
    }

    @Test
    public void hashCodeReturnsSameWhenAppliedInSuccession() {
        // Act & Assert
        int hashCode1 = score.hashCode();
        int hashCode2 = score.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsReturnsFalseForNullOther() {
        // Act & Assert
        assertFalse(score.equals(null));
    }

    @Test
    public void equalsReturnsTrueForEqualReferenceOther() {
        // Arrange
        Score otherScore = score;

        // Act & Assert
        assertTrue(score.equals(otherScore));
    }

    @Test
    public void equalsReturnsFalseForOtherInWrongHierarchy() {
        // Arrange
        Object otherObject = new Object();

        // Act & Assert
        assertFalse(score.equals(otherObject));
    }

    @Test
    public void equalsReturnsTrueForSameScoreTypeAndScoreValue() {
        // Arrange
        Score otherScore = new Score(score.getScoreType(), score.getScoreValue());

        // Act & Assert
        assertTrue(score.equals(otherScore));
    }

    @Test
    public void equalsReturnsFalseForDifferentScoreType() {
        // Arrange
        Score otherScore = new Score(ScoreType.TWOS, score.getScoreValue());

        // Act & Assert
        assertFalse(score.equals(otherScore));
    }

    @Test
    public void equalsReturnsFalseForDifferentScoreValue() {
        // Arrange
        Score otherScore = new Score(score.getScoreType(), score.getScoreValue() + 1);

        // Act & Assert
        assertFalse(score.equals(otherScore));
    }
}
