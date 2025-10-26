import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bet {
    private int numSpots;
    private int numDrawings;
    private ArrayList<Integer> chosenNumbers;

    public Bet(int numSpots, int numDrawings) {
        this.numSpots = numSpots;
        this.numDrawings = numDrawings;
        this.chosenNumbers = new ArrayList<>();
    }

    public int getNumSpots() {
        return numSpots;
    }

    public int getNumDrawings() {
        return numDrawings;
    }

    public List<Integer> getChosenNumbers() {
        return new ArrayList<>(chosenNumbers);
    }

    public boolean addNumber(int number) {
        if (chosenNumbers.contains(number)) {
            return false; // Already selected
        }
        if (chosenNumbers.size() >= numSpots) {
            return false; // Max reached
        }
        chosenNumbers.add(number);
        return true;
    }

    public boolean removeNumber(int number) {
        return chosenNumbers.remove(Integer.valueOf(number));
    }

    public void autoPick() {
        chosenNumbers.clear();
        ArrayList<Integer> pool = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            pool.add(i);
        }
        Collections.shuffle(pool, new Random());
        for (int i = 0; i < numSpots; i++) {
            chosenNumbers.add(pool.get(i));
        }
    }

    public boolean isComplete() {
        return chosenNumbers.size() == numSpots;
    }

    public void reset() {
        chosenNumbers.clear();
    }
}