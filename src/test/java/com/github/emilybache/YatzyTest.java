package com.github.emilybache;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.emilybache.enums.Category;
import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.exception.YatzyError;
import com.github.emilybache.exception.YatzyException;
import com.github.emilybache.utils.YatzyUtils;

public class YatzyTest {

    YatzyProcessor yatzyProcessor = new YatzyProcessor();

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = checkValidRollWith(Category.CHANCE, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1);
        assertEquals(expected, actual);
        assertEquals(16, checkValidRollWith(Category.CHANCE, DiceStatus.D3, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1));
    }

    @Test public void yatzy_scores_50() {
        int expected = 50;
        int actual = checkValidRollWith(Category.YATZY, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4);
        assertEquals(expected, actual);
        assertEquals(50, checkValidRollWith(Category.YATZY, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6));
        assertEquals(0, checkValidRollWith(Category.YATZY, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D3));
    }

    @Test public void test_1s() {
        assertTrue(checkValidRollWith(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5) == 1);
        assertEquals(2, checkValidRollWith(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D1, DiceStatus.D4, DiceStatus.D5));
        assertEquals(0, checkValidRollWith(Category.ONES, DiceStatus.D6, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
        assertEquals(4, checkValidRollWith(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D1, DiceStatus.D1, DiceStatus.D1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, checkValidRollWith(Category.TWOS, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D2, DiceStatus.D6));
        assertEquals(10, checkValidRollWith(Category.TWOS, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, checkValidRollWith(Category.THREES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D2, DiceStatus.D3));
        assertEquals(12, checkValidRollWith(Category.THREES, DiceStatus.D2, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3));
    }

    @Test
    public void fours_test() 
    {
        assertEquals(12, checkValidRollWith(Category.FOURS, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(8, checkValidRollWith(Category.FOURS, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
        assertEquals(4, checkValidRollWith(Category.FOURS, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void fives() {
        assertEquals(10, checkValidRollWith(Category.FIVES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(15, checkValidRollWith(Category.FIVES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
        assertEquals(20, checkValidRollWith(Category.FIVES, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void sixes_test() {
        assertEquals(0, checkValidRollWith(Category.SIXES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(6, checkValidRollWith(Category.SIXES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D6, DiceStatus.D5, DiceStatus.D5));
        assertEquals(18, checkValidRollWith(Category.SIXES, DiceStatus.D6, DiceStatus.D5, DiceStatus.D6, DiceStatus.D6, DiceStatus.D5));
    }

    @Test
    public void one_pair() {
        assertEquals(6, checkValidRollWith(Category.PAIR, DiceStatus.D3, DiceStatus.D4, DiceStatus.D3, DiceStatus.D5, DiceStatus.D6));
        assertEquals(10, checkValidRollWith(Category.PAIR, DiceStatus.D5, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
        assertEquals(12, checkValidRollWith(Category.PAIR, DiceStatus.D5, DiceStatus.D3, DiceStatus.D6, DiceStatus.D6, DiceStatus.D5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, checkValidRollWith(Category.TWO_PAIRS, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(16, checkValidRollWith(Category.TWO_PAIRS, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void three_of_a_kind() 
    {
        assertEquals(9, checkValidRollWith(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(15, checkValidRollWith(Category.THREE_OF_A_KIND, DiceStatus.D5, DiceStatus.D3, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(9, checkValidRollWith(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, checkValidRollWith(Category.FOUR_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
        assertEquals(20, checkValidRollWith(Category.FOUR_OF_A_KIND, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(9, checkValidRollWith(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, checkValidRollWith(Category.SMALL_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(15, checkValidRollWith(Category.SMALL_STRAIGHT, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1));
        assertEquals(0, checkValidRollWith(Category.SMALL_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, checkValidRollWith(Category.LARGE_STRAIGHT, DiceStatus.D6, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(20, checkValidRollWith(Category.LARGE_STRAIGHT, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D6));
        assertEquals(0, checkValidRollWith(Category.LARGE_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, checkValidRollWith(Category.FULL_HOUSE, DiceStatus.D6, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D6));
        assertEquals(0, checkValidRollWith(Category.FULL_HOUSE, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D6));
    }
    
    @Test
    public void categoryNull() {
    	YatzyException exception = assertThrows(YatzyException.class, () -> {
    		yatzyProcessor.roll(null, DiceStatus.D6, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D6);
        });
        assertEquals(YatzyError.ROLL_CATEGORY_NULL.getCode(), exception.getCode());
    }
    
    @Test
    public void oneOrMultipleDicesNull() {
    	YatzyException exception = assertThrows(YatzyException.class, () -> {
    		yatzyProcessor.roll(Category.ONES, DiceStatus.D6, null, DiceStatus.D2, DiceStatus.D2, DiceStatus.D6);
        });
        assertEquals(YatzyError.ROLL_DICE_MESSING.getCode(), exception.getCode());
        
        exception = assertThrows(YatzyException.class, () -> {
        	yatzyProcessor.roll(Category.LARGE_STRAIGHT, null, DiceStatus.D3, DiceStatus.D4, null, DiceStatus.D6);
        });
        assertEquals(YatzyError.ROLL_DICE_MESSING.getCode(), exception.getCode());
    }
    
    private int checkValidRollWith(final Category category, final DiceStatus d1, final DiceStatus d2, final DiceStatus d3, final DiceStatus d4, final DiceStatus d5) {
    	try {
			return yatzyProcessor.roll(category, d1, d2, d3, d4, d5);
		} catch (YatzyException e) {
			fail(YatzyUtils.formatYatzyExceptionMessage(e));
			return 0;
		}
    }
}
