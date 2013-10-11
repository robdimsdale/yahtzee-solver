package com.rmd.personal.yahtzee.core.score;

import java.util.List;

import com.rmd.personal.yahtzee.core.Rules;
import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    public void throwsExceptionForNullDiceRoll() {
        // Arrange
        DiceRoll diceRoll = null;

        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceRoll);
            fail();
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
        }
    }

    @Test
    public void throwsExceptionForNullDiceValues() {
        // Arrange
        // By default a mock will return null for any method returning an object,
        // therefore we don't need to explicitly set the behavior of the mock.
        DiceRoll diceRoll = mock(DiceRoll.class);

        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceRoll);
            fail();
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
        }
    }

    @Test
    public void throwsExceptionForEmptyDiceValues() {
        // Arrange
        DiceRoll diceRoll = new DiceRoll(new int[]{});

        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceRoll);
            fail();
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
        }
    }

    @Test
    public void throwsExceptionForMoreThanFiveDiceValues() {
        // Arrange
        DiceRoll diceRoll = new DiceRoll(new int[] {1, 2, 1, 2, 1, 2});

        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceRoll);
            fail();
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
        }
    }

    @Test
    public void returnsScoresInOrderForEqualNumberOfSpecificDiceValues() {
        // Act
        List<Score> scores = scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 1, 2, 2, 3})); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(4, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        Assert.assertEquals(ScoreType.CHANCE, scores.get(0).getScoreType());
        assertEquals(9, scores.get(0).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(1).getScoreType());
        assertEquals(4, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREES, scores.get(2).getScoreType());
        assertEquals(3, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.ONES, scores.get(3).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(2, scores.get(3).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void returnedScoresIncludesFullHouseWhenValid() {
        // Act
        Score score = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 2, 1, 2, 2}))).get(0);

        // Assert
        assertEquals(ScoreType.FULL_HOUSE, score.getScoreType());
        Assert.assertEquals(Rules.getFullHouseScoreValue(), score.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesLongStraightWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 2, 3, 4, 5}))).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score2 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{6, 3, 4, 5, 2}))).get(0); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.LONG_STRAIGHT, score1.getScoreType());
        assertEquals(Rules.getLongStraightScoreValue(), score1.getScoreValue());

        assertEquals(ScoreType.LONG_STRAIGHT, score2.getScoreType());
        assertEquals(Rules.getLongStraightScoreValue(), score2.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesShortStraightWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 2, 3, 4, 6}))).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score2 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 3, 4, 5, 6}))).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score3 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{1, 2, 3, 3, 4}))).get(0); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.SHORT_STRAIGHT, score1.getScoreType());
        assertEquals(Rules.getShortStraightScoreValue(), score1.getScoreValue());

        assertEquals(ScoreType.SHORT_STRAIGHT, score2.getScoreType());
        assertEquals(Rules.getShortStraightScoreValue(), score2.getScoreValue());

        assertEquals(ScoreType.SHORT_STRAIGHT, score3.getScoreType());
        assertEquals(Rules.getShortStraightScoreValue(), score3.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesYahtzeeWhenValid() {
        // Act
        Score score = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{2, 2, 2, 2, 2}))).get(0);

        // Assert
        assertEquals(ScoreType.YAHTZEE, score.getScoreType());
        assertEquals(Rules.getYahtzeeInitialScoreValue(), score.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesFourOfAKindWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{2, 2, 2, 1, 2}))).get(1);
        Score score2 = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{3, 2, 2, 2, 2}))).get(1); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.FOUR_OF_A_KIND, score1.getScoreType());
        assertEquals(9, score1.getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FOUR_OF_A_KIND, score2.getScoreType());
        assertEquals(11, score2.getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void validYahtzeeIncludesYahtzeeAndFourOfAKindAndThreeOfAKind() {
        // Act
        List<Score> scores = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{2, 2, 2, 2, 2})));

        // Assert
        assertEquals(5, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.YAHTZEE, scores.get(0).getScoreType());
        assertEquals(Rules.getYahtzeeInitialScoreValue(), scores.get(0).getScoreValue());

        assertEquals(ScoreType.CHANCE, scores.get(1).getScoreType());
        assertEquals(10, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(2).getScoreType());
        assertEquals(10, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FOUR_OF_A_KIND, scores.get(3).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(10, scores.get(3).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREE_OF_A_KIND, scores.get(4).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(10, scores.get(4).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void validFullHouseIncludesThreeOfAKind() {
        // Act
        List<Score> scores = (scoreCalculator.calculateScoreValues(new DiceRoll(new int[]{2, 1, 2, 2, 1})));

        // Assert
        assertEquals(5, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FULL_HOUSE, scores.get(0).getScoreType());
        assertEquals(Rules.getFullHouseScoreValue(), scores.get(0).getScoreValue());

        assertEquals(ScoreType.CHANCE, scores.get(1).getScoreType());
        assertEquals(8, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREE_OF_A_KIND, scores.get(2).getScoreType());
        assertEquals(8, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(3).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(6, scores.get(3).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.ONES, scores.get(4).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(2, scores.get(4).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }
}
