public class ActionAttack extends Action{
    /**
     * performAction() -- Has no parameters or returns. Performs the Attack action either on a piece of the opponent team
     * or on the current player's own team. If the attack is on the opponent team, the fromSquare will speak and then remove
     * the toSquare piece from the board as well as the opponent team. The fromSquare will take the place on the board of the
     * attacked toSquare. If the attack is on the current player's own team piece, then the fromSquare has to be a PieceEvilMinion
     * and the toSquare must be a PieceMinion. This Attack action will remove the regular PieceMinion(which cannot attack) from
     * its own team, as well as from the board,replacing it with a new PieceEvilMinion, which can attack.
     */

    public ActionAttack(GameS22 game, int rowFromSquare, int columnFromSquare, int rowToSquare, int columnToSquare) {
        super(game, rowFromSquare, columnFromSquare, rowToSquare, columnToSquare);
    }

    public void performAction() {
        if (this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece() instanceof Attacker) {
            ((Attacker) this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece()).setNumAttacks(((Attacker) this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece()).getNumAttacks()+1);
            System.out.println(this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece() + " has attacked " + ((Attacker) this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece()).getNumAttacks() + " time(s).");
        }
        //Checks if the piece performing the action is a PieceEvilMinion object
        if (this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece() instanceof PieceEvilMinion) {
            //Checks if the piece getting attacked is part of the current team
            if (this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece().getTeamColor().equals(this.game.getCurrentTeam().getTeamColor())){
                //Checks if the piece getting attacked is a PieceMinion object
                if (this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece() instanceof PieceMinion){
                    Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
                    thePiece.speak();
                    Piece attacked = this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece();
                    this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].removePiece();
                    this.game.getCurrentTeam().removePieceFromTeam(attacked);
                    PieceEvilMinion evilMinion = new PieceEvilMinion();
                    this.game.getCurrentTeam().addPieceToTeam(evilMinion);
                    this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].setPiece(evilMinion);
                    this.game.changeTurn();
                }//The piece performing the action was an Evil Minion, attacking its own piece, BUT the piece it was attacking was not a minion.
                //Therefore, do nothing and change turn.
                else{
                    this.game.changeTurn();
                }
            }
            else{ //Attack the piece like normal
                Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
                thePiece.speak();
                Piece attacked = this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece();
                this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].removePiece();
                this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].removePiece();
                this.game.getOpponentTeam().removePieceFromTeam(attacked);
                this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].setPiece(thePiece);
                this.game.changeTurn();
            }

        }
        else { //Attack the piece like normal
            Piece thePiece = this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].getPiece();
            thePiece.speak();
            Piece attacked = this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].getPiece();
            this.game.getGameBoard().getSquares()[rowFromSquare][columnFromSquare].removePiece();
            this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].removePiece();
            this.game.getOpponentTeam().removePieceFromTeam(attacked);
            this.game.getGameBoard().getSquares()[rowToSquare][columnToSquare].setPiece(thePiece);
            this.game.changeTurn();
        }

    }
}
