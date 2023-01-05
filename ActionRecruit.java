public class ActionRecruit extends Action{
    /**
     * performAction() -- Has no parameters or returns. This method performs the Recruit action. When the user selects
     * a spot on the board to recruit, this piece (the toSquare) will get removed from the team it belongs to (the opponent
     * team) before getting added to the fromSquare piece's team, thus recruiting the piece.
     */
    public ActionRecruit(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare) {
        super(game, rowFromSquare, columnFromSquare, rowToSquare, columnToSquare);
    }

    @Override
    public void performAction() {
        if (this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece() instanceof PieceAppa) {
            ((PieceAppa) this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece()).updateNumTimesRecruited();
        }
        Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
        thePiece.speak();
        Piece recruited = this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece();
        this.game.getOpponentTeam().removePieceFromTeam(recruited);
        this.game.getCurrentTeam().addPieceToTeam(recruited);
        this.game.changeTurn();
    }
}
