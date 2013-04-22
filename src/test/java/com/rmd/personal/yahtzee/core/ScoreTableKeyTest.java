package com.rmd.personal.yahtzee.core;

import com.rmd.personal.yahtzee.core.DiceRoll;
import com.rmd.personal.yahtzee.core.ScoreTableKey;
import com.rmd.personal.yahtzee.core.ScoreType;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTableKeyTest {

    private DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
    private ScoreTableKey key = new ScoreTableKey(diceRoll, ScoreType.ONES);

    @Test
    public void hashCodeReturnsSameForEqualObjects() {
        // Arrange
        ScoreTableKey other = new ScoreTableKey(key.getDiceRoll(), key.getScoreType());

        // Act & Assert
        assertEquals(key.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeReturnsSameWhenAppliedInSuccession() {
        // Act & Assert
        int hashCode1 = key.hashCode();
        int hashCode2 = key.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsReturnsFalseForNullOther() {
        // Act & Assert
        assertFalse(key.equals(null));
    }

    @Test
    public void equalsReturnsTrueForEqualReferenceOther() {
        // Arrange
        ScoreTableKey other = key;

        // Act & Assert
        assertTrue(key.equals(other));
    }

    @Test
    public void equalsReturnsFalseForOtherInWrongHierarchy() {
        // Arrange
        Object otherObject = new Object();

        // Act & Assert
        assertFalse(key.equals(otherObject));
    }

    @Test
    public void equalsReturnsTrueForSameScoreTypeAndScoreValue() {
        // Arrange
        ScoreTableKey other = new ScoreTableKey(key.getDiceRoll(), key.getScoreType());

        // Act & Assert
        assertTrue(key.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentScoreType() {
        // Arrange
        ScoreTableKey other = new ScoreTableKey(key.getDiceRoll(), ScoreType.TWOS);

        // Act & Assert
        assertFalse(key.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentScoreValue() {
        // Arrange
        ScoreTableKey other = new ScoreTableKey(new DiceRoll(new int[]{1, 2, 1, 2, 2}), key.getScoreType());

        // Act & Assert
        assertFalse(key.equals(other));
    }
}
