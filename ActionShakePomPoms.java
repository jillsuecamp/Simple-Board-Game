public class ActionShakePomPoms extends Action {
    public ActionShakePomPoms(GameS22 currGame, int fromRow, int fromCol, int toRow, int toCol) {
        super(currGame, fromRow, fromCol, toRow, toCol);
    }

    public void performAction() {
        Piece returningPiece = this.game.getCurrentTeam().getFirstPieceRemovedPiece();
        this.game.getCurrentTeam().addPieceToTeam(returningPiece);
        this.game.getGameBoard().findRandomEmptySpace().setPiece(returningPiece);
        System.out.println(returningPiece + " has been brought back to life!");
        this.game.changeTurn();
    }
}
