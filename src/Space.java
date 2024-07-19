public class Space {

    int x;
    int y;
    boolean hasMultiplier;
    String multiplierType;
    int multiplierVal;

    public Space( int x, int y, boolean hasMultiplier) {
        this.x = x;
        this.y = y;
        this.hasMultiplier = hasMultiplier;
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

}
