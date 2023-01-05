//NEW PIECE MODIFICATION

public class PieceAppa extends Piece implements Attacker, Recruiter {
    private boolean canSlobber;
    private int numAttacks;
    private int numRecruits;
    boolean canAttack;
    private int numTimesSlobbered;

    //Constructor for Appa
    public PieceAppa(char symbol, String teamColor, boolean hidden, boolean original,
                     int numAttacks, int numRecruits, boolean CanSlobber, boolean canAttack){
        super(symbol, teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        this.canSlobber = canSlobber;
        this.canAttack = canAttack;

    }

    //Default constructor for Appa
    public PieceAppa(){
        this('A', "- -", false, true, 0, 0, false, true);
    }

    //LIMITATION: if Appa has already recruited at least once piece, then he can no longer attack.
    public void updateNumTimesRecruited(){
        this.numRecruits += 1;
        this.canAttack = false;
    }


    public void speak(){
        System.out.println("Grruh");
    }

    // Appa can spawn

    public boolean canSpawn(){
        return true;
    }
    public Piece spawn(){
        return new PieceAppa(Character.toLowerCase(this.symbol), this.teamColor, false, false,
                0, 0, true, true);
    }

    //getters
    public int getNumAttacks(){
        return this.numAttacks;
    }

    public int getNumRecruits(){
        return this.numRecruits;
    }

    public int getNumTimesSlobbered() {
        return this.numTimesSlobbered;
    }
    //setters
    public void setNumAttacks(int num){
        this.numAttacks = num;
    }

    public void setNumRecruits(int num){
        this.numRecruits = num;
    }

    public void setNumTimesSlobbered(int num) {
        this.numTimesSlobbered = num;
    }

    //Appa can move however many spaces so long as it is only up/down/right/left
    public boolean validMovePath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
        boolean validMove = false;
        if (toSquareRow == fromSquareRow){//if he is moving to a square that is right or left (only changing col index)
            validMove = true;
        }
        else if (toSquareCol == fromSquareCol){ //if he is moving to a square that is up or down (only changing row index)
            validMove = true;
        }
        else{ //if the toSquare row&col index differ from the fromSquare's, this means he is moving diagonally (not allowed)
            validMove = false;
        }
        return validMove;
    }

    //Appa can only recruit the pieces that are diagonal from him.
    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        boolean validRecruit = false;
        if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol -1){//if piece moved diagonally up-left
            validRecruit = true;
        }
        else if(toSquareRow == fromSquareRow +1 && toSquareCol == fromSquareCol - 1){//if piece moved diagonally down-left
            validRecruit = true;
        }
        else if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol +1){//if piece moved diagonally up-right
            validRecruit = true;
        }
        else if(toSquareRow == fromSquareRow + 1 && toSquareCol == fromSquareCol +1){ //if piece moved diagonally down-right
            validRecruit = true;
        }
        else{
            validRecruit = false;
        }
        return validRecruit;
    }


    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        return true;
    }


    // Appa can only attack a piece directly in front of him (by in front of, we mean above him or under him)
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
        boolean validAttack = false;
        if (toSquareCol == fromSquareCol){ //Ensures he is only accessing the piece upwards from him
            if (toSquareRow == fromSquareRow -1 || toSquareRow == fromSquareRow +1){ //Ensures the piece is immediately above him
                validAttack = true;
            }
        }
        return validAttack;
    }
}
