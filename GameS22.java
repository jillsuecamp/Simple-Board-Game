import java.util.ArrayList;


//GAME OBJECTIVE MODIFICATION FOUND HERE
public class GameS22 extends Game{

    public GameS22(int numRows, int numColumns, Team teamA, Team teamB){
        super(numRows, numColumns, teamA, teamB);
    }

    /**
     * isGameEnded() -- Returns a boolean, has no parameters. This method checks if the game is over, which means
     * that a team has lost. If the length of the ArrayList for either TeamA or TeamB is 0, then the game is over.
     * @return (boolean) -- states whether or not the game is over. If true, the game is over.
     */
    @Override
    public boolean isGameEnded() {
        boolean winner = false;
        for (int i = 0; i < teamA.getTeamRemovedPieces().size(); i++){
            if (teamA.getTeamRemovedPieces().get(i) instanceof PieceAppa){
                winner = true;
            }
        }
        for (int j = 0; j < teamB.getTeamRemovedPieces().size(); j++){
            if (teamB.getTeamRemovedPieces().get(j) instanceof PieceAppa){
                winner = true;
            }
        }
        return winner;
    }



    /**
     * getWinner() -- Returns a Team object, has no parameters. This method checks which team still has
     * pieces left in its ArrayList and then returns that Team. For there to be a winning team, one team's
     * ArrayList length must be > 0 while the other is == 0.
     * @return (Team) -- returns the ArrayList of the team that has won.
     */
    @Override
    //GAME OBJECTIVE MODIFICATION
    public Team getWinner() {
        Team winner = null;
        for (int i = 0; i < teamA.getTeamRemovedPieces().size(); i++){
            if (teamA.getTeamRemovedPieces().get(i) instanceof PieceAppa){
                winner = teamA;
            }
        }
        for (int j = 0; j < teamB.getTeamRemovedPieces().size(); j++){
            if (teamB.getTeamRemovedPieces().get(j) instanceof PieceAppa){
                winner = teamB;
            }
        }
        return winner;
    }

    /**
     * isAWinner() -- Returns a boolean stating if there is a winner, has no parameters. This method calls the getWinner()
     * method and if the return from that method is an ArrayList of either teamA or TeamB, that means there is a winner. If
     * the return is null, there is no winner.
     * @return (boolean) -- Tells whether there is a winner in the game yet or not.
     */
    @Override
    public boolean isAWinner() {
        boolean isWinner = false;
        if (getWinner().equals(this.teamA) || getWinner().equals(this.teamB)){
            isWinner = true;
        }
        else if (getWinner().equals(null)){
            isWinner = false;
        }
        return isWinner;
    }

}
