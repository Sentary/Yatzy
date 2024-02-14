package com.github.emilybache.enums;


/**
 * @Category represent the category of the roll
 * @author Ismael
 */
public enum Category {
	CHANCE("Chance"),
	YATZY("Yatzy"),
	ONES("Ones"),
	TWOS("Twos"),
	THREES("Threes"),
	FOURS("Fours"),
	FIVES("Fives"),
	SIXES("Sixes"),
	PAIR("Pair"),
	TWO_PAIRS("Two pairs"),
	THREE_OF_A_KIND("Three of a kind"),
	FOUR_OF_A_KIND("Four of a kind"),
	SMALL_STRAIGHT("Small straight"),
	LARGE_STRAIGHT("Large straight"),
	FULL_HOUSE("Full house");

	// Label text of the corresponding category 
    private String label;

	Category(final String text) {
		this.label = text;
	}

	public String getLabel() {
		return label;
	}
}
