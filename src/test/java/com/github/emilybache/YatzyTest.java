package com.github.emilybache;
import org.junit.*;
import static org.junit.Assert.*;
import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.enums.Category;

public class YatzyTest {

    YatzyProcessor yatzyProcessor = new YatzyProcessor();

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = yatzyProcessor.roll(Category.CHANCE, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1);
        assertEquals(expected, actual);
        assertEquals(16, yatzyProcessor.roll(Category.CHANCE, DiceStatus.D3, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1));
    }

    @Test public void yatzy_scores_50() {
        int expected = 50;
        int actual = yatzyProcessor.roll(Category.YATZY, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4);
        assertEquals(expected, actual);
        assertEquals(50, yatzyProcessor.roll(Category.YATZY, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6));
        assertEquals(0, yatzyProcessor.roll(Category.YATZY, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D6, DiceStatus.D3));
    }

    @Test public void test_1s() {
        assertTrue(yatzyProcessor.roll(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5) == 1);
        assertEquals(2, yatzyProcessor.roll(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D1, DiceStatus.D4, DiceStatus.D5));
        assertEquals(0, yatzyProcessor.roll(Category.ONES, DiceStatus.D6, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
        assertEquals(4, yatzyProcessor.roll(Category.ONES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D1, DiceStatus.D1, DiceStatus.D1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, yatzyProcessor.roll(Category.TWOS, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D2, DiceStatus.D6));
        assertEquals(10, yatzyProcessor.roll(Category.TWOS, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, yatzyProcessor.roll(Category.THREES, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D2, DiceStatus.D3));
        assertEquals(12, yatzyProcessor.roll(Category.THREES, DiceStatus.D2, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3));
    }

    @Test
    public void fours_test() 
    {
        assertEquals(12, yatzyProcessor.roll(Category.FOURS, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(8, yatzyProcessor.roll(Category.FOURS, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
        assertEquals(4, yatzyProcessor.roll(Category.FOURS, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void fives() {
        assertEquals(10, yatzyProcessor.roll(Category.FIVES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(15, yatzyProcessor.roll(Category.FIVES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
        assertEquals(20, yatzyProcessor.roll(Category.FIVES, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void sixes_test() {
        assertEquals(0, yatzyProcessor.roll(Category.SIXES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D4, DiceStatus.D5, DiceStatus.D5));
        assertEquals(6, yatzyProcessor.roll(Category.SIXES, DiceStatus.D4, DiceStatus.D4, DiceStatus.D6, DiceStatus.D5, DiceStatus.D5));
        assertEquals(18, yatzyProcessor.roll(Category.SIXES, DiceStatus.D6, DiceStatus.D5, DiceStatus.D6, DiceStatus.D6, DiceStatus.D5));
    }

    @Test
    public void one_pair() {
        assertEquals(6, yatzyProcessor.roll(Category.PAIR, DiceStatus.D3, DiceStatus.D4, DiceStatus.D3, DiceStatus.D5, DiceStatus.D6));
        assertEquals(10, yatzyProcessor.roll(Category.PAIR, DiceStatus.D5, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
        assertEquals(12, yatzyProcessor.roll(Category.PAIR, DiceStatus.D5, DiceStatus.D3, DiceStatus.D6, DiceStatus.D6, DiceStatus.D5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, yatzyProcessor.roll(Category.TWO_PAIRS, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(16, yatzyProcessor.roll(Category.TWO_PAIRS, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5));
    }

    @Test
    public void three_of_a_kind() 
    {
        assertEquals(9, yatzyProcessor.roll(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(15, yatzyProcessor.roll(Category.THREE_OF_A_KIND, DiceStatus.D5, DiceStatus.D3, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(9, yatzyProcessor.roll(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, yatzyProcessor.roll(Category.FOUR_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D5));
        assertEquals(20, yatzyProcessor.roll(Category.FOUR_OF_A_KIND, DiceStatus.D5, DiceStatus.D5, DiceStatus.D5, DiceStatus.D4, DiceStatus.D5));
        assertEquals(9, yatzyProcessor.roll(Category.THREE_OF_A_KIND, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3, DiceStatus.D3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, yatzyProcessor.roll(Category.SMALL_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(15, yatzyProcessor.roll(Category.SMALL_STRAIGHT, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D1));
        assertEquals(0, yatzyProcessor.roll(Category.SMALL_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, yatzyProcessor.roll(Category.LARGE_STRAIGHT, DiceStatus.D6, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5));
        assertEquals(20, yatzyProcessor.roll(Category.LARGE_STRAIGHT, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D6));
        assertEquals(0, yatzyProcessor.roll(Category.LARGE_STRAIGHT, DiceStatus.D1, DiceStatus.D2, DiceStatus.D2, DiceStatus.D4, DiceStatus.D5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, yatzyProcessor.roll(Category.FULL_HOUSE, DiceStatus.D6, DiceStatus.D2, DiceStatus.D2, DiceStatus.D2, DiceStatus.D6));
        assertEquals(0, yatzyProcessor.roll(Category.FULL_HOUSE, DiceStatus.D2, DiceStatus.D3, DiceStatus.D4, DiceStatus.D5, DiceStatus.D6));
    }
}
