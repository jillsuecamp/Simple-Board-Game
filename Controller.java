import java.util.ArrayList;


public class Controller {
    private GameS22 currGame;
    public TextView view;

    public GameS22 setUpGameModel(){
        // Create 4 pieces for team A

        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(new PieceMinion('M',"Blu",
                0,0,false,true));
        piecesTeamA.add(new PieceBuzz('B',"Blu",0,0,
                true,false,true));
        piecesTeamA.add(new PieceBlueHen('H',"Blu",0,
                0,false,true));
        piecesTeamA.add(new PieceEvilMinion('E',"Blu",0,
                0,0,false, true));
        piecesTeamA.add(new PieceBabyBlue('L',"Blu",false,true));
        piecesTeamA.add(new PieceAppa('A',"Blu",false,true,0,0,false,true));
        // Create a team object
        Team teamA = new Team("Blu",piecesTeamA);

        // Create 4 pieces for team B
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(new PieceMinion('M',"Red",
                0,0,false,true));
        piecesTeamB.add(new PieceBlueHen('H',"Red",0,
                0,false,true));
        piecesTeamB.add(new PieceBuzz('B',"Red",0,0,
                true,false,true));
        piecesTeamB.add(new PieceEvilMinion('E',"Red",0,
                0,0,false, true));
        piecesTeamB.add(new PieceBabyBlue('L',"Red",false,true));
        piecesTeamB.add(new PieceAppa('A',"Red",false,true,0,0,false,true));
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);

        // Create an instance of the game
        return new GameS22(8, 8,teamA, teamB);
    }

    // sets up game, creates a text view, and prints it
    public Controller() {
        this.currGame = setUpGameModel();
        this.view = new TextView();
        this.view.updateView(this.currGame);
    }

    // given the action, performs the action based on the passed from square and to square indexes
    public void carryOutAction(int fromRow, int fromCol, int toRow, int toCol, char action) {
        if (action == 'M' || action == 'm') {
            ActionMove move = new ActionMove(this.currGame,fromRow,fromCol,toRow,toCol);
            move.performAction();
        }
        else if (action == 'S' || action == 's') {
            ActionSpawn spawn = new ActionSpawn(this.currGame,fromRow,fromCol,toRow,toCol);
            spawn.performAction();
        }
        else if (action == 'R' || action == 'r') {
            ActionRecruit recruit = new ActionRecruit(this.currGame,fromRow,fromCol,toRow,toCol);
            recruit.performAction();
        }
        else if (action == 'A' || action == 'a') {
            ActionAttack attack = new ActionAttack(this.currGame,fromRow,fromCol,toRow,toCol);
            attack.performAction();
        }
        else if (action == 'P' || action == 'p') {
            ActionShakePomPoms shakePomPoms = new ActionShakePomPoms(this.currGame,fromRow,fromCol,toRow,toCol);
            shakePomPoms.performAction();
        }
        else if (action == 'O' || action == 'o') {
            ActionSlobber slobber = new ActionSlobber(this.currGame,fromRow,fromCol,toRow,toCol);
            slobber.performAction();
        }

    }

    public void playGame() {
        boolean check = false;
        boolean isGameOver = false;
        // hidden square moD
        //BOARD SQUARE MODIFICATION
        this.currGame.getGameBoard().createHiddenSquare();
        System.out.println(currGame.getGameBoard().getHiddenSquareRow() + ", " + currGame.getGameBoard().getHiddenSquareCol()); // 4
        // THIS PRINTS HIDDEN SQUARE FOR TESTING
        while (!isGameOver) {
            // hidden square mod: if theres a piece on hidden square, print it out
            if (!(this.currGame.getGameBoard().getSquares()[currGame.getGameBoard().getHiddenSquareRow()][currGame.getGameBoard().getHiddenSquareCol()].isEmpty())) {
                System.out.println(currGame.getGameBoard().getSquares()[currGame.getGameBoard().getHiddenSquareRow()][currGame.getGameBoard().getHiddenSquareCol()].getPiece() + " landed on a hidden square located at (" + currGame.getGameBoard().getHiddenSquareRow() + "," + currGame.getGameBoard().getHiddenSquareCol() + ").");
            }
            // while loop to make sure the player's action is valid
            while (!check) {
                this.view.getNextPlayersAction(this.currGame);
                if (!(Rules.checkValidAction(this.currGame,this.view.getFromRow(),this.view.getFromCol(),this.view.getToRow(),this.view.getToCol(), this.view.getAction()))) {
                    System.out.println("Not a valid action, try again");
                }
                else {
                    // true once players actions are valid, breaks out of loop
                    check = true;
                }
            }
            // carries out action and prints the new board
            carryOutAction(this.view.getFromRow(),this.view.getFromCol(), this.view.getToRow(),this.view.getToCol(),this.view.getAction());
            System.out.println(this.currGame);
            // if game is over, breaks the loop
            if (this.currGame.isGameEnded()) {
                isGameOver = true;
            }
            // sets check to false to get ready for new input
            check = false;
        }
        if (this.currGame.isAWinner()) {
            this.view.printEndOfGameMessage(this.currGame);
        }

    }

    public static void main(String[] args) {
        Controller game = new Controller();
        game.playGame();
    }
}
