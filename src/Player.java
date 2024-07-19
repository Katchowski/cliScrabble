import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private String name;
    private ArrayList<String> hand = new ArrayList<String>();
    private String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private double[] letterProbabilities = {.09, .02, .02, .04, .12, .02, .03, .02, .09, .01, .01, .04, .02, .06, .08, .02, .01, .06, .04, .02, .02, .01, .02, .01, .02, .01};
    private int[] points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    public Player (String name) {
        System.out.println("Player " + name + " created");
        this.name = name;
        makeHand();
    }

    public void makeHand() {
        while (7 > hand.toArray().length) {

            String letter = getLetter();
            if (letter != null) {
                hand.add(letter);
                int index = Arrays.binarySearch(letters, letter);
                letterProbabilities[index] = letterProbabilities[index] - .01;
            }
        }
    }

    public void updatehand(int num) {
        while (num > 0) {

            String letter = getLetter();
            if (letter != null) {
                hand.add(letter);
                int index = Arrays.binarySearch(letters, letter);
                letterProbabilities[index] = letterProbabilities[index] - .01;
            }
            num--;
        }
    }

    private String getLetter () {
        double p = Math.random();
        double cumulativeProbability = 0.0;
        for (String letter : letters) {
            int index = Arrays.asList(letters).indexOf(letter);
            cumulativeProbability += letterProbabilities[index];
            if (p <= cumulativeProbability) {
                return letter;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    public Player getPlayer() {
        return this;
    }
}