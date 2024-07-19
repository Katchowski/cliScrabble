import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    static String uInput = "";
    static boolean gameRunning = false;
    public static board board = new board();
    public static ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {




        words = getWords("C:\\Users\\katchowski\\IdeaProjects\\scrabble\\src\\words.txt");
        words.sort(String::compareToIgnoreCase);

        while (true) {
            in();

            if (uInput.equals("count") || uInput.equals("size")) {
                System.out.println(words.size());
            }

            if (uInput.equals("search")) {
                System.out.println("input word to check: ");
                String str = getin();
                int index = Arrays.binarySearch(words.toArray(), uInput);
                if (index >= 0) {
                    System.out.println("Word found at index: " + index);
                } else {
                    System.out.println("Word not found");
                }
            }

            if (uInput.equals("create")) {
                makegame();
            }

            if (gameRunning) {

                if (uInput.equals("addplayer")) {
                    System.out.println("Enter player name: ");
                    String name = getin();
                    Scrabble.addplayer(name);
                }

                if (uInput.equals("listplayers")) {
                    for (Player player : Scrabble.players) {
                        System.out.println(player.getName());
                    }
                }

                if (uInput.equals("gethand")) {
                    System.out.println("Enter player name: ");
                    String name = getin();
                    for (Player player : Scrabble.players) {
                        if (player.getName().equals(name)) {
                            System.out.println(player.getHand());
                        }
                    }
                }

                if (uInput.equals("showboard")) {
                    board.getBoard();
                }

                if (uInput.equals("start")) {
                    Scrabble.startgame();
                }

            } else {
                System.out.println("Game is not running, start a game to use this command");
            }


            if (uInput.equals("quit") || uInput.equals("exit")) {
                System.exit(0);
            }
        }
    }

    public static ArrayList <String> getWords(String path) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<String>();
        Scanner scanner = null;

        try{
            File file = new File(path);
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        while(scanner.hasNext()) {
            words.add(scanner.next());
        }

        return words;
    }

    public static void makegame() {
        System.out.println("Starting game...");
        board.setup();
        gameRunning = true;
        Scrabble scrabble = new Scrabble();
        System.out.println("Game started, add your players using addplayer, start the game using start");
    }

    public static String getin() {
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        return in.nextLine();
    }

    public static void in() {
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        uInput = in.nextLine();
    }


}
