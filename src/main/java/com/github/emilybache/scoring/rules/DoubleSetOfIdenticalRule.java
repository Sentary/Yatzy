package com.github.emilybache.scoring.rules;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.scoring.ScoringRule;

/**
 * @ExactScoreRule Represent the rule corresponding to double sets of identical
 *                 dices (having the same number) covering or not the full 5
 *                 dices The This rule handle dynamically the rules: Two pairs
 *                 and Full house
 * @author Ismael
 */
public class DoubleSetOfIdenticalRule implements ScoringRule {

	private boolean allDicesInvolved;

	/**
	 * DoubleSetOfIdenticalRule constructor having parameters:
	 * 
	 * @param allDicesInvolved True if all dice counts are considered (case of Full
	 *                         House), false only if the expected identical count is
	 *                         applied (case of Two Pairs)
	 */
	public DoubleSetOfIdenticalRule(final boolean allDicesInvolved) {
		this.allDicesInvolved = allDicesInvolved;
	}

	@Override
	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus) {

		// Filter the map of identical dices by count to get only identical sets with 2
		// or 3 counts (required for Two Pairs and Full House)
		final Map<DiceStatus, Integer> identicalDiceStatusByCount = diceCountByStatus.entrySet().stream()
				.filter(entry -> entry.getValue() == 2 || entry.getValue() == 3)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		// Return the default score if there is no identical sets or there are less than
		// 2 sets
		if (MapUtils.isEmpty(identicalDiceStatusByCount) || identicalDiceStatusByCount.size() < 2) {
			return DEFAULT_SCORE;
		}

		// Return the sum of dices with all counts applied only in case of full house
		return identicalDiceStatusByCount.entrySet().stream()
				.mapToInt(entry -> entry.getKey().getNumber() * (allDicesInvolved && entry.getValue() > 2 ? 3 : 2))
				.sum();
	}

}
