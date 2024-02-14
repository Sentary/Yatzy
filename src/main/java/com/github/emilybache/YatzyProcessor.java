package com.github.emilybache;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import com.github.emilybache.enums.Category;
import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.exception.YatzyError;
import com.github.emilybache.exception.YatzyException;
import com.github.emilybache.scoring.ScoringRule;
import com.github.emilybache.scoring.ScoringRuleFactory;
import com.github.emilybache.utils.YatzyLogger;
import com.github.emilybache.utils.YatzyUtils;

public class YatzyProcessor {

	/**
	 * The single entry point method for the Dice roll scoring processor
	 * 
	 * @param category The category to be considered during the scoring (ONES,
	 *                 TWOES, FULL HOUSE...)
	 * @param d1       The first dice of the group
	 * @param d2       The second dice of the group
	 * @param d3       The third dice of the group
	 * @param d4       The fourth dice of the group
	 * @param d5       The fifth dice of the group
	 * @return The score of the roll based on the given category and dice set
	 * @throws YatzyException thrown in case of an invalid roll parameter, e.g: null
	 *                        category or a null dice
	 */
	public int roll(final Category category, final DiceStatus d1, final DiceStatus d2, final DiceStatus d3,
			final DiceStatus d4, final DiceStatus d5) throws YatzyException {
		int score = 0;
		try {
			final EnumMap<DiceStatus, Integer> diceCountByStatus = checkRollParameters(category, d1, d2, d3, d4, d5);
			ScoringRule scoringRule = ScoringRuleFactory.getScoringRule(category);
			score = scoringRule.getRuleScore(diceCountByStatus);
		} catch (YatzyException yatzyException) {
			YatzyLogger.logError(yatzyException);
			throw yatzyException;
		} catch (Exception exception) {
			throw new YatzyException(YatzyError.APPLICATION_ERROR, exception);
		}

		String dicesInfo = YatzyUtils.getRollDicesInfo(d1, d2, d3, d4, d5);
		YatzyLogger.logInfo("Yatzy roll score according category [" + category.getLabel() + "] for the valid dices ["
				+ dicesInfo + "]: " + score);
		return score;
	}

	/**
	 * Check the roll inputs
	 * 
	 * @param category        The given roll category (e.g: ONES, TWOS, PAIR...)
	 * @param diceStatusArray The list of dices
	 * @return The map of dice counts by each scored number
	 * @throws YatzyException When one of the given roll parameters is not valid
	 */
	private EnumMap<DiceStatus, Integer> checkRollParameters(final Category category,
			final DiceStatus... diceStatusArray) throws YatzyException {
		if (category == null) {
			throw new YatzyException(YatzyError.ROLL_CATEGORY_NULL);
		}
		final List<DiceStatus> nonNullDices = Arrays.stream(diceStatusArray).filter(dice -> dice != null)
				.collect(Collectors.toList());
		if (nonNullDices == null || nonNullDices.size() < ScoringRule.DICE_TOTAL_COUNT) {
			throw new YatzyException(YatzyError.ROLL_DICE_MESSING, ScoringRule.DICE_TOTAL_COUNT);
		}
		return nonNullDices.stream().collect(
				Collectors.groupingBy(x -> x, () -> new EnumMap<>(DiceStatus.class), Collectors.summingInt(x -> 1)));
	}

	public static void main(String[] args) throws YatzyException {
		YatzyProcessor yatzyProcessor = new YatzyProcessor();
		yatzyProcessor.roll(Category.SMALL_STRAIGHT, null, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5);
	}
}
