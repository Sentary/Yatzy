package com.github.emilybache.enums;


/**
 * @IdenticalScoringStatus Represent the scoring approach when identical dices with the same number are found
 * EXPECTED_COUNT Is applied when the exact expected number of identical is applied (e.g: scoring 6 in case of Two-pairs rule applied on 1,1,2,2,2)
 * ALL Is applied when the found count of identical is applied (e.g: scoring 6 in case of Full House rule applied on 1,1,2,2,2)
 * @author Ismael
 */
public enum IdenticalScoringStatus {
	EXPECTED_COUNT,
	ALL;
}
