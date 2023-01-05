public abstract class Piece implements Comparable<Piece> {
    // PARTNER: JILLIAN CAMP
    // member fields
    protected char symbol;
    protected String teamColor;
    protected boolean hidden;
    protected boolean original;

    // common variables in constructors
    public Piece(char symbol, String teamColor, boolean hidden, boolean original) {
        this.symbol = symbol;
        this.teamColor = teamColor;
        this.hidden = hidden;
        this.original = original;
    }

    // common getters
    public char getSymbol() {
        return symbol;
    }
    public String getTeamColor() {
        return teamColor;
    }
    public boolean isHidden() {
        return hidden;
    }
    public boolean isOriginal() {
        return original;
    }

    // common setters
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public void setOriginal(boolean original){
        this.original = original;
    }
    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    // common method, different execution
    public abstract void speak();
    public abstract boolean validMovePath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol);
    public abstract boolean validSpawnPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol);
    public abstract boolean canSpawn();
    public abstract Piece spawn();

    // toString override
    @Override
    public String toString() {
        return this.teamColor + " " + this.symbol;
    }

    public int compareTo(Piece otherPiece) {
        return Character.toString(symbol).compareTo(Character.toString(otherPiece.symbol));
    }
}
