import java.sql.Array;
import java.util.ArrayList;
import java.lang.Math;


public class GameBoard {
    // PARTNER: JILLIAN CAMP
    // member fields
    private int numRows;
    private int numColumns;
    private BoardSquare[][] squares;
    // MODIFICATION: hidden board square
    private int hiddenSquareRow;
    private int hiddenSquareCol;

    // constructor
    public GameBoard(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        squares = new BoardSquare[numRows][numColumns];
        setUpEmptyBoard();
    }

    // accessors
    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public BoardSquare[][] getSquares() {
        return this.squares;
    }

    // MODIFICATION GETTERS
    public int getHiddenSquareRow() {
        return this.hiddenSquareRow;
    }

    public int getHiddenSquareCol() {
        return this.hiddenSquareCol;
    }

    // inBounds method:
    // if parameters numRows and numColumns are greater than or equal to the object's
    // numRows and numColumns, or if parameters are negative, returns false. otherwise, true
    public boolean inBounds(int numRows, int numColumns) {
        boolean isTrue;
        if (numRows >= this.numRows || numColumns >= this.numColumns || numRows < 0 || numColumns < 0) {
            isTrue = false;
        }
        else {
            isTrue = true;
        }
        return isTrue;
    }

    // setUpEmptyBoard method:
    // iterates through each row and in each row, iterates through columns, changing
    // the color every time it runs through the columns for loop. If columns is even, adds
    // another count to colorSwitch to account for it. creates new squares in the second for loop
    // and assigns it to different colors.
    private void setUpEmptyBoard() {
        int colorSwitch = 2; // even = white, odd = black
        for (int i = 0; i < this.numRows; i++) {
            if (this.numColumns % 2 != 0) {
                colorSwitch += 1;
            }
            for (int j = 0; j < this.numColumns; j++) {
                if (colorSwitch % 2 == 0) {
                    squares[i][j] = new BoardSquare("White");
                }
                else if (colorSwitch % 2 != 0) {
                    squares[i][j] = new BoardSquare("Black");
                }
                colorSwitch += 1;
            }
        }

    }

    // findRandomEmptySpace method:
    // when called, goes through loop and sets randRow and randCol to random ints between
    // 0 and the numRows or numColumns - 1. if the boardSquare at that location is empty,
    // returns it. if not, go through loop again.
    public BoardSquare findRandomEmptySpace() {
        while (true) {
            int randRow = (int) (Math.floor(Math.random() * (this.numRows)));
            int randCol = (int) (Math.floor(Math.random() * (this.numColumns)));

            if (squares[randRow][randCol].isEmpty()) {
                return squares[randRow][randCol];
            }
        }
    }

    // MODIFICATION: creating hidden square
    public void createHiddenSquare() {
        this.hiddenSquareRow = (int) (Math.floor(Math.random() * (this.numRows)));
        this.hiddenSquareCol = (int) (Math.floor(Math.random() * (this.numColumns)));
    }

    // toString
    @Override
    public String toString(){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :       ");

        for(int col = 0; col < squares[0].length; col++){
            boardString.append(col + "        ");
        }
        boardString.append("\n");
        for(int row = 0; row < squares.length; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < squares[row].length; col++){
                boardString.append(squares[row][col].toString() + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

}
