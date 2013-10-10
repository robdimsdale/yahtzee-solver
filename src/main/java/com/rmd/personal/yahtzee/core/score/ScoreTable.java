package com.rmd.personal.yahtzee.core.score;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public Set<ScoreTableKey> keySet() {
        return getScoreTable().keySet();
    }

    public Collection<Integer> values() {
        return getScoreTable().values();
    }
}
