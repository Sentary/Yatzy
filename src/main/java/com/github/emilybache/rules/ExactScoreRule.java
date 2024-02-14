package com.github.emilybache.rules;

import java.util.EnumMap;

import com.github.emilybache.enums.DiceStatus;

/**
 * @ExactScoreRule Represent a the exact rule that is configured by the desired exact dice score parameter (exactDiceStatus)
 * This rule handle dynamically the rules (ones, twos, threes, fours, fives and sixes)
 * @author Ismael
 */
public class ExactScoreRule implements ScoringRule {
	
   private DiceStatus exactDiceStatus;

   // The constructor has the parameter exactDiceStatus that represent the given exact dice score for comparison  
   public ExactScoreRule(DiceStatus exactDiceStatus) {
	   this.exactDiceStatus = exactDiceStatus;
   }

	@Override
	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus) {

		// Return the default rule score (0) if the desired exact score in null or there is no scored dice matching the exact score 
		if(exactDiceStatus == null || diceCountByStatus == null || !diceCountByStatus.containsKey(exactDiceStatus)) {
			return DEFAULT_SCORE;
		}
		return exactDiceStatus.getNumber() * diceCountByStatus.get(exactDiceStatus);
	}   
   
}
