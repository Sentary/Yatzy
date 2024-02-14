package com.github.emilybache;

import java.util.EnumMap;

import com.github.emilybache.enums.DiceStatus;

/**
 * @DiceRoll Represent a roll in the Yatzy game
 */
public class DiceRoll {

   //
   private DiceStatus[] diceStatusArray;

   // Number of dices by each status within a roll
   private EnumMap<DiceStatus, Integer> diceCountByStatus = new EnumMap<>(DiceStatus.class);
   
   public DiceRoll () {
	   
   }
   
}
