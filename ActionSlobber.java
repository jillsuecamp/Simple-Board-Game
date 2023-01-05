//NEW ACTION MODIFICATION

public class ActionSlobber extends Action {

    public ActionSlobber(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare) {
        super(game, rowFromSquare, columnFromSquare, rowToSquare, columnToSquare);
    }


    public void performAction() {
        System.out.println("Incoming! Slobber is falling from the sky! Where will it land?");
        if (this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece() instanceof PieceAppa) {
            ((PieceAppa) this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece()).setNumTimesSlobbered(1);
        }
        int count = 0;
        for (int i = 0; i <= 3; i++) {
            int randRow = (int) (Math.floor(Math.random() * (this.game.getGameBoard().getNumRows())));
            int randCol = (int) (Math.floor(Math.random() * (this.game.getGameBoard().getNumRows())));
            // if random square is not empty
            if (!(this.game.getGameBoard().getSquares()[randRow][randCol].isEmpty())) {
                if (this.game.getGameBoard().getSquares()[randRow][randCol].getPiece().getTeamColor().equals(this.game.getOpponentTeam().getTeamColor())){
                    count = count + 1;
                    Piece attacked = this.game.getGameBoard().getSquares()[randRow][randCol].getPiece();
                    this.game.getGameBoard().getSquares()[randRow][randCol].removePiece();
                    this.game.getOpponentTeam().removePieceFromTeam(attacked);
                }
            }
        } //NEW RULE MODIFICATION
        if (count > 0) {
            System.out.println("You slobbered " + count + " pieces! You get an extra turn!");
            this.game.changeTurn();
        } else {
            System.out.println("No pieces were slobbered!");
        }
        this.game.changeTurn();

    }


}
