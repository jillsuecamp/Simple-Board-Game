public class PieceBlueHen extends Piece implements Attacker, Recruiter {
    // PARTNER: JILLIAN CAMP
    private int numAttacks;
    private int numRecruits;
    private boolean flies;

    final public int MAX_NUM_ATTACKS = 3;

    public PieceBlueHen (char symbol,
                         String teamColor,
                         int numAttacks, int numRecruits,
                         boolean hidden, boolean original){
        super(symbol,teamColor,hidden,original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        updateFly();
    }

    public PieceBlueHen ()  {
        this('H',"NON",
                0,0,
                false,true);
    }
    @Override
    public int getNumAttacks()    {
        return this.numAttacks;
    }
    @Override
    public int getNumRecruits(){
        return this.numRecruits;
    }


    public boolean canFly()    {
        return this.flies;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    @Override
    public void setNumAttacks(int numAttacks)    {
        this.numAttacks = numAttacks;
        updateFly();
    }
    @Override
    public void setNumRecruits(int numRecruits)    {
        this.numRecruits = numRecruits;
    }

    private void updateFly()    {
        if (this.numAttacks < MAX_NUM_ATTACKS){
            this.flies = true;
        }
        else {
            this.flies = false;
        }
    }
    public void speak(){
        System.out.println("Go UD!");
    }

    public boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                 int toSquareRow, int toSquareCol) {
        if (this.numAttacks < MAX_NUM_ATTACKS){ //this condition means that pieceBlueHen can fly
            return true;// the piece can fly to any spot on the board
        }
        else if(toSquareCol == fromSquareCol + 1 || toSquareCol == fromSquareCol -1){ //if the piece moved up 1 or down 1
            return true;
        }
        else if(toSquareRow == fromSquareRow + 1 || toSquareRow == fromSquareRow -1){//if the piece moved R 1 or L 1
            return true;
        }
        else if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol -1){//if piece moved diagonally up-left
            return true;
        }
        else if(toSquareRow == fromSquareRow +1 && toSquareCol == fromSquareCol - 1){//if piece moved diagonally down-left
            return true;
        }
        else if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol +1){//if piece moved diagonally up-right
            return true;
        }
        else if(toSquareRow == fromSquareRow + 1 && toSquareCol == fromSquareCol +1){ //if piece moved diagonally down-right
            return true;
        }
        else{
            return false; // if the hen can't fly, and it moves more than 1 space in any given direction, then it is not a valid move
        }
    }
    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                  int toSquareRow, int toSquareCol) {
        boolean validSpawn = false;
        if (this.numAttacks < MAX_NUM_ATTACKS){
            validSpawn = true;
        }
        else if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol -1){//if piece moved diagonally up-left
            validSpawn = true;
        }
        else if(toSquareRow == fromSquareRow +1 && toSquareCol == fromSquareCol - 1){//if piece moved diagonally down-left
            validSpawn = true;
        }
        else if(toSquareRow == fromSquareRow -1 && toSquareCol == fromSquareCol +1){//if piece moved diagonally up-right
            validSpawn = true;
        }
        else if(toSquareRow == fromSquareRow + 1 && toSquareCol == fromSquareCol +1){ //if piece moved diagonally down-right
            validSpawn = true;
        }
        else{
            validSpawn = false;
        }
        return validSpawn;
    }
    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
        boolean validRecruit = false;
        if (this.numAttacks < MAX_NUM_ATTACKS){
            validRecruit = true;
        }
        else if (toSquareCol == fromSquareCol) { //Ensures the piece is moving only up and down
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
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol ){
        boolean validAttack = false;
        if (this.numAttacks < MAX_NUM_ATTACKS){
            validAttack = true;
        }
        else if(toSquareRow == fromSquareRow){ //Ensures the piece is only moving left and right
            if(toSquareCol == fromSquareCol +1 || toSquareCol == fromSquareCol -1){ //Ensures the piece is only moving +1 or -1
                validAttack = true;
            }
        }
        else{
            validAttack = false;
        }
        return validAttack;
    }
    public PieceBlueHen spawn()    {
        PieceBlueHen copyHen =
                new PieceBlueHen(Character.toLowerCase(this.symbol),
                        this.teamColor,this.numAttacks,this.numRecruits,
                        false,false);
        return copyHen;
    }

    public boolean canSpawn(){
        return true;
    }
}
