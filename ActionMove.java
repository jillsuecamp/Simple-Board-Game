public class ActionMove extends Action{
    /**
     * performAction() -- Has no parameters or returns. This method performs the Move action. The piece that is about
     * to move will first speak its message before being removed from its current spot (the fromSquare) to its user-selected destination (the            toSquare).
     */
    public ActionMove(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare){
        super(game, rowFromSquare, columnFromSquare, rowToSquare, columnToSquare);
    }

    public void performAction(){
        Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
        thePiece.speak();
        this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].removePiece();
        this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].setPiece(thePiece);
        this.game.changeTurn();
    }
}
