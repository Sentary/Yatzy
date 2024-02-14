package com.github.emilybache;

import static com.github.emilybache.enums.DiceStatus.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.emilybache.enums.Category;
import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.exception.YatzyError;
import com.github.emilybache.exception.YatzyException;
import com.github.emilybache.rules.ScoringRule;
import com.github.emilybache.rules.ScoringRuleFactory;

public class YatzyProcessor {
	
    public int roll(final Category category, final DiceStatus d1, final DiceStatus d2, final DiceStatus d3, final DiceStatus d4, final DiceStatus d5) {
    	try {
			final EnumMap<DiceStatus, Integer> diceCountByStatus = checkRollParameters(category, d1, d2, d3, d4, d5);
	    	ScoringRule scoringRule = ScoringRuleFactory.getScoringRule(category);
	    	return scoringRule.getRuleScore(diceCountByStatus);
		} catch (YatzyException e) {
			// TODO catch exception, log and return the default score
			return 0;
		}
    }

    /**
     * Check the roll inputs
     * @param category The given roll category (e.g: ONES, TWOS, PAIR...)
     * @param diceStatusArray The list of dices 
     * @return The map of dice counts by each scored number
     * @throws YatzyException When one of the given roll parameters is not valid
     */
    private EnumMap<DiceStatus, Integer> checkRollParameters(final Category category, final DiceStatus... diceStatusArray) throws YatzyException {
    	if(category == null) {
    		throw new YatzyException(YatzyError.ROLL_CATEGORY_NULL);
    	}
        final List<DiceStatus> nonNullDices = Arrays.stream(diceStatusArray).filter(dice -> dice != null).collect(Collectors.toList());
        if(nonNullDices == null || nonNullDices.size() < ScoringRule.DICE_TOTAL_COUNT) {
    		throw new YatzyException(YatzyError.ROLL_DICE_MESSING, ScoringRule.DICE_TOTAL_COUNT);
        }
        return nonNullDices.stream().collect(Collectors.groupingBy(x -> x, () -> new EnumMap<>(DiceStatus.class), Collectors.summingInt(x -> 1)));
	}
}



