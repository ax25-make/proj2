import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KenoGameTest {

    private Bet bet1, bet4, bet8, bet10;
    private KenoGame game;

    @BeforeEach
    public void setup() {
        bet1 = new Bet(1, 1);
        bet4 = new Bet(4, 1);
        bet8 = new Bet(8, 1);
        bet10 = new Bet(10, 1);
        game = new KenoGame();
    }

    // ------------------ Bet Tests ------------------
    @Test
    public void testAddNumber() {
        assertTrue(bet1.addNumber(5));
        assertFalse(bet1.addNumber(5)); // duplicate
        assertEquals(List.of(5), bet1.getChosenNumbers());
    }

    @Test
    public void testRemoveNumber() {
        bet1.addNumber(7);
        assertTrue(bet1.removeNumber(7));
        assertFalse(bet1.removeNumber(7)); // already removed
    }

    @Test
    public void testIsComplete() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.addNumber(3);
        assertFalse(bet4.isComplete());
        bet4.addNumber(4);
        assertTrue(bet4.isComplete());
    }

    @Test
    public void testResetBet() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.reset();
        assertTrue(bet4.getChosenNumbers().isEmpty());
    }

    @Test
    public void testAutoPick() {
        bet8.autoPick();
        assertEquals(8, bet8.getChosenNumbers().size());
        // all numbers should be unique
        List<Integer> numbers = bet8.getChosenNumbers();
        long uniqueCount = numbers.stream().distinct().count();
        assertEquals(8, uniqueCount);
    }

    // ------------------ KenoGame Tests ------------------
    @Test
    public void testSetAndGetBet() {
        game.setBet(bet1);
        assertEquals(bet1, game.getCurrentBet());
    }

    @Test
    public void testGenerateDrawing() {
        ArrayList<Integer> draw = game.generateDrawing();
        assertEquals(20, draw.size());
        // numbers are within 1-80
        for (int n : draw) {
            assertTrue(n >= 1 && n <= 80);
        }
    }

    @Test
    public void testGetMatches() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.addNumber(3);
        bet4.addNumber(4);
        game.setBet(bet4);

        ArrayList<Integer> draw = new ArrayList<>();
        draw.add(1); draw.add(5); draw.add(2); draw.add(6);
        ArrayList<Integer> matches = game.getMatches(draw);
        assertEquals(List.of(1,2), matches);
    }

    @Test
    public void testCalculateWinnings1Spot() {
        bet1.addNumber(10);
        game.setBet(bet1);
        assertEquals(2, game.calculateWinnings(1));
        assertEquals(0, game.calculateWinnings(0));
    }

    @Test
    public void testCalculateWinnings4Spots() {
        bet4.addNumber(1); bet4.addNumber(2); bet4.addNumber(3); bet4.addNumber(4);
        game.setBet(bet4);
        assertEquals(1, game.calculateWinnings(2));
        assertEquals(5, game.calculateWinnings(3));
        assertEquals(75, game.calculateWinnings(4));
        assertEquals(81, game.getTotalWinnings()); // total accumulated
    }

    @Test
    public void testCalculateWinnings8Spots() {
        bet8.autoPick();
        game.setBet(bet8);
        assertEquals(2, game.calculateWinnings(4));
        assertEquals(12, game.calculateWinnings(5));
        assertEquals(50, game.calculateWinnings(6));
        assertEquals(750, game.calculateWinnings(7));
        assertEquals(10000, game.calculateWinnings(8));
    }

    @Test
    public void testCalculateWinnings10Spots() {
        bet10.autoPick();
        game.setBet(bet10);
        assertEquals(5, game.calculateWinnings(0));
        assertEquals(2, game.calculateWinnings(5));
        assertEquals(15, game.calculateWinnings(6));
        assertEquals(40, game.calculateWinnings(7));
        assertEquals(450, game.calculateWinnings(8));
        assertEquals(4250, game.calculateWinnings(9));
        assertEquals(100000, game.calculateWinnings(10));
    }

    @Test
    public void testTotalWinningsAccumulation() {
        bet1.addNumber(1);
        game.setBet(bet1);
        game.calculateWinnings(1);
        game.calculateWinnings(0);
        assertEquals(2, game.getTotalWinnings());
    }

    @Test
    public void testResetGame() {
        bet1.addNumber(1);
        game.setBet(bet1);
        game.calculateWinnings(1);
        game.resetGame();
        assertEquals(0, game.getTotalWinnings());
        assertTrue(game.getCurrentBet().getChosenNumbers().isEmpty());
    }

    @Test
    public void testNoDuplicatesInAutoPick() {
        bet10.autoPick();
        List<Integer> nums = bet10.getChosenNumbers();
        long uniqueCount = nums.stream().distinct().count();
        assertEquals(10, uniqueCount);
    }

    @Test
    public void testAddingMoreThanNumSpotsFails() {
        bet1.addNumber(1);
        assertFalse(bet1.addNumber(2));
    }

    @Test
    public void testMatchesEmptyDraw() {
        bet4.addNumber(1); bet4.addNumber(2);
        game.setBet(bet4);
        ArrayList<Integer> draw = new ArrayList<>();
        assertTrue(game.getMatches(draw).isEmpty());
    }

    @Test
    public void testMatchesNoPlayerNumbers() {
        bet4.addNumber(1); bet4.addNumber(2);
        game.setBet(bet4);
        ArrayList<Integer> draw = new ArrayList<>();
        draw.add(3); draw.add(4);
        assertTrue(game.getMatches(draw).isEmpty());
    }

    @Test
    public void testMatchesAllPlayerNumbers() {
        bet4.addNumber(1); bet4.addNumber(2);
        game.setBet(bet4);
        ArrayList<Integer> draw = new ArrayList<>();
        draw.add(1); draw.add(2);
        ArrayList<Integer> matches = game.getMatches(draw);
        assertEquals(2, matches.size());
        assertTrue(matches.contains(1));
        assertTrue(matches.contains(2));
    }

    @Test
    public void testGenerateDrawingIsRandom() {
        ArrayList<Integer> draw1 = game.generateDrawing();
        ArrayList<Integer> draw2 = game.generateDrawing();
        assertNotEquals(draw1, draw2); // very small chance this fails randomly
    }

    @Test
    public void testResetBetAfterAutoPick() {
        bet4.autoPick();
        bet4.reset();
        assertTrue(bet4.getChosenNumbers().isEmpty());
    }

    @Test
    public void testAddRemoveMultipleNumbers() {
        assertTrue(bet4.addNumber(1));
        assertTrue(bet4.addNumber(2));
        assertTrue(bet4.removeNumber(1));
        assertEquals(List.of(2), bet4.getChosenNumbers());
    }

    @Test
    public void testSetBetNull() {
        game.setBet(null);
        assertNull(game.getCurrentBet());
    }

    @Test
    public void testCalculateWinningsWithoutBet() {
        game.setBet(null);
        assertThrows(NullPointerException.class, () -> game.calculateWinnings(1));
    }

    @Test
    public void testTotalWinningsAfterMultipleBets() {
        bet1.addNumber(1);
        game.setBet(bet1);
        game.calculateWinnings(1);
        bet4.addNumber(1); bet4.addNumber(2); bet4.addNumber(3); bet4.addNumber(4);
        game.setBet(bet4);
        game.calculateWinnings(4);
        assertEquals(77, game.getTotalWinnings());
    }
}
