import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    // ---- Bet class tests ----

    @Test
    public void testBetConstructorAndGetters() {
        assertEquals(1, bet1.getNumSpots());
        assertEquals(1, bet1.getNumDrawings());
        assertTrue(bet1.getChosenNumbers().isEmpty());
    }

    @Test
    public void testAddNumber() {
        assertTrue(bet1.addNumber(5));
        assertFalse(bet1.addNumber(5)); // duplicate
        assertTrue(bet1.isComplete());
    }

    @Test
    public void testRemoveNumber() {
        bet1.addNumber(3);
        assertTrue(bet1.removeNumber(3));
        assertFalse(bet1.removeNumber(3)); // already removed
    }

    @Test
    public void testAutoPickSize() {
        bet4.autoPick();
        assertEquals(4, bet4.getChosenNumbers().size());
    }

    @Test
    public void testIsComplete() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.addNumber(3);
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

    // ---- KenoGame class tests ----

    @Test
    public void testSetAndGetBet() {
        game.setBet(bet8);
        assertEquals(bet8, game.getCurrentBet());
    }

    @Test
    public void testGenerateDrawingSize() {
        ArrayList<Integer> draw = game.generateDrawing();
        assertEquals(20, draw.size());
        // numbers between 1-80
        for (int num : draw) {
            assertTrue(num >= 1 && num <= 80);
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
        draw.add(2);
        draw.add(4);
        draw.add(10);
        draw.add(20);

        ArrayList<Integer> matches = game.getMatches(draw);
        assertEquals(2, matches.size());
        assertTrue(matches.contains(2));
        assertTrue(matches.contains(4));
    }

    @Test
    public void testCalculateWinningsFor1Spot() {
        bet1.addNumber(5);
        game.setBet(bet1);
        assertEquals(2, game.calculateWinnings(1));
        assertEquals(2, game.getTotalWinnings());
    }

    @Test
    public void testCalculateWinningsFor4Spots() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.addNumber(3);
        bet4.addNumber(4);
        game.setBet(bet4);

        assertEquals(1, game.calculateWinnings(2));
        assertEquals(1, game.getTotalWinnings());
        assertEquals(5, game.calculateWinnings(3));
        assertEquals(6, game.getTotalWinnings());
        assertEquals(75, game.calculateWinnings(4));
        assertEquals(81, game.getTotalWinnings());
    }

    @Test
    public void testCalculateWinningsFor8Spots() {
        bet8.autoPick();
        game.setBet(bet8);

        assertEquals(2, game.calculateWinnings(4));
        assertEquals(2, game.getTotalWinnings());
        assertEquals(12, game.calculateWinnings(5));
        assertEquals(14, game.getTotalWinnings());
        assertEquals(50, game.calculateWinnings(6));
        assertEquals(64, game.getTotalWinnings());
        assertEquals(750, game.calculateWinnings(7));
        assertEquals(814, game.getTotalWinnings());
        assertEquals(10000, game.calculateWinnings(8));
        assertEquals(10814, game.getTotalWinnings());
    }

    @Test
    public void testCalculateWinningsFor10Spots() {
        bet10.autoPick();
        game.setBet(bet10);

        assertEquals(5, game.calculateWinnings(0));
        assertEquals(5, game.getTotalWinnings());
        assertEquals(2, game.calculateWinnings(5));
        assertEquals(7, game.getTotalWinnings());
        assertEquals(15, game.calculateWinnings(6));
        assertEquals(22, game.getTotalWinnings());
        assertEquals(40, game.calculateWinnings(7));
        assertEquals(62, game.getTotalWinnings());
        assertEquals(450, game.calculateWinnings(8));
        assertEquals(512, game.getTotalWinnings());
        assertEquals(4250, game.calculateWinnings(9));
        assertEquals(4762, game.getTotalWinnings());
        assertEquals(100000, game.calculateWinnings(10));
        assertEquals(104762, game.getTotalWinnings());
    }

    @Test
    public void testResetGame() {
        bet4.autoPick();
        game.setBet(bet4);
        game.calculateWinnings(3);
        game.resetGame();
        assertEquals(0, game.getTotalWinnings());
        assertTrue(game.getCurrentBet().getChosenNumbers().isEmpty());
    }

    @Test
    public void testAddNumberOverLimit() {
        bet4.addNumber(1);
        bet4.addNumber(2);
        bet4.addNumber(3);
        bet4.addNumber(4);
        assertFalse(bet4.addNumber(5));
    }

    @Test
    public void testRemoveNonexistentNumber() {
        assertFalse(bet1.removeNumber(10));
    }

    @Test
    public void testMatchesWithNoBet() {
        game.setBet(bet1);
        ArrayList<Integer> draw = new ArrayList<>();
        draw.add(1);
        draw.add(2);
        assertEquals(0, game.getMatches(draw).size());
    }
}