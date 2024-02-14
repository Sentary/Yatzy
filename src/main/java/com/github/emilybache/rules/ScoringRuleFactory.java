package com.github.emilybache.rules;

import com.github.emilybache.enums.Category;
import static com.github.emilybache.enums.DiceStatus.*;

public class ScoringRuleFactory {
    
	private ScoringRuleFactory() {
        throw new IllegalStateException("Factory class");
	}

	public static ScoringRule getScoringRule(Category category) {
		switch (category) {
		case CHANCE:
			return new ManyOfKindRule(0);
		case YATZY:
			return new ManyOfKindRule(5);
		case ONES:
			return new ExactScoreRule(D1);
		case TWOS:
			return new ExactScoreRule(D2);
		case THREES:
			return new ExactScoreRule(D3);
		case FOURS:
			return new ExactScoreRule(D4);
		case FIVES:
			return new ExactScoreRule(D5);
		case SIXES:
			return new ExactScoreRule(D6);
		case PAIR:
			return new ManyOfKindRule(2);
		case TWO_PAIRS:
			return new ManyOfKindRule(0);
		case THREE_OF_A_KIND:
			return new ManyOfKindRule(3);
		case FOUR_OF_A_KIND:
			return new ManyOfKindRule(4);
		case SMALL_STRAIGHT:
			return new ManyOfKindRule(0);
		case LARGE_STRAIGHT:
			return new ManyOfKindRule(0);
		case FULL_HOUSE:
			return new ManyOfKindRule(0);
		default:
			return new ManyOfKindRule(0);
		}
	}
}
