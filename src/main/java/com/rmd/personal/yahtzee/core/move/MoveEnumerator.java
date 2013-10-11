package com.rmd.personal.yahtzee.core.move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.diceroll.DiceRollTransitionCalculator;
import com.rmd.personal.yahtzee.core.score.Score;
import com.rmd.personal.yahtzee.core.score.ScoreCalculator;
import com.rmd.personal.yahtzee.core.score.ScoreType;

public class MoveEnumerator {

    private static final List<DiceRoll> ALL_DICE_ROLLS;

    static {
        ALL_DICE_ROLLS = new ArrayList<DiceRoll>();
        populateAllDiceRolls();
    }

    private static void populateAllDiceRolls() {
        final int maxDice = 6;
        for (int d1 = 1; d1 <= maxDice; d1++) {
            for (int d2 = 1; d2 <= maxDice; d2++) {
                for (int d3 = 1; d3 <= maxDice; d3++) {
                    for (int d4 = 1; d4 <= maxDice; d4++) {
                        for (int d5 = 1; d5 <= maxDice; d5++) {
                            getAllDiceRolls().add(new DiceRoll(new int[]{d1, d2, d3, d4, d5}));
                        }
                    }
                }
            }
        }
    }

    private static List<DiceRoll> getAllDiceRolls() {
        return ALL_DICE_ROLLS;
    }

    public List<Move> getMoves(Set<ScoreType> scoreTypes, DiceRoll current, int remainingRolls) {
        if (scoreTypes == null) {
            throw new IllegalArgumentException("Score types must not be null");
        }

        if (current == null) {
            throw new IllegalArgumentException("Current dice roll must not be null");
        }

        if (remainingRolls < 0) {
            throw new IllegalArgumentException("Remaining rolls not be negative.");
        }

        Map<ScoreType, Move> movesByScoreType = new HashMap<ScoreType, Move>();

        for (DiceRoll next : getAllDiceRolls()) {
            double probability = DiceRollTransitionCalculator.getInstance().getTransitionProbability(
                    current,
                    next,
                    remainingRolls);

            if (probability == 0) {
                continue;
            }

            List<Score> scores = new ScoreCalculator().calculateScoreValues(next);
            for (Score score : scores) {
                Move move = new Move(score, probability, next);
                ScoreType scoreType = score.getScoreType();

                if (!scoreTypes.contains(scoreType)) {
                    continue;
                }

                if (!movesByScoreType.containsKey(scoreType)
                        || movesByScoreType.get(scoreType).getValue() < move.getValue()) {
                    movesByScoreType.put(scoreType, move);
                }
            }
        }

        List<Move> moves = new ArrayList<Move>();
        for (Move move : movesByScoreType.values()) {
            moves.add(move);
        }

        Collections.sort(moves);
        Collections.reverse(moves);

        return moves;
    }
}
