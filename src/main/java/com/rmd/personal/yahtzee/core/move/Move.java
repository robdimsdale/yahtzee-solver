package com.rmd.personal.yahtzee.core.move;

import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.score.Score;

public class Move implements Comparable<Move> {
    private Score score;
    private double probability;
    private DiceRoll diceRoll;

    public Move(Score score, double probability, DiceRoll diceRoll) {
        this.score = score;
        this.probability = probability;
        this.diceRoll = diceRoll;
    }

    public Score getScore() {
        return score;
    }

    public double getProbability() {
        return probability;
    }

    public DiceRoll getDiceRoll() {
        return diceRoll;
    }

    public double getValue() {
        return this.getProbability() * this.getScore().getScoreValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }

        Move otherMove = (Move) other;

        return otherMove.getDiceRoll().equals(this.getDiceRoll())
                && otherMove.getProbability() == this.getProbability()
                && otherMove.getScore().equals(this.getScore());
    }

    @Override
    public int hashCode() {
        final int effectiveHashMultiplier = 37;
        double hash = Math.pow(effectiveHashMultiplier, 1) * this.getProbability();
        hash += Math.pow(effectiveHashMultiplier, 2) * this.getScore().hashCode();
        hash += Math.pow(effectiveHashMultiplier, 3) * this.getDiceRoll().hashCode(); // SUPPRESS CHECKSTYLE magicNumber
        return (int) hash;
    }

    @Override
    public int compareTo(Move o) {
        return (int) Math.signum(this.getValue() - o.getValue());
    }
}
