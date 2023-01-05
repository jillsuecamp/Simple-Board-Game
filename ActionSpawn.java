public class ActionSpawn extends Action {
    /**
     * performAction() -- Has no parameters or returns. This method performs the Spawn action. When this action is selected
     * by the user, the piece they have chosen to spawn will speak its message before a new instance of that piece is created
     * and then added to the team. The spawned piece will get placed wherever the user specified.
     */
    public ActionSpawn(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare) {
        super(game, rowFromSquare, columnFromSquare, rowToSquare, columnToSquare);
    }

    @Override
    public void performAction() {
        Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
        thePiece.speak();
        Piece spawnedPiece = thePiece.spawn();
        Team currentTeam = this.game.getCurrentTeam();
        currentTeam.addPieceToTeam(spawnedPiece);
        this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].setPiece(spawnedPiece);
        this.game.changeTurn();
    }
}
