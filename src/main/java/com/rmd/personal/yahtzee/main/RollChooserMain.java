package com.rmd.personal.yahtzee.main;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rmd.personal.yahtzee.core.Rules;
import com.rmd.personal.yahtzee.core.diceroll.DiceRoll;
import com.rmd.personal.yahtzee.core.move.Move;
import com.rmd.personal.yahtzee.core.move.MoveEnumerator;
import com.rmd.personal.yahtzee.core.score.ScoreType;

public final class RollChooserMain {

    private static final DecimalFormat THREE_DP_DECIMAL_FORMAT = new DecimalFormat("#.###");

    private RollChooserMain() {
    }

    public static void main(String[] args) {
        new RollChooserMain().run();
    }

    private void run() {
        DiceRoll current = new DiceRoll(new int[]{1, 2, 2, 3, 1}); // SUPPRESS CHECKSTYLE magicNumber
        final int maxRemainingRolls = Rules.getDiceRollsCount() - 1;

        Set<ScoreType> scoreTypeSet = new HashSet<ScoreType>();
        for (ScoreType scoreType : ScoreType.values()) {
            scoreTypeSet.add(scoreType);
        }
        scoreTypeSet.remove(ScoreType.CHANCE);
        scoreTypeSet.remove(ScoreType.FOUR_OF_A_KIND);

        System.out.println("Current: " + current);
        System.out.println("Included scoreTypes: " + scoreTypeSet);
        System.out.println();
        for (int remainingRolls = maxRemainingRolls; remainingRolls >= 0; remainingRolls--) {
            List<Move> moves = new MoveEnumerator().getMoves(scoreTypeSet, current, remainingRolls);

            System.out.println("Remaining rolls: " + remainingRolls);
            for (Move move : moves) {
                System.out.println(
                        move.getScore().getScoreType() + ": " + THREE_DP_DECIMAL_FORMAT.format(move.getValue()));
            }
            System.out.println();
        }
    }
}
