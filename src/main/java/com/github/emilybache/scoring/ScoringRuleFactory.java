package com.github.emilybache.scoring;

import com.github.emilybache.enums.Category;
import com.github.emilybache.scoring.rules.AllDicesScoreRule;
import com.github.emilybache.scoring.rules.DoubleSetOfIdenticalRule;
import com.github.emilybache.scoring.rules.ExactScoreRule;
import com.github.emilybache.scoring.rules.SingleSetOfIdenticalRule;
import com.github.emilybache.scoring.rules.StraightNumbersRule;

import static com.github.emilybache.enums.DiceStatus.*;

public class ScoringRuleFactory {

	private ScoringRuleFactory() {
		throw new IllegalStateException("Factory class");
	}

	public static ScoringRule getScoringRule(Category category) {
		switch (category) {
		case CHANCE:
			return new AllDicesScoreRule();
		case YATZY:
			return new SingleSetOfIdenticalRule(5);
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
			return new SingleSetOfIdenticalRule(2);
		case TWO_PAIRS:
			return new DoubleSetOfIdenticalRule(false);
		case THREE_OF_A_KIND:
			return new SingleSetOfIdenticalRule(3);
		case FOUR_OF_A_KIND:
			return new SingleSetOfIdenticalRule(4);
		case SMALL_STRAIGHT:
			return new StraightNumbersRule(false);
		case LARGE_STRAIGHT:
			return new StraightNumbersRule(true);
		case FULL_HOUSE:
			return new DoubleSetOfIdenticalRule(true);
		default:
			return new SingleSetOfIdenticalRule(0);
		}
	}
}
