import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class KenoGame {
    // Current bet placed by player
    // total winnings accumulated
    // random number generator for drawings
    private Bet currentBet;
    private int totalWinnings;
    private Random random;

    // Constructor
    public KenoGame() {
        this.random = new Random();
        this.totalWinnings = 0;
    }

    // Getters and Setters
    public void setBet(Bet bet) {
        this.currentBet = bet;
    }

    public Bet getCurrentBet() {
        return currentBet;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    // Game Logic Methods
    // Generate 20 random numbers for the drawing
    // Make 80 numbers, shuffle, pick first 20
    public ArrayList<Integer> generateDrawing() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, random);
        //Try to account for duplicates?
        ArrayList<Integer> draw = new ArrayList<>(numbers.subList(0, 20));
        Collections.sort(draw);
        return draw;
    }

    // compare player's chosen numbers to drawing results
    public ArrayList<Integer> getMatches(ArrayList<Integer> draw) {
        ArrayList<Integer> matches = new ArrayList<>();
        for (int num : draw) {
            if (currentBet.getChosenNumbers().contains(num)) {
                matches.add(num);
            }
        }
        return matches;
    }

    // Get winnings based on number of matches and bet configuration
    public int calculateWinnings(int numMatches) {
        int payout = 0;
        int spots = currentBet.getNumSpots();

        switch (spots) {
            case 1:
                payout = (numMatches == 1) ? 2 : 0;
                break;
            case 4:
                if (numMatches == 2) payout = 1;
                else if (numMatches == 3) payout = 5;
                else if (numMatches == 4) payout = 75;
                break;
            case 8:
                if (numMatches == 4) payout = 2;
                else if (numMatches == 5) payout = 12;
                else if (numMatches == 6) payout = 50;
                else if (numMatches == 7) payout = 750;
                else if (numMatches == 8) payout = 10000;
                break;
            case 10:
                if (numMatches == 0) payout = 5;
                else if (numMatches == 5) payout = 2;
                else if (numMatches == 6) payout = 15;
                else if (numMatches == 7) payout = 40;
                else if (numMatches == 8) payout = 450;
                else if (numMatches == 9) payout = 4250;
                else if (numMatches == 10) payout = 100000;
                break;
        }

        totalWinnings += payout;
        return payout;
    }

    public void resetGame() {
        this.totalWinnings = 0;
        if (currentBet != null) {
            currentBet.reset();
        }
    }
}