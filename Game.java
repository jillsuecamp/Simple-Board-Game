import java.lang.management.GarbageCollectorMXBean;
import java.sql.Array;
import java.util.ArrayList;
import java.util.*;
public abstract class Game {
    // PARTNER: JILLIAN CAMP
    protected GameBoard board;
    protected Team teamA;
    protected Team teamB;
    protected String turn;

    // initializeGameBoard method
    private void initializeGameBoard(int numRows, int numColumns) {
        this.board = new GameBoard(numRows, numColumns);
        for (int i = 0; i < this.teamA.teamPieces.size(); i++) {
            this.board.findRandomEmptySpace().setPiece(this.teamA.teamPieces.get(i));
        }
        for (int j = 0; j < this.teamB.teamPieces.size(); j++) {
            this.board.findRandomEmptySpace().setPiece(this.teamB.teamPieces.get(j));
        }
    }

    // constructor
    public Game(int numRows, int numColumns, Team teamA, Team teamB) {
        this.turn = teamA.getTeamColor();
        this.teamA = teamA;
        this.teamB = teamB;
        initializeGameBoard(numRows, numColumns);
    }

    // accessor methods
    public GameBoard getGameBoard() {
        return this.board;
    }

    // getCurrentTeam returns the team who's turn it is by comparing the color of teamsTurn to the team colors
    public Team getCurrentTeam() {
        Team teamsTurn;
        if (this.turn.equals(this.teamA.getTeamColor())) {
            teamsTurn = this.teamA;
        }
        else {
            teamsTurn = this.teamB;
        }
        return teamsTurn;
    }

    // getOpponentTeam returns the opposite as the get current team
    public Team getOpponentTeam() {
        Team teamsTurn;
        if (this.turn.equals(this.teamA.getTeamColor())) {
            teamsTurn = this.teamB;
        }
        else {
            teamsTurn = this.teamA;
        }
        return teamsTurn;
    }
    // isTurn is a true/false return of whether it's the team given in the parameter's turn or not
    public boolean isTurn(Team team) {
        if (team.getTeamColor().equals(this.turn)) {
            return true;
        }
        else {
            return false;
        }
    }
    public BoardSquare[][] getBoardSquares() {
        return getGameBoard().getSquares();
    }

    // mutator method changeTurn: changes the teams turn from one to the other
    public void changeTurn() {
        if (this.turn.equals(this.teamA.getTeamColor())) {
            this.turn = this.teamB.getTeamColor();
        }
        else {
            this.turn = this.teamA.getTeamColor();
        }
    }

    // Abstract methods
    public abstract boolean isAWinner();
    public abstract Team getWinner();
    public abstract boolean isGameEnded();

    // toString
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Game Board:\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getGameBoard().toString())
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getCurrentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getOpponentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\nIt is Team " + getCurrentTeam().getTeamColor() + "'s turn\n");
        return retString.toString();
    }

}
