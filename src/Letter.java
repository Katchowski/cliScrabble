public class Letter {

    String letter;
    int x;
    int y;
    Player player;
    int points;

    public Letter (String letter, int x, int y, Player player, int points) {
        if (letter == null) {
            System.out.println("Letter is null");
        }
        this.letter = letter;
        this.x = x;
        this.y = y;
        this.player = player;
        this.points = points;
    }

    public String getLetter() {
        return " " + letter + " ";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

}
