public abstract class Action {
    protected GameS22 game;
    protected int rowFromSquare; // represents the row index of the piece taking action
    protected int columnFromSquare; // represents the column index of the piece taking action
    protected int rowToSquare; // represents the row index of the square where the action-taking piece is going
    protected int columnToSquare; // represents the column of the square where the action-taking piece is going

    public Action(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare){
        this.game = game;
        this.rowFromSquare = rowFromSquare;
        this.columnFromSquare = columnFromSquare;
        this.rowToSquare = rowToSquare;
        this.columnToSquare = columnToSquare;

    }

    public abstract void performAction();
}
