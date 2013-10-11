package com.rmd.personal.yahtzee.move;

import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.move.Move;
import com.rmd.personal.yahtzee.core.score.Score;
import com.rmd.personal.yahtzee.core.score.ScoreType;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MoveTest {

    private final double probability = 0.01;
    private final DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
    private final Score score = new Score(ScoreType.CHANCE, 5);

    private Move move = new Move(score, probability, diceRoll);

    @Test
    public void hashCodeReturnsSameForEqualObjects() {
        // Arrange
        Move other = new Move(move.getScore(), move.getProbability(), move.getDiceRoll());

        // Act & Assert
        assertEquals(move.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeReturnsSameWhenAppliedInSuccession() {
        // Act & Assert
        int hashCode1 = move.hashCode();
        int hashCode2 = move.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsReturnsFalseForNullOther() {
        // Arrange
        Object other = null;

        // Act & Assert
        assertFalse(move.equals(other));
    }

    @Test
    public void equalsReturnsTrueForEqualReferenceOther() {
        // Arrange
        Move other = move;

        // Act & Assert
        assertTrue(move.equals(other));
    }

    @Test
    public void equalsReturnsFalseForOtherInWrongHierarchy() {
        // Arrange
        Object otherObject = new Object();

        // Act & Assert
        assertFalse(move.equals(otherObject));
    }

    @Test
    public void equalsReturnsTrueForSameScoreProbabilityAndDiceRoll() {
        // Arrange
        Move other = new Move(move.getScore(), move.getProbability(), move.getDiceRoll());

        // Act & Assert
        assertTrue(move.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentScore() {
        // Arrange
        Score mockScore = mock(Score.class);
        Move other = new Move(mockScore, move.getProbability(), move.getDiceRoll());

        // Act & Assert
        assertFalse(move.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentProbability() {
        // Arrange
        Move other = new Move(move.getScore(), move.getProbability() - 1, move.getDiceRoll());

        // Act & Assert
        assertFalse(move.equals(other));
    }

    @Test
    public void equalsReturnsFalseForDifferentDiceRoll() {
        // Arrange
        DiceRoll mockDiceRoll = mock(DiceRoll.class);
        Move other = new Move(move.getScore(), move.getProbability(), mockDiceRoll);

        // Act & Assert
        assertFalse(move.equals(other));
    }

    @Test
    public void compareToReturns0WhenEqual() {
        // Arrange
        Move other = mock(Move.class);
        doReturn(move.getValue()).when(other).getValue();

        // Act & Assert
        assertEquals(0, move.compareTo(other));
    }

    @Test
    public void compareToReturnsNegativeWhenMoveValueIsLessThanOtherValue() {
        // Arrange
        Move other = mock(Move.class);
        doReturn(move.getValue() + 1).when(other).getValue();

        // Act & Assert
        assertTrue(move.compareTo(other) < 0);
    }

    @Test
    public void compareToReturnsPositiveWhenMoveValueIsGreaterThanOtherValue() {
        // Arrange
        Move other = mock(Move.class);
        doReturn(move.getValue() - 1).when(other).getValue();

        // Act & Assert
        assertTrue(move.compareTo(other) > 0);
    }
}
