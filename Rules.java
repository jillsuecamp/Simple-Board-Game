import java.util.ArrayList;
import java.lang.Math;
public class Rules {
    /**
     * checkValidAction -- This method has 6 parameters and returns a boolean stating whether or not the action the user has
     * selected adheres to the rules of the game. See individual lines of docstrings within the method to see what conditions
     * need to be true in order for the action to be considered valid.
     * @param currGame (GameS22) -- the instance of the GameS22 object being currently played.
     * @param fromRow (int) -- The row index of the piece about to perform the action.
     * @param fromCol (int) -- The column index of the piece about to perform the action.
     * @param toRow (int) -- The row index of the square where the piece will either move to, Attack, Recruit, or Spawn.
     * @param toCol (int) -- The column index of the square where the piece will either move to, Attack, Recruit, or Spawn.
     * @param action (char) -- The user-entered char that represents an action (A, M, S, R)
     * @return (boolean) -- Whether the action is valid according to the game's rules.
     */
    public static boolean checkValidAction(GameS22 currGame, int fromRow, int fromCol, int toRow, int toCol, char action) {
        Piece currentPieceType = currGame.getGameBoard().getSquares()[fromRow][fromCol].getPiece();
        boolean isActionTrue = false;
        boolean canMove = false;
        // checks if parameters are in bounds
        if (currGame.getGameBoard().inBounds(fromRow,fromCol) && currGame.getGameBoard().inBounds(toRow,toCol)) {
            // MODIFICATION: checking if its on hidden square
            if (fromRow == currGame.getGameBoard().getHiddenSquareRow() && fromCol == currGame.getGameBoard().getHiddenSquareCol()) {
                System.out.println("You've landed on the hidden square, you may not do anything except move.");
                if (action == 'S' || action == 's' || action == 'R' || action == 'r' || action == 'A' || action == 'a') {
                    isActionTrue = false;
                    // set action to X so it doesnt go through the rest of the if statements
                    action = 'X';
                }
                else if (action == 'M' || action == 'm') {
                    // goes through M section anyway
                    isActionTrue = true;
                }
            }
            // if action is POMPOMS (only available for PieceBabyBlue)
            if (action == 'P' || action == 'p') {
                if (currentPieceType instanceof PieceBabyBlue) {
                    if (currGame.getCurrentTeam().getFirstPieceRemovedPiece() == null) {
                        isActionTrue = false;
                    }
                    else {
                        isActionTrue = true;
                    }
                }
            }

            if (action == 'O' || action == 'o') {
                if (currentPieceType instanceof PieceAppa) {
                    if (((PieceAppa) currentPieceType).getNumAttacks() >= 1) {
                        if (((PieceAppa) currentPieceType).getNumTimesSlobbered() < 1) {
                            isActionTrue = true;
                        }
                    }
                }
            }


            // if action is MOVE (check also S because the from and to square requirements are the same
            if (action == 'M' || action == 'm' || action == 'S' || action == 's') {
                // checks for a piece on FROM square
                if (currentPieceType != null) {
                    // the piece on the FROM SQUARE is on the same team as the currentTeam
                    if (currGame.getGameBoard().getSquares()[fromRow][fromCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                        // the TO SQUARE must be empty
                        if (currGame.getGameBoard().getSquares()[toRow][toCol].isEmpty()) {
                            // checks PieceType's specific validMovePath
                            if (currentPieceType.validMovePath(fromRow,fromCol,toRow,toCol)) {
                                isActionTrue = true;

                            }
                        }
                    }
                }
            }
            // if action is SPAWN
            if (action == 'S' || action == 's') {
                isActionTrue = false;
                // checks that from square belongs to current team, and to square is empty (same as move action)
                if (currentPieceType != null) {
                    if (currGame.getGameBoard().getSquares()[fromRow][fromCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                        // the TO SQUARE must be empty
                        if (currGame.getGameBoard().getSquares()[toRow][toCol].isEmpty()) {
                            // make sure piece is any piece type except for buzz
                            if (!(currentPieceType instanceof PieceBuzz)) {
                                // NEW PIECE SPAWN RULE MODIFICATION
                                // checks if piece is not minion, so it can just call valid spawn path and return value
                                if (!(currentPieceType instanceof PieceMinion)) {
                                    // checking APPA because all 8 tiles must be empty before he can spawn
                                    if (currentPieceType instanceof PieceAppa) {
                                        // check top 3 squares are empty
                                        if (currGame.getGameBoard().getSquares()[fromRow-1][fromCol-1].isEmpty() && currGame.getGameBoard().getSquares()[fromRow-1][fromCol].isEmpty() && currGame.getGameBoard().getSquares()[fromRow-1][fromCol+1].isEmpty()) {
                                            // check bottom 3 squares are empty
                                            if (currGame.getGameBoard().getSquares()[fromRow+1][fromCol-1].isEmpty() && currGame.getGameBoard().getSquares()[fromRow+1][fromCol].isEmpty() && currGame.getGameBoard().getSquares()[fromRow+1][fromCol+1].isEmpty()) {
                                                // check squares next to are empty
                                                if (currGame.getGameBoard().getSquares()[fromRow][fromCol-1].isEmpty() && currGame.getGameBoard().getSquares()[fromRow][fromCol+1].isEmpty()) {
                                                    isActionTrue = true;
                                                }
                                            }
                                        }
                                        else {
                                            isActionTrue = false;
                                        }
                                    }
                                    else if (currentPieceType.validSpawnPath(fromRow,fromCol,toRow,toCol)) {
                                        isActionTrue = true;
                                    }
                                }
                                if (currentPieceType instanceof PieceMinion) {
                                    boolean keepGoing = false;
                                    // distance formula
                                    int lastRow = currGame.getGameBoard().getNumRows()-1;
                                    int lastCol = currGame.getGameBoard().getNumColumns()-1;
                                    int topLeft = (int) Math.sqrt(Math.pow(-fromRow,2) + Math.pow(-fromCol,2));
                                    int topRight = (int) Math.sqrt(Math.pow(-fromRow,2) + Math.pow(lastCol - fromCol,2));
                                    int bottomLeft = (int) Math.sqrt(Math.pow(lastRow - fromRow,2) + Math.pow(-fromCol,2));
                                    int bottomRight = (int) Math.sqrt(Math.pow(lastRow - fromRow,2) + Math.pow(lastCol - fromCol,2));
                                    int[] listCorners = new int[4];
                                    listCorners[0] = topLeft;
                                    listCorners[1] = topRight;
                                    listCorners[2] = bottomLeft;
                                    listCorners[3] = bottomRight;
                                    int min = 100;
                                    for (int i=0; i < listCorners.length; i++) {
                                        if (listCorners[i] < min) {
                                            min = listCorners[i];
                                        }
                                    }
                                    // checking if the square addressed is a corner or not and if its minimum
                                    // top left
                                    if (toRow == 0 && toCol == 0 && topLeft == min) {
                                        isActionTrue = true;
                                    }
                                    // top right
                                    if (toRow == 0 && toCol == currGame.getGameBoard().getNumColumns()-1 && topRight == min) {
                                        isActionTrue = true;
                                    }
                                    // bottom left
                                    if (toRow == currGame.getGameBoard().getNumRows()-1 && toCol == 0 && bottomLeft == min) {
                                        isActionTrue = true;
                                    }
                                    // bottom right
                                    if (toRow == currGame.getGameBoard().getNumRows()-1 && toCol == currGame.getGameBoard().getNumColumns()-1 && bottomRight == min) {
                                        isActionTrue = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // if action is RECRUIT
            if (action == 'R' || action == 'r') {
                // check that there is a piece on FROM square
                if (currentPieceType != null) {
                    // check that piece on FROM square is on the current team's team
                    if (currentPieceType.getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                        // check that there is a piece on TO square
                        if (currGame.getGameBoard().getSquares()[toRow][toCol].getPiece() != null) {
                            // check that piece on the TO square is on opponent team
                            if (!(currGame.getGameBoard().getSquares()[toRow][toCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor()))) {
                                // make sure piece is any piece type except for Buzz
                                if (currentPieceType instanceof PieceMinion || currentPieceType instanceof PieceBlueHen || currentPieceType instanceof PieceAppa){
                                    // check valid recruit path
                                    if (((Recruiter) currentPieceType).validRecruitPath(fromRow,fromCol,toRow,toCol)) {
                                        isActionTrue = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // if action is ATTACK
            if (action == 'A' || action == 'a') {
                // check that there is a piece on FROM square
                if (currentPieceType != null) {
                    // if current piece is BUZZ or BLUEHEN
                    if (currentPieceType instanceof PieceBuzz || currentPieceType instanceof PieceBlueHen) {
                        // piece on FROM square must be on current team
                        if (currentPieceType.getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                            // check that there is a piece on TO square
                            if (currGame.getGameBoard().getSquares()[toRow][toCol].getPiece() != null) {
                                // piece on TO square must be on opposite team
                                if (!(currGame.getGameBoard().getSquares()[toRow][toCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor()))) {
                                    // checks that if its piece buzz, laser must be working
                                    if (currentPieceType instanceof PieceBuzz) {
                                        if (((PieceBuzz) currentPieceType).canAttack()) {
                                            // check if valid attack path
                                            if (((PieceBuzz) currentPieceType).validAttackPath(fromRow,fromCol,toRow,toCol)) {
                                                isActionTrue = true;
                                            }
                                        }
                                    }
                                    // comes back to piece BlueHen
                                    if (currentPieceType instanceof PieceBlueHen) {
                                        // checks valid attack path
                                        if (((PieceBlueHen) currentPieceType).validAttackPath(fromRow,fromCol,toRow,toCol)) {
                                            isActionTrue = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // if current piece is an EVIL MINION
                    if (currentPieceType instanceof PieceEvilMinion) {
                        // piece on FROM square must be on current team
                        if (currentPieceType.getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                            // checks if PieceEvilMinion is hungry
                            if (((PieceEvilMinion) currentPieceType).canAttack()) {
                                // checks if valid attack path
                                if (((PieceEvilMinion) currentPieceType).validAttackPath(fromRow,fromCol,toRow,toCol)) {
                                    // check if there is a piece on TO square
                                    if (currGame.getGameBoard().getSquares()[toRow][toCol].getPiece() != null) {
                                        // checking OPPONENT pieces on TO square
                                        if (!(currGame.getGameBoard().getSquares()[toRow][toCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor()))) {
                                            isActionTrue = true;
                                        }
                                        // checking if TO square has a CURRENT TEAM piece
                                        if (currGame.getGameBoard().getSquares()[toRow][toCol].getPiece().getTeamColor().equals(currGame.getCurrentTeam().getTeamColor())) {
                                            // if current team piece is MINION
                                            if (currGame.getGameBoard().getSquares()[toRow][toCol].getPiece() instanceof PieceMinion) {
                                                isActionTrue = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (currentPieceType instanceof PieceAppa) {
                        if (((PieceAppa) currentPieceType).validAttackPath(fromRow,fromCol,toRow,toCol)) {
                            if (((PieceAppa) currentPieceType).getNumRecruits() < 1) {
                                isActionTrue = true;
                            }
                        }
                    }
                }
            }
        }
        return isActionTrue;

    }

    // MAIN TESTS
    public static void main(String[] arg){
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(new PieceMinion('M',"Blu",
                0,0,false,true));
        piecesTeamA.add(new PieceBuzz('B',"Blu",2,1,
                true,false,true));
        piecesTeamA.add(new PieceBlueHen('H',"Blu",3,
                9,false,true));
        piecesTeamA.add(new PieceEvilMinion('E',"Blu",1,
                1,4,false, true));
        // Create a team object
        Team teamA = new Team("Blu",piecesTeamA);

        // Create 4 pieces for team B
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(new PieceMinion('M',"Red",
                0,0,false,true));
        piecesTeamB.add(new PieceBlueHen('H',"Red",3,
                9,false,true));
        piecesTeamB.add(new PieceBuzz('B',"Red",2,1,
                true,false,true));
        piecesTeamB.add(new PieceEvilMinion('E',"Red",1,
                1,4,false, true));
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);

        // create a game
        GameS22 game = new GameS22(6,6,teamA,teamB);

        // clear Piece off the board
        for(int row = 0; row < game.getGameBoard().getNumRows();row++){
            for (int col = 0; col < game.getGameBoard().getNumColumns(); col++){
                game.getGameBoard().getSquares()[row][col].removePiece();
            }
        }
        System.out.println(game);

        // load your pieces in specific locations of your choice
        game.getGameBoard().getSquares()[0][0].setPiece(piecesTeamA.get(0));
        game.getGameBoard().getSquares()[0][1].setPiece(piecesTeamA.get(1));
        game.getGameBoard().getSquares()[0][2].setPiece(piecesTeamA.get(2));
        game.getGameBoard().getSquares()[0][3].setPiece(piecesTeamA.get(3));
        game.getGameBoard().getSquares()[2][0].setPiece(piecesTeamB.get(0));
        game.getGameBoard().getSquares()[2][1].setPiece(piecesTeamB.get(1));
        game.getGameBoard().getSquares()[2][2].setPiece(piecesTeamB.get(2));
        game.getGameBoard().getSquares()[2][3].setPiece(piecesTeamB.get(3));

        System.out.println(game);
        /**
         // Test some moves that should be valid
         // Test some moves that are invalid
         System.out.println("T");
         // This should be a valid move
         System.out.println(Rules.checkValidAction(game,
         0,0,
         1,4,'M'));
         System.out.println("F");
         // To Square isn't empty - should not be a valid move
         System.out.println(Rules.checkValidAction(game,
         0,0,
         0,1,'M'));
         // This isn't current team's piece - should not be valid
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,
         2,0,
         0,5,'M'));

         */


        System.out.println("MOVE: BLUE TEAM TURN all passed");
        System.out.println("F"); // can't move into a non-empty space
        System.out.println(Rules.checkValidAction(game,0,3,2,3,'M'));
        System.out.println("F"); // not red's turn
        System.out.println(Rules.checkValidAction(game,2,2,1,2,'M'));
        System.out.println("T"); // can move into an empty space
        System.out.println(Rules.checkValidAction(game,0,3,5,5,'M'));
        System.out.println("T"); // can move into an empty space
        System.out.println(Rules.checkValidAction(game,0,2,5,2,'M'));


        System.out.println("TESTING SPAWN: BLUE TEAM TURN");

        System.out.println("T"); // Minion can spawn a piece into an empty space
        System.out.println(Rules.checkValidAction(game,0,0,1,0,'S'));
        System.out.println("F"); // Buzz can't spawn
        System.out.println(Rules.checkValidAction(game,0,1,1,0,'S'));
        System.out.println("F"); // Can't spawn into a non-empty board square
        System.out.println(Rules.checkValidAction(game,0,2,2,2,'S'));
        System.out.println("F"); // fromSquare is empty
        System.out.println(Rules.checkValidAction(game,4,4,1,3,'S'));




        System.out.println("RECRUIT: BLUE TEAM TURN");

        System.out.println("F"); // Buzz can't recruit
        System.out.println(Rules.checkValidAction(game,0,1,2,1,'R'));
        System.out.println("T"); // Blue hen can recruit an enemy piece
        System.out.println(Rules.checkValidAction(game,0,2,2,1,'R'));
        System.out.println("F"); // Not red's turn
        System.out.println(Rules.checkValidAction(game,2,1,0,2,'R'));
        System.out.println("T"); // Minion can recruit
        System.out.println(Rules.checkValidAction(game,0,0,2,0,'R'));
        System.out.println("T"); // Minion can recruit
        System.out.println(Rules.checkValidAction(game,0,3,2,1,'R'));



        System.out.println("ATTACK: BLUE TEAM TURN");

        System.out.println("F"); // Minions can't attack
        System.out.println(Rules.checkValidAction(game,0,0,2,1,'A'));
        System.out.println("T"); // Buzz can attack enemy piece
        System.out.println(Rules.checkValidAction(game,0,1,2,0,'A'));
        System.out.println("T"); // Evil minion can attack team piece minion
        System.out.println(Rules.checkValidAction(game,0,3,0,0,'A'));
        System.out.println("F"); // Evil minion can't attack a team piece blue hen
        System.out.println(Rules.checkValidAction(game,0,3,0,1,'A'));
        System.out.println("T"); // Hen can attack enemy pieces
        System.out.println(Rules.checkValidAction(game,0,2,2,2,'A'));


        // You can change the turn to test the other team pieces
        game.changeTurn();

        /**
         * TESTING MOVE: RED TEAM  TURN all passed
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,
         2,0,
         0,5,'M'));
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,2,2,1,2,'M'));
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,2,0,5,0,'M'));
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,0,1,1,2,'M'));
         */

        /**
         * TESTING SPAWN: RED TEAM TURN all passed
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,2,2,4,2,'S'));
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,0,0,5,0,'S'));
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,2,3,1,2,'S'));
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,4,2,2,2,'S'));
         */

        /**
         * TESTING RECRUIT: RED TEAM TURN all pass
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,2,2,0,3,'R'));
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,2,0,0,1,'R'));
         System.out.println("F");
         System.out.println(Rules.checkValidAction(game,3,1,0,2,'R'));
         System.out.println("T");
         System.out.println(Rules.checkValidAction(game,2,3,0,0,'R'));
         */
    }


}
