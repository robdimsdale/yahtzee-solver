package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    public void throwsExceptionForNullDiceValues() {
        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(null);
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void throwsExceptionForEmptyDiceValues() {
        // Arrange
        int[] diceValues = new int[]{};

        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceValues);
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void throwsExceptionForMoreThanFiveDiceValues() {
        // Arrange
        int[] diceValues = {1, 2, 1, 2, 1, 2};
        // Act & Assert
        try {
            scoreCalculator.calculateScoreValues(diceValues);
        } catch (IllegalArgumentException e) {
            // Assert
            assertNotNull(e);
            return;
        }
        fail();
    }

    @Test
    public void returnsScoresInOrderForEqualNumberOfSpecificDiceValues() {
        // Act
        List<Score> scores = scoreCalculator.calculateScoreValues(1, 1, 2, 2, 3); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(4, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.CHANCE, scores.get(0).getScoreType());
        assertEquals(9, scores.get(0).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(1).getScoreType());
        assertEquals(4, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREES, scores.get(2).getScoreType());
        assertEquals(3, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.ONES, scores.get(3).getScoreType());
        assertEquals(2, scores.get(3).getScoreValue());
    }

    @Test
    public void returnedScoresIncludesFullHouseWhenValid() {
        // Act
        Score score = (scoreCalculator.calculateScoreValues(1, 2, 1, 2, 2)).get(0);

        // Assert
        assertEquals(ScoreType.FULL_HOUSE, score.getScoreType());
        assertEquals(ScoreCalculator.FULL_HOUSE_SCORE_VALUE, score.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesLongStraightWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(1, 2, 3, 4, 5)).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score2 = (scoreCalculator.calculateScoreValues(6, 3, 4, 5, 2)).get(0); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.LONG_STRAIGHT, score1.getScoreType());
        assertEquals(ScoreCalculator.LONG_STRAIGHT_SCORE_VALUE, score1.getScoreValue());

        assertEquals(ScoreType.LONG_STRAIGHT, score2.getScoreType());
        assertEquals(ScoreCalculator.LONG_STRAIGHT_SCORE_VALUE, score2.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesShortStraightWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(1, 2, 3, 4, 6)).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score2 = (scoreCalculator.calculateScoreValues(1, 3, 4, 5, 6)).get(0); // SUPPRESS CHECKSTYLE magicNumber
        Score score3 = (scoreCalculator.calculateScoreValues(1, 2, 3, 3, 4)).get(0); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.SHORT_STRAIGHT, score1.getScoreType());
        assertEquals(ScoreCalculator.SHORT_STRAIGHT_SCORE_VALUE, score1.getScoreValue());

        assertEquals(ScoreType.SHORT_STRAIGHT, score2.getScoreType());
        assertEquals(ScoreCalculator.SHORT_STRAIGHT_SCORE_VALUE, score2.getScoreValue());

        assertEquals(ScoreType.SHORT_STRAIGHT, score3.getScoreType());
        assertEquals(ScoreCalculator.SHORT_STRAIGHT_SCORE_VALUE, score3.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesYahtzeeWhenValid() {
        // Act
        Score score = (scoreCalculator.calculateScoreValues(2, 2, 2, 2, 2)).get(0);

        // Assert
        assertEquals(ScoreType.YAHTZEE, score.getScoreType());
        assertEquals(ScoreCalculator.YAHTZEE_INITIAL_SCORE_VALUE, score.getScoreValue());
    }

    @Test
    public void returnedScoresIncludesFourOfAKindWhenValid() {
        // Act
        Score score1 = (scoreCalculator.calculateScoreValues(2, 2, 2, 1, 2)).get(1);
        Score score2 = (scoreCalculator.calculateScoreValues(3, 2, 2, 2, 2)).get(1); // SUPPRESS CHECKSTYLE magicNumber

        // Assert
        assertEquals(ScoreType.FOUR_OF_A_KIND, score1.getScoreType());
        assertEquals(9, score1.getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FOUR_OF_A_KIND, score2.getScoreType());
        assertEquals(11, score2.getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void validYahtzeeIncludesYahtzeeAndFourOfAKindAndThreeOfAKind() {
        // Act
        List<Score> scores = (scoreCalculator.calculateScoreValues(2, 2, 2, 2, 2));

        // Assert
        assertEquals(5, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.YAHTZEE, scores.get(0).getScoreType());
        assertEquals(ScoreCalculator.YAHTZEE_INITIAL_SCORE_VALUE, scores.get(0).getScoreValue());

        assertEquals(ScoreType.CHANCE, scores.get(1).getScoreType());
        assertEquals(10, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(2).getScoreType());
        assertEquals(10, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FOUR_OF_A_KIND, scores.get(3).getScoreType());
        assertEquals(10, scores.get(3).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREE_OF_A_KIND, scores.get(4).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(10, scores.get(4).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }

    @Test
    public void validFullHouseIncludesThreeOfAKind() {
        // Act
        List<Score> scores = (scoreCalculator.calculateScoreValues(2, 1, 2, 2, 1));

        // Assert
        assertEquals(5, scores.size()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.FULL_HOUSE, scores.get(0).getScoreType());
        assertEquals(ScoreCalculator.FULL_HOUSE_SCORE_VALUE, scores.get(0).getScoreValue());

        assertEquals(ScoreType.CHANCE, scores.get(1).getScoreType());
        assertEquals(8, scores.get(1).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.THREE_OF_A_KIND, scores.get(2).getScoreType());
        assertEquals(8, scores.get(2).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.TWOS, scores.get(3).getScoreType());
        assertEquals(6, scores.get(3).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber

        assertEquals(ScoreType.ONES, scores.get(4).getScoreType()); // SUPPRESS CHECKSTYLE magicNumber
        assertEquals(2, scores.get(4).getScoreValue()); // SUPPRESS CHECKSTYLE magicNumber
    }
}
