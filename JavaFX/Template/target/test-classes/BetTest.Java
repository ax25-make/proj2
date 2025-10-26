import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BetTest {

    @Test
    void testAddNumber() {
        Bet bet = new Bet(4, 1);
        assertTrue(bet.addNumber(5));
        assertTrue(bet.addNumber(10));
        assertTrue(bet.addNumber(15));
        assertTrue(bet.addNumber(20));
        assertFalse(bet.addNumber(25)); // exceeds 4 spots
        assertEquals(4, bet.getChosenNumbers().size());
    }

    @Test
    void testRemoveNumber() {
        Bet bet = new Bet(4, 1);
        bet.addNumber(5);
        bet.addNumber(10);
        assertTrue(bet.removeNumber(5));
        assertFalse(bet.removeNumber(99)); // not present
        assertEquals(1, bet.getChosenNumbers().size());
    }

    @Test
    void testAutoPick() {
        Bet bet = new Bet(4, 1);
        bet.autoPick();
        List<Integer> numbers = bet.getChosenNumbers();
        assertEquals(4, numbers.size());
        for (int num : numbers) {
            assertTrue(num >= 1 && num <= 80);
        }
    }

    @Test
    void testIsComplete() {
        Bet bet = new Bet(2, 1);
        assertFalse(bet.isComplete());
        bet.addNumber(1);
        assertFalse(bet.isComplete());
        bet.addNumber(2);
        assertTrue(bet.isComplete());
    }

    @Test
    void testReset() {
        Bet bet = new Bet(3, 1);
        bet.addNumber(1);
        bet.addNumber(2);
        bet.reset();
        assertEquals(0, bet.getChosenNumbers().size());
    }
}