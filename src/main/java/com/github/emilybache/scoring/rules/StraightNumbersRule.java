package com.github.emilybache.scoring.rules;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.scoring.ScoringRule;

/**
 * @ExactScoreRule Represent the rule corresponding to straight dice numbers The
 *                 This rule handle dynamically the rules: Small straight and
 *                 Large straight
 * @author Ismael
 */
public class StraightNumbersRule implements ScoringRule {

	private boolean isLargeStraight;

	// Mandatory dices that must be present in both straight rules: 2..5
	private static final List<DiceStatus> MANDATORY_STRAIGHT_DICES = Arrays.asList(DiceStatus.D2, DiceStatus.D3,
			DiceStatus.D4, DiceStatus.D5);

	/**
	 * StraightNumbersRule constructor having parameter:
	 * 
	 * @param isLargeStraight True if the large straight rule is applied, false if
	 *                        in case of small straight
	 */
	public StraightNumbersRule(final boolean isLargeStraight) {
		this.isLargeStraight = isLargeStraight;
	}

	@Override
	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus) {

		// Filter the map of identical dices by count to get only identical sets with 1
		// count (required for Straight rule)
		final Map<DiceStatus, Integer> identicalDiceStatusByCount = diceCountByStatus.entrySet().stream()
				.filter(entry -> entry.getValue() == 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		// Return the default score if there is no individual dice or there are less
		// than 5 sets
		if (MapUtils.isEmpty(identicalDiceStatusByCount) || identicalDiceStatusByCount.size() < 5) {
			return DEFAULT_SCORE;
		}

		// Return the default score if the individual dices do not include the mandatory
		// straight dices (2..5)
		if (!identicalDiceStatusByCount.keySet().containsAll(MANDATORY_STRAIGHT_DICES)) {
			return DEFAULT_SCORE;
		}

		// Return the default score if the dice 1 is missing with small straight is
		// applied or if dice 6 is missing with large straight applied
		if (!identicalDiceStatusByCount.containsKey(DiceStatus.D1) && !isLargeStraight
				|| !identicalDiceStatusByCount.containsKey(DiceStatus.D6) && isLargeStraight) {
			return DEFAULT_SCORE;
		}

		// Return the sum of dices
		return identicalDiceStatusByCount.entrySet().stream()
				.mapToInt(entry -> entry.getKey().getNumber() * entry.getValue()).sum();
	}

}
