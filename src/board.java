import java.util.StringJoiner;

public class board {


    //static Letter[][] board = new Letter[15][15];
    static Space[][] spaces = new Space[15][15];

    public board() {
        System.out.println("Board created");
    }

    public void setup() {
        for (int x = 0; x < spaces.length; x++) {
            for (int y = 0; y < spaces[0].length; y++) {
                spaces[x][y] = new Space(x, y, false);
                spaces[x][y].addLetter(null);
            }
        }

        spaces[0][0].setMultiplier("W",3);
        spaces[0][3].setMultiplier("L",2);
        spaces[0][7].setMultiplier("W",3);
        spaces[0][11].setMultiplier("L",2);
        spaces[0][14].setMultiplier("W",3);

        spaces[1][1].setMultiplier("W",2);
        spaces[1][5].setMultiplier("L",3);
        spaces[1][9].setMultiplier("L",3);
        spaces[1][13].setMultiplier("W",2);

        spaces[2][2].setMultiplier("W",2);
        spaces[2][6].setMultiplier("L",2);
        spaces[2][8].setMultiplier("L",2);
        spaces[2][12].setMultiplier("W",2);

        spaces[3][0].setMultiplier("L",2);
        spaces[3][3].setMultiplier("W",2);
        spaces[3][7].setMultiplier("L",2);
        spaces[3][11].setMultiplier("W",2);
        spaces[3][14].setMultiplier("L",2);

        spaces[4][4].setMultiplier("W",2);
        spaces[4][10].setMultiplier("W",2);

        spaces[5][1].setMultiplier("L",3);
        spaces[5][5].setMultiplier("L",3);
        spaces[5][9].setMultiplier("L",3);
        spaces[5][13].setMultiplier("L",3);

        spaces[6][2].setMultiplier("L",2);
        spaces[6][6].setMultiplier("L",2);
        spaces[6][8].setMultiplier("L",2);
        spaces[6][12].setMultiplier("L",2);

        spaces[7][0].setMultiplier("W",3);
        spaces[7][3].setMultiplier("L",2);
        spaces[7][7].setMultiplier("W",2);
        spaces[7][11].setMultiplier("L",2);
        spaces[7][14].setMultiplier("W",3);

        spaces[8][2].setMultiplier("L",2);
        spaces[8][6].setMultiplier("L",2);
        spaces[8][8].setMultiplier("L",2);
        spaces[8][12].setMultiplier("L",2);

        spaces[9][1].setMultiplier("L",3);
        spaces[9][5].setMultiplier("L",3);
        spaces[9][9].setMultiplier("L",3);
        spaces[9][13].setMultiplier("L",3);

        spaces[10][4].setMultiplier("W",2);
        spaces[10][10].setMultiplier("W",2);

        spaces[11][0].setMultiplier("L",2);
        spaces[11][3].setMultiplier("W",2);
        spaces[11][7].setMultiplier("L",2);
        spaces[11][11].setMultiplier("W",2);
        spaces[11][14].setMultiplier("L",2);

        spaces[12][2].setMultiplier("W",2);
        spaces[12][6].setMultiplier("L",2);
        spaces[12][8].setMultiplier("L",2);
        spaces[12][12].setMultiplier("W",2);

        spaces[13][1].setMultiplier("W",2);
        spaces[13][5].setMultiplier("L",3);
        spaces[13][9].setMultiplier("L",3);
        spaces[13][13].setMultiplier("W",2);

        spaces[14][0].setMultiplier("W",3);
        spaces[14][3].setMultiplier("L",2);
        spaces[14][7].setMultiplier("W",3);
        spaces[14][11].setMultiplier("L",2);
        spaces[14][14].setMultiplier("W",3);

        System.out.println("Board setup");
    }

    public static void playletter(String str, int x, int y, Player player) {
        int points = 0;

        spaces[y][x].playLetter(str, player, points);
        spaces[y][x].hasLetter = true;
    }

    public static void getBoard() {
        int y = 1;
        String lineSplit = "";
        StringJoiner splitJoiner = new StringJoiner("+", "|", "|");
        for (int index = 0; index < (spaces[0].length + 1); index++) {
            splitJoiner.add(String.format("%5s", "").replace(" ", "-"));
        }
        lineSplit = splitJoiner.toString();

        StringJoiner strj = new StringJoiner(" | ", "| ", " |");
        for (int i = 0; i < spaces.length + 1; i++) {
            if (i >= 10) {
                strj.add(" " + i);
            } else {
                strj.add(" " + i + " ");
            }
        }
        System.out.println(lineSplit);
        System.out.println(strj.toString());

        for (Space[] row : spaces) {
            StringJoiner sj = new StringJoiner(" | ", "| ", " |");

            if (y >= 10) {
                sj.add(" " + y);
            } else {
                sj.add(" " + y + " ");
            }
            for (Space col : row) {

                if (col.getLetterValue().equals(" null ")) {

                    if (spaces[col.getLetterX()][col.getLetterY()].getMultiplier().contains("null")) {
                        sj.add("   ");
                    } else {
                        sj.add(String.format(spaces[col.getLetterX()][col.getLetterY()].getMultiplier()));
                    }
                } else {
                    sj.add(String.format(col.getLetterValue()));
                }
            }
            System.out.println(lineSplit);
            System.out.println(sj.toString());
            y++;
        }
        System.out.println(lineSplit);
    }

    public static Space[][] returnBoard () {

        return spaces;
    }
}
