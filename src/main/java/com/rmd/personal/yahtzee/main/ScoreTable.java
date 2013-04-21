package com.rmd.personal.yahtzee.main;

import java.util.HashMap;
import java.util.Map;

public class ScoreTable {

    private Map<ScoreTableKey, Integer> scoreTable = new HashMap<ScoreTableKey, Integer>();

    private Map<ScoreTableKey, Integer> getScoreTable() {
        return scoreTable;
    }

    public void put(ScoreTableKey key, Integer count) {
        getScoreTable().put(key, count);
    }

    public Integer get(ScoreTableKey key) {
        return getScoreTable().get(key);
    }
}
