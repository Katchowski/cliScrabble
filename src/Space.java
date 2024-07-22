public class Space {

    int x;
    int y;
    boolean hasMultiplier;
    String multiplierType;
    int multiplierVal;
    Letter letter;
    boolean hasLetter = false;

    public Space( int x, int y, boolean hasMultiplier) {
        this.x = x;
        this.y = y;
        this.hasMultiplier = hasMultiplier;
    }

    public void addLetter (String letter) {
        this.letter = new Letter(letter, x, y, null, 0);
        if (letter != null) {
            hasLetter = true;
            System.out.println("Letter added to space " + x + ", " + y + ": " + letter);
        }
    }

    public void playLetter (String letter, Player player, int points) {
        this.letter = new Letter(letter, x, y, player, points);
    }

    public boolean spaceHasLetter () {
        return hasLetter;
    }

    public void setMultiplier (String str, int num) {
        multiplierType = str;
        multiplierVal = num;
        hasMultiplier = true;
    }

    public String getMultiplier() {
        return multiplierType + " " + multiplierVal;
    }

    public boolean hasMultiplier () {
        return hasMultiplier;
    }

    public String getLetterValue() {
        return letter.getLetter();
    }

    public int getLetterPoints() {
        return letter.getPoints();
    }

    public int getLetterX() {
        return x;
    }

    public int getLetterY() {
        return y;
    }

}
