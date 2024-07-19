import java.util.ArrayList;
import java.util.Arrays;

public class Scrabble {

    static ArrayList<Player> players = new ArrayList<Player>();
    static int currentPlayer = 0;

    public Scrabble() {

    }

    public static void addplayer(String name) {
        Player player = new Player(name);
        players.add(player);
        System.out.println("Player " + name + " added with hand: " + player.getHand());
    }

    public static void startgame() {
        int playerCount = players.size();
        if (playerCount < 2) {
            System.out.println("Not enough players");
        } else {
            System.out.println("Game started with " + playerCount + " players");
        }

        turn(players.getFirst());

    }

    public static void turn(Player player) {
        board.getBoard();
        System.out.println("Player " + player.getName() + "'s turn");
        System.out.println(player.getName() + "'s hand: " + player.getHand());

        System.out.println("discard or playword?");

        String input = main.getin();

        if (input.equals("discard")) {
            discard(player);
        } else if (input.equals("playword")) {
            playWord(player);
        } else {
            System.out.println("Invalid input");
            turn(player);
        }


    }

    private static void discard(Player player) {
        System.out.println("Enter letter to discard: ");
        String letter = main.getin();
        if (player.getHand().contains(letter)) {
            player.getHand().remove(letter);
            player.updatehand(1);
        } else {
            System.out.println("Invalid letter");
            discard(player);
        }
        nextplayer();
    }

    private static void playWord(Player player) {
        System.out.println("Enter starting x coordinate: ");
        int x = (Integer.parseInt(main.getin()) - 1);
        System.out.println("Enter starting y coordinate: ");
        int y = (Integer.parseInt(main.getin()) - 1);
        System.out.println("Enter word direction (h or v): ");
        String direction = main.getin();
        System.out.println("Enter word to play: ");
        String input = main.getin();

        if (input.length() > 7) {
            System.out.println("Word too long");
            turn(player);
        }

        if (checkWord(input, direction, x, y, player)) {
            if (direction.equals("h")) {
                for (int i = 0; i < input.length(); i++) {
                    board.playletter(input.substring(i, i + 1), x + i, y, player);
                }
                updatehand(player, input);
            } else if (direction.equals("v")) {
                for (int i = 0; i < input.length(); i++) {
                    board.playletter(input.substring(i, i + 1), x, y + i, player);
                }
                updatehand(player, input);
            } else {
                System.out.println("Invalid direction");
                turn(player);
            }
        } else {
            turn(player);
        }
        nextplayer();
    }

    private static void updatehand(Player player, String input) {
        String[] arr = input.split("(?!^)");
        int num = arr.length;
        for (String letter : arr) {
            player.getHand().remove(letter);
        }
        System.out.println(num + " letters removed from hand");
        player.updatehand(num);
    }

    private static boolean checkWord(String input, String direction, int x, int y, Player player) {

        boolean valid = Arrays.binarySearch(main.words.toArray(), input) >= 0;
        boolean validLen = true;
        boolean validLetters = true;

        if (input.length() < 7) {
           if (direction.equals("v") && (y + input.length() > 15)) {
                validLen = false;
           } else if (direction.equals("h") && (x + input.length() > 15)) {
                validLen = false;

           }
        }


        String[] arr = input.split("(?!^)");

        for (String letter : arr) {
            if (!(player.getHand().contains(letter))) {
                System.out.println("Invalid letter: " + letter);
                validLetters = false;
                break;
            } else {
                System.out.println("valid letter: " + letter);
            }
        }

        if (valid && validLen && validLetters) {
            return true;
        } else {
            if (!valid) {
                System.out.println("Word not found");
            }
            if (!validLen) {
                System.out.println("Word too long");
            }
            if (!validLetters) {
                System.out.println("Invalid letters");
            }
            return false;
        }
    }

    public static void checkIntersect (String letter, int x, int y) {
        String currentLetter = board.returnBoard()[x][y].getLetter();
        boolean intersect = !(currentLetter.equals("   ") || currentLetter.equals("L 2") || currentLetter.equals("L 3") || currentLetter.equals("W 2") || currentLetter.equals("W 3"));
        if (intersect) {
            System.out.println("Intersection found at " + x + ", " + y);
        }
    }

    private static void nextplayer() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
        turn(players.get(currentPlayer));
    }

}
