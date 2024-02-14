package com.github.emilybache.scoring.rules;

import java.util.EnumMap;

import org.apache.commons.collections4.MapUtils;

import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.scoring.ScoringRule;

/**
 * @ExactScoreRule Represent the rule corresponding to the case of scoring all
 *                 dices
 * @author Ismael
 */
public class AllDicesScoreRule implements ScoringRule {

	@Override
	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus) {

		// Return the default score if there is dices
		if (MapUtils.isEmpty(diceCountByStatus)) {
			return DEFAULT_SCORE;
		}

		// Return the sum of dices
		return diceCountByStatus.entrySet().stream().mapToInt(entry -> entry.getKey().getNumber() * entry.getValue())
				.sum();
	}

}
