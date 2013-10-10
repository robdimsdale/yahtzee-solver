package com.rmd.personal.yahtzee.core.diceroll;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceRollTransitionTableKeyTest {

    private final int maxN = 5;
    private final int remainingRolls = 2;
    private DiceRollTransitionTableKey key = new DiceRollTransitionTableKey(maxN, remainingRolls);

    @Test
    public void hashCodeReturnsSameForEqualObjects() {
        // Arrange
        DiceRollTransitionTableKey other = new DiceRollTransitionTableKey(key.getMaxN(), key.getRemainingRolls());

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
        // Arrange
        Object other = null;

        // Act & Assert
        assertFalse(key.equals(other));
    }

    @Test
    public void equalsReturnsTrueForEqualReferenceOther() {
        // Arrange
        DiceRollTransitionTableKey other = key;

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
    public void equalsReturnsTrueForSameMaxNAndRemainingRolls() {
        // Arrange
        DiceRollTransitionTableKey other = new DiceRollTransitionTableKey(key.getMaxN(), key.getRemainingRolls());

        // Act & Assert
        assertTrue(key.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentRemainingRolls() {
        // Arrange
        DiceRollTransitionTableKey other = new DiceRollTransitionTableKey(key.getMaxN(), key.getRemainingRolls() - 1);

        // Act & Assert
        assertFalse(key.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentMaxN() {
        // Arrange
        DiceRollTransitionTableKey other = new DiceRollTransitionTableKey(key.getMaxN() - 1, key.getRemainingRolls());

        // Act & Assert
        assertFalse(key.equals(other));
    }
}
