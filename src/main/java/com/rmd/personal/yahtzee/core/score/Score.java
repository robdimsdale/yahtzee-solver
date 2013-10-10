package com.rmd.personal.yahtzee.core.score;

public class Score implements Comparable<Score> {

    private int scoreValue;
    private ScoreType scoreType;

    public Score(ScoreType scoreType, int scoreValue) {
        this.setScoreType(scoreType);
        this.setScoreValue(scoreValue);
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Score)) {
            return false;
        }

        Score otherScore = (Score) other;
        return otherScore.getScoreType() == this.getScoreType()
            && otherScore.getScoreValue() == this.getScoreValue();
    }

    @Override
    public int hashCode() {
        final int effectiveHashMultiplier = 37;
        return effectiveHashMultiplier * getScoreType().hashCode() + getScoreValue();
    }

    @Override
    public int compareTo(Score other) {
        if (other == null) {
            throw new NullPointerException();
        }

        if (this.getScoreValue() < other.getScoreValue()) {
            return -1;
        }

        if (this.getScoreValue() > other.getScoreValue()) {
            return 1;
        }

        return 0;
    }
}
