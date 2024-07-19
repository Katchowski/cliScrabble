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
        System.out.println("Player" + name + "added with hand: " + player.getHand());
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
        System.out.println(player + "'s hand: " + player.getHand());

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

        if (direction.equals("v") && (y + input.length() > 15)) {
            System.out.println("Word too long");
            turn(player);
        } else if (direction.equals("h") && (x + input.length() > 15)) {
            System.out.println("Word too long");
            turn(player);
        }

        if (checkWord(input, direction, x, y, player)) {
            if (direction.equals("v")) {
                for (int i = 0; i < input.length(); i++) {
                    board.playletter(input.substring(i, i + 1), x + i, y, player);
                }
            } else if (direction.equals("h")) {
                for (int i = 0; i < input.length(); i++) {
                    board.playletter(input.substring(i, i + 1), x, y + i, player);
                }
            } else {
                System.out.println("Invalid direction");
                turn(player);
            }
        } else {
            turn(player);
        }
        nextplayer();
    }

    private static boolean checkWord(String input, String direction, int x, int y, Player player) {

        boolean valid = Arrays.binarySearch(main.words.toArray(), input) >= 0;
        boolean validLen = (input.length() < 7 && ((direction.equals("v") && (y + input.length() > 15) || (direction.equals("h") && (x + input.length() > 15)))));
        boolean validLetters = true;

        String[] arr = input.split("\\|", -1);

        for (String letter : arr) {
            if (!(player.getHand().contains(letter))) {
                validLetters = false;
                break;
            }
        }

        if (valid && validLen && validLetters) {
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
