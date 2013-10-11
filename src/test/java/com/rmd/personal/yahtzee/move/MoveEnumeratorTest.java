package com.rmd.personal.yahtzee.move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.move.Move;
import com.rmd.personal.yahtzee.core.move.MoveEnumerator;
import com.rmd.personal.yahtzee.core.score.ScoreType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveEnumeratorTest {

    private MoveEnumerator moveEnumerator = new MoveEnumerator();

    @Test
    public void getMovesThrowsExceptionForNullScoreTypes() {
        // Arrange
        Set<ScoreType> scoreTypes = null;
        DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 0;

        // Act & Assert
        try {
            moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("score"));
        }
    }

    @Test
    public void getMovesThrowsExceptionForNullDiceRoll() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        DiceRoll diceRoll = null;
        int remainingRolls = 0;

        // Act & Assert
        try {
            moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("dice"));
        }
    }

    @Test
    public void getMovesThrowsExceptionForNegativeDiceRoll() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = -1;

        // Act & Assert
        try {
            moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("remaining"));
        }
    }

    @Test
    public void getMovesReturnsEmptyListForEmptyScoreTypes() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 0;

        // Act
        List<Move> moves = moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);

        // Assert
        assertEquals(0, moves.size());
    }

    @Test
    public void getMovesReturnsSameNumberOfMovesAsInputForRemainingRollsNonZero() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 1;

        // Act & Assert
        for (int i = 0; i < ScoreType.values().length; i++) {
            scoreTypes.add(ScoreType.values()[i]);
            List<Move> moves = moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);
            assertEquals(i + 1, moves.size());
        }
    }

    @Test
    public void getMovesReturnsOnlyNonZeroScoresObtainedAtCurrentForRemainingRollsZero() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        scoreTypes.add(ScoreType.ONES);
        scoreTypes.add(ScoreType.TWOS);
        scoreTypes.add(ScoreType.THREE_OF_A_KIND);
        scoreTypes.add(ScoreType.FOUR_OF_A_KIND);

        DiceRoll diceRoll = new DiceRoll(new int[]{1, 2, 1, 1, 1});
        int remainingRolls = 0;

        // Act
        List<Move> moves = moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);

        // Assert
        Set<ScoreType> seenScoreTypes = new HashSet<ScoreType>();
        for (Move move : moves) {
            seenScoreTypes.add(move.getScore().getScoreType());
        }
        assertEquals(scoreTypes, seenScoreTypes);
    }

    @Test
    public void getMovesReturnsMovesSortedByValue() {
        // Arrange
        Set<ScoreType> scoreTypes = new HashSet<ScoreType>();
        scoreTypes.add(ScoreType.YAHTZEE);
        scoreTypes.add(ScoreType.ONES);
        DiceRoll diceRoll = new DiceRoll(new int[]{1, 1, 1, 1, 1});
        int remainingRolls = 0;

        // Act
        List<Move> moves = moveEnumerator.getMoves(scoreTypes, diceRoll, remainingRolls);

        // Assert
        assertEquals(2, moves.size());
        assertTrue(moves.get(0).getValue() > moves.get(1).getValue());
    }
}
