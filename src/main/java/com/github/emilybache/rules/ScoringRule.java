package com.github.emilybache.rules;

import java.util.EnumMap;

import com.github.emilybache.enums.DiceStatus;

public interface ScoringRule {

	public static final int DEFAULT_SCORE = 0;
	public static final int DICE_TOTAL_COUNT = 5;

	public int getRuleScore(final EnumMap<DiceStatus, Integer> diceCountByStatus);

}
