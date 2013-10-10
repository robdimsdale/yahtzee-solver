package com.rmd.personal.yahtzee.core.diceroll;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceRollTest {

    private DiceRoll diceRoll = new DiceRoll(new int[]{1, 2, 1, 1, 2});

    @Test
    public void hashCodeReturnsSameForEqualObjects() {
        // Arrange
        DiceRoll other = new DiceRoll(diceRoll.getDiceValues());

        // Act & Assert
        assertEquals(diceRoll.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeReturnsSameWhenAppliedInSuccession() {
        // Act & Assert
        int hashCode1 = diceRoll.hashCode();
        int hashCode2 = diceRoll.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsReturnsFalseForNullOther() {
        // Arrange
        Object nullObject = null;

        // Act & Assert
        assertFalse(diceRoll.equals(nullObject));
    }

    @Test
    public void equalsReturnsTrueForEqualReferenceOther() {
        // Arrange
        DiceRoll other = diceRoll;

        // Act & Assert
        assertTrue(diceRoll.equals(other));
    }

    @Test
    public void equalsReturnsFalseForOtherInWrongHierarchy() {
        // Arrange
        Object otherObject = new Object();

        // Act & Assert
        assertFalse(diceRoll.equals(otherObject));
    }

    @Test
    public void equalsReturnsTrueForSameValues() {
        // Arrange
        DiceRoll other = new DiceRoll(diceRoll.getDiceValues());

        // Act & Assert
        assertTrue(diceRoll.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentValues() {
        // Arrange
        DiceRoll other = new DiceRoll(new int[]{1, 1, 1, 1, 1});

        // Act & Assert
        assertFalse(diceRoll.equals(other));
    }
}
