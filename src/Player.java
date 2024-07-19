import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private String name;
    private ArrayList<String> hand = new ArrayList<String>();
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
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
            }
        }
        System.out.println(hand);
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


/*

A-9: 0-9
B-2: 10-11
C-2: 12-13
D-4: 14-17
E-12: 18-29
F-2: 30-31
G-3: 32-34
H-2: 35-36
I-9: 37-46
J-1: 47
K-1: 48
L-4: 49-52
M-2: 53-54
N-6: 55-60
O-8: 61-68
P-2: 69-70
Q-1: 71
R-6: 72-77
S-4: 78-81
T-6: 82-87
U-4: 88-91
V-2: 92-93
W-2: 94-95
X-1: 96
Y-2: 97-98
Z-1: 99

 */
