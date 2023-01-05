import java.util.Scanner;
public class TextView {
    //
    public int fromRow;
    public int fromCol;
    public int toRow;
    public int toCol;
    public char action;

    public void TextView() {
        Scanner scr = new Scanner(System.in);
    }

    public int getFromRow() {
        return this.fromRow;
    }

    public int getFromCol() {
        return this.fromCol;
    }

    public int getToRow() {
        return this.toRow;
    }

    public int getToCol() {
        return this.toCol;
    }

    public char getAction() {
        return this.action;
    }

    public static char getUsersNextActionType(Scanner scr) {
        /**
         * takes first value of user input (an a, m, r, or s, lowercase or upper), rejecting anything else
         * @param scr - input from user
         * @return finalCharacter - the first valid character that the user inputs
         */
        boolean validChar = false;
        String currString = " ";
        char finalCharacter = ' ';
        char currentChar = ' ';

        while (!validChar) {
            System.out.println("Enter A to attack, M to move, R to recruit, or S to spawn.");
            new Scanner(System.in);
            if (scr.hasNext()) {
                currString = scr.next();
                currentChar = currString.charAt(0);
                if (currentChar == 'A' || currentChar == 'M' || currentChar == 'R' || currentChar == 'S' || currentChar == 'a' || currentChar == 'm' || currentChar == 'r' || currentChar == 's') {
                    validChar = true;
                    finalCharacter = currentChar;
                }
                else {
                    System.out.println("Not a valid character. Please try again.");
                }
            }
            else {
                System.out.println("Not a valid character. Please try again.");
            }


        }
        return finalCharacter;
    }

    public static char getPieceBabyBlueNextActionType(Scanner scr) {
        /**
         * takes first value of user input (an a, m, r, or s, lowercase or upper), rejecting anything else
         * @param scr - input from user
         * @return finalCharacter - the first valid character that the user inputs
         */
        boolean validChar = false;
        String currString = " ";
        char finalCharacter = ' ';
        char currentChar = ' ';

        while (!validChar) {
            System.out.println("You've selected a PieceBabyBlue: Enter A to attack, M to move, R to recruit, S to spawn, or P to shake PomPoms.");
            new Scanner(System.in);
            if (scr.hasNext()) {
                currString = scr.next();
                currentChar = currString.charAt(0);
                if (currentChar == 'A' || currentChar == 'M' || currentChar == 'R' || currentChar == 'S' || currentChar == 'a' || currentChar == 'm' || currentChar == 'r' || currentChar == 's' || currentChar == 'p' || currentChar == 'P') {
                    validChar = true;
                    finalCharacter = currentChar;
                }
                else {
                    System.out.println("Not a valid character. Please try again.");
                }
            }
            else {
                System.out.println("Not a valid character. Please try again.");
            }


        }
        return finalCharacter;
    }

    public static char getPieceAppaNextActionType(Scanner scr) {
        /**
         * takes first value of user input (an a, m, r, or s, lowercase or upper), rejecting anything else
         * @param scr - input from user
         * @return finalCharacter - the first valid character that the user inputs
         */
        boolean validChar = false;
        String currString = " ";
        char finalCharacter = ' ';
        char currentChar = ' ';

        while (!validChar) {
            System.out.println("You've selected a PieceAppa. Enter A to attack, M to move, R to recruit, S to spawn, or O to slobber (if you have already attacked with Appa before.)");
            new Scanner(System.in);
            if (scr.hasNext()) {
                currString = scr.next();
                currentChar = currString.charAt(0);
                if (currentChar == 'A' || currentChar == 'M' || currentChar == 'R' || currentChar == 'S' || currentChar == 'a' || currentChar == 'm' || currentChar == 'r' || currentChar == 's' || currentChar == 'O' || currentChar == 'o') {
                    validChar = true;
                    finalCharacter = currentChar;
                }
                else {
                    System.out.println("Not a valid character. Please try again.");
                }
            }
            else {
                System.out.println("Not a valid character. Please try again.");
            }


        }
        return finalCharacter;
    }

    public static int getValidInt(int minValue, int maxValue, Scanner scr) {
        /**
         * given a min and max, takes input from user until they enter an integer in between the two
         * @param minValue - the minimum value the entered integer can be
         * @param maxValue - the maximum value the entered integer can be
         * @param scr - input from user
         * @return currentVal - the int entered that falls between min and max and is an int
         */
        boolean validInt = false;
        int currentVal = 0;

        while (!validInt) {
            // System.out.println("Enter an integer between " + minValue + " and " + maxValue + ".");
            new Scanner(System.in);
            if (scr.hasNextInt()) {
                currentVal = scr.nextInt();
                if (currentVal >= minValue && currentVal <= maxValue) {
                    validInt = true;
                }
                else {
                    System.out.println("Integer not in range. Please try again.");
                }
            }
            else {
                scr.next();
                System.out.println("Not a valid integer value. Please try again.");
            }
        }
        return currentVal;
    }

    // repeatedly calls get valid int to get the integers of from square and to square indexes
    public void getNextPlayersAction(GameS22 currGame) {
        Scanner scr = new Scanner(System.in);
        int gameRows = currGame.getGameBoard().getNumRows()-1;
        int gameCols = currGame.getGameBoard().getNumColumns()-1;
        boolean validFromSquare = false;
        while (!validFromSquare) {
            System.out.println("Enter the row number of the from square you want to perform an action between 0 and " + gameRows + ":");
            this.fromRow = getValidInt(0, (currGame.getGameBoard().getNumRows())-1,scr);
            System.out.println("Enter the column number of the from square you want to perform an action between 0 and " + gameCols + ":");
            this.fromCol = getValidInt(0, (currGame.getGameBoard().getNumColumns())-1,scr);
            if (!(currGame.getGameBoard().getSquares()[this.fromRow][this.fromCol].isEmpty())) {
                if (currGame.getGameBoard().getSquares()[this.fromRow][this.fromCol].getPiece() instanceof PieceBabyBlue) {
                    this.action = getPieceBabyBlueNextActionType(scr);
                    validFromSquare = true;
                    if (this.action == 'P' || this.action == 'p') {
                        // no need to select to row and col; it returns a random piece on removed team.
                        this.toRow = 0;
                        this.toCol = 0;
                    }
                }
                else if (currGame.getGameBoard().getSquares()[this.fromRow][this.fromCol].getPiece() instanceof PieceAppa) {
                    this.action = getPieceAppaNextActionType(scr);
                    validFromSquare = true;
                    if (this.action == 'O' || this.action == 'o') {
                        // no need to select row and col
                        this.toRow = 0;
                        this.toCol = 0;
                    }
                }
                else {
                    this.action = getUsersNextActionType(scr);
                    validFromSquare = true;
                }
            }
            else {
                System.out.println("Square is empty. Please try again");
                validFromSquare = false;
            }
        }
        System.out.println("Enter the row number of the to square you want to target between 0 and " + gameRows + ":");
        this.toRow = getValidInt(0, (currGame.getGameBoard().getNumRows())-1,scr);
        System.out.println("Enter the column number of the to square you want to target between 0 and " + gameCols + ":");
        this.toCol = getValidInt(0, (currGame.getGameBoard().getNumColumns())-1,scr);
    }

    // prints game board
    public void updateView(Game currGame) {
        System.out.println(currGame);
    }

    // prints final message and the winner
    public void printEndOfGameMessage(Game currGame) {
        System.out.print("Game has ended! Winner: Team " + currGame.getWinner().getTeamColor());
    }
}
