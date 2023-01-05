//NEW EXTENDED PIECE MODIFICATION

public class PieceBabyBlue extends PieceBlueHen{

    public PieceBabyBlue(char symbol,
                         String teamColor,
                         boolean hidden, boolean original) {
        super(symbol,teamColor,0,0,hidden,original);
    }

    public PieceBabyBlue() {
        this('L',"- -", false, true);
    }

    public boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                 int toSquareRow, int toSquareCol) {
        boolean canMove = false;
        // checking top 3 squares (row always one minus the from row)
        if (toSquareRow == (fromSquareRow -1) && (toSquareCol == fromSquareCol-1)) {
            canMove = true;
        }
        if (toSquareCol == fromSquareRow-1 && toSquareCol == fromSquareCol) {
            canMove = true;
        }
        if (toSquareCol == fromSquareRow-1 && toSquareCol == fromSquareCol+1) {
            canMove = true;
        }
        // checking bottom 3 squares (row always one plus the from row)
        if (toSquareRow == fromSquareRow + 1 && toSquareCol == fromSquareCol-1) {
            canMove = true;
        }
        if (toSquareRow == fromSquareRow+1 && toSquareCol == fromSquareCol) {
            canMove = true;
        }
        if (toSquareRow == fromSquareRow+1 && toSquareCol == fromSquareCol+1) {
            canMove = true;
        }
        // checking two squares left and right of the from square
        if (toSquareRow == fromSquareRow && (toSquareCol == fromSquareCol-1 || toSquareCol == fromSquareCol+1)) {
            canMove = true;
        }
        else {
            canMove = false;
        }
        return canMove;
    }
    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                  int toSquareRow, int toSquareCol) {
        // You will implement this method in a later step
        // each Piece will have a different valid path
        return false;
    }
    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
        boolean validRecruit = false;
        if (toSquareCol == fromSquareCol) { //Ensures the piece is moving only up and down
            if (toSquareRow == fromSquareRow + 1 || toSquareRow == fromSquareRow - 1) { //Ensures the piece is only moving +1 or -1
                validRecruit = true;
            }
        }
        else{
            validRecruit = false;
        }
        return validRecruit;
    }
    @Override
    public boolean validAttackPath(int xPieceAttacking, int yPieceAttacking, int xPieceAttacked, int yPieceAttacked ){
        return false;
    }

    public void speak() {
        System.out.println("Let's go UD!");
    }
}
