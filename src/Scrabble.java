import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Scrabble {

    static ArrayList<Player> players = new ArrayList<Player>();
    static int currentPlayer = 0;
    static String uInput;

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
        uInput = main.getin();

        if (uInput.length() > 7) {
            System.out.println("Word too long");
            turn(player);
        }

        if (checkWord(uInput, direction, x, y, player)) {
            if (direction.equals("h")) {
                for (int i = 0; i < uInput.length(); i++) {
                    board.playletter(uInput.substring(i, i + 1), x + i, y, player);
                }
                updatehand(player, uInput);
            } else if (direction.equals("v")) {
                for (int i = 0; i < uInput.length(); i++) {
                    board.playletter(uInput.substring(i, i + 1), x, y + i, player);
                }
                updatehand(player, uInput);
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
        boolean validPlacement = true;

        if (input.length() < 7) {
           if (direction.equals("v") && (y + input.length() > 15)) {
                validLen = false;
           } else if (direction.equals("h") && (x + input.length() > 15)) {
                validLen = false;

           }
        }

        String[] arr = input.split("(?!^)");
        ArrayList<String> InvalidLetters = new ArrayList<String>();

        for (String letter : arr) {
            if (!(player.getHand().contains(letter))) {
                System.out.println("Invalid letter: " + letter);
                InvalidLetters.add(letter);
                validLetters = false;
                break;
            } else {
                System.out.println("valid letter: " + letter);
            }
        }


        for (int i = 0; i < arr.length; i++) {
            if (direction.equals("h")) {
                if (checkIntersect(x + i, y)) {
                    if (board.returnBoard()[x + i][y].getLetterValue().equals(arr[i])) {
                        System.out.println("Intersection found at " + x + i + ", " + y);
                        InvalidLetters.remove(arr[i]);
                        ArrayList<String> uInputArr;
                        uInputArr = (ArrayList<String>) Arrays.asList(uInput.split("(?!^)"));
                        System.out.println(uInputArr);
                    } else {
                        System.out.println("Invalid placement 1");
                        validPlacement = false;
                    }
                }
            } else if (direction.equals("v")) {
                if (checkIntersect(x, y + i)) {
                    if (board.returnBoard()[x][y + i].getLetterValue().equals(arr[i])) {
                        System.out.println("Intersection found at " + x + i + ", " + y);
                        InvalidLetters.remove(arr[i]);
                    } else {
                        System.out.println("Invalid placement 2");
                        validPlacement = false;
                    }
                }
            }
        }

        if (valid && validLen && validLetters && validPlacement) {
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
            if (!validPlacement) {
                System.out.println("Invalid placement 3");
            }
            return false;
        }
    }

    public static boolean checkIntersect (int x, int y) {

        Space[][] arr = board.returnBoard();

        if (arr[y][x].spaceHasLetter()) {
            System.out.println("Intersection found at " + x + ", " + y);
            return true;
        }

        return false;
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
