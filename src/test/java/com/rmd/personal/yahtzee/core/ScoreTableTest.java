package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ScoreTableTest {

    private ScoreTable scoreTable = new ScoreTable();

    private ScoreTableKey key = new ScoreTableKey(new DiceRoll(new int[]{1, 1, 1, 1, 1}), ScoreType.ONES);

    @Test
    public void putAndGetOperateCorrectly() {
        // Arrange
        int expectedResult = 1;
        // Act
        scoreTable.put(key, expectedResult);
        int result = scoreTable.get(key);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void keySetReturnsCorrectly() {
        // Arrange
        Set<ScoreTableKey> expectedKeyset = new HashSet<ScoreTableKey>();
        ScoreTableKey otherKey = new ScoreTableKey(key.getDiceRoll(), ScoreType.TWOS);
        expectedKeyset.add(key);
        expectedKeyset.add(otherKey);

        // Act
        scoreTable.put(key, 1);
        scoreTable.put(otherKey, 0);
        Set<ScoreTableKey> keySet = scoreTable.keySet();

        // Assert
        assertEquals(expectedKeyset, keySet);
    }

    @Test
    public void valuesReturnsCorrectly() {
        // Arrange
        Collection<Integer> expectedValues = new HashSet<Integer>();
        ScoreTableKey otherKey = new ScoreTableKey(key.getDiceRoll(), ScoreType.TWOS);
        expectedValues.add(1);
        expectedValues.add(0);

        // Act
        scoreTable.put(key, 1);
        scoreTable.put(otherKey, 0);
        Collection<Integer> values = scoreTable.values();

        // Assert
        assertEquals(expectedValues.size(), values.size());
        assertTrue(values.containsAll(expectedValues));
    }
}
