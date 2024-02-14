package com.github.emilybache.scoring.rules;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Optional;

import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.scoring.ScoringRule;

/**
 * @ExactScoreRule Represent the rule corresponding to one group of identical
 *                 dices (having the same number) by the given count parameter
 *                 (expectedCountOfIdenticals) The This rule handle dynamically
 *                 the rules: Pair, Three of a kind, Four of a kind, Yatzy (five
 *                 of kind)
 * @author Ismael
 */
public class SingleSetOfIdenticalRule implements ScoringRule {

	private static final int YATZY_5_DICES_SCORE = 50;
	private int expectedCountOfIdenticals;

	/**
	 * SingleSetOfIdenticalRule constructor having parameters:
	 * 
	 * @param expectedCountOfIdenticals Expected count of identical dices with the
	 *                                  same number
	 */
	public SingleSetOfIdenticalRule(final int expectedCountOfIdenticals) {
		this.expectedCountOfIdenticals = expectedCountOfIdenticals;
	}

	@Override
	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus) {
		// Return the default rule score (0) if the expected count of identical is lower
		// than 2 (not handled) or higher that the dice count (5)
		if (expectedCountOfIdenticals < 2 || expectedCountOfIdenticals > DICE_TOTAL_COUNT
				|| diceCountByStatus == null) {
			return DEFAULT_SCORE;
		}

		// Filter the map of identical dices by count to get only count superior or
		// equal the expected count based on the rule
		// In case of multiple groups of identical dices (e.g case of two pairs), get
		// the max based on individual dice number
		final Optional<Entry<DiceStatus, Integer>> maxSimilarDice = diceCountByStatus.entrySet().stream()
				.filter(entry -> entry.getValue() >= expectedCountOfIdenticals)
				.max(Comparator.comparing(entry -> entry.getKey().getNumber()));

		// Return the default score if there is no identical count superior or equal the
		// expected count
		if (!maxSimilarDice.isPresent()) {
			return DEFAULT_SCORE;
		}

		if (expectedCountOfIdenticals == ScoringRule.DICE_TOTAL_COUNT) {
			// If number of identical is the number of dice, return 50
			return YATZY_5_DICES_SCORE;
		} else {
			return maxSimilarDice.get().getKey().getNumber() * expectedCountOfIdenticals;
		}
	}

}
