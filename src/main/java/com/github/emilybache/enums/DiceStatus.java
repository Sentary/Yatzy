package com.github.emilybache.enums;

/**
 * @DiceStatus Represent the status of a dice element in a roll
 * @author Ismael
 */
public enum DiceStatus {
	D1(1, "One"), D2(2, "Two"), D3(3, "Three"), D4(4, "Four"), D5(5, "Five"), D6(6, "Six");

	// Scored number on the dice in a roll (1..6)
	private int number;

	// Label text of the corresponding dice
	private String label;

	DiceStatus(final int i, final String text) {
		this.number = i;
		this.label = text;
	}

	public int getNumber() {
		return number;
	}

	public String getLabel() {
		return label;
	}
}
