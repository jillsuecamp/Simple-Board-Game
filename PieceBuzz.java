public class PieceBuzz extends Piece implements Attacker {
    // PARTNER: JILLIAN CAMP
    private int numAttacks;
    private int numTimesBeenAttacked;
    private boolean workingLaser;

    public PieceBuzz(char symbol,
                     String teamColor,
                     int numAttacks,
                     int numTimesBeenAttacked,
                     boolean workingLaser,
                     boolean hidden,
                     boolean original) {
        super(symbol,teamColor,hidden,original);
        this.numAttacks = numAttacks;
        this.numTimesBeenAttacked = numTimesBeenAttacked;
        this.workingLaser = workingLaser;
    }

    public PieceBuzz(){
        this('B',"- -",
                0,0,
                true, false, true);
    }
    @Override
    public int getNumAttacks() {
        return numAttacks;
    }
    public int getNumTimesBeenAttacked() {
        return numTimesBeenAttacked;
    }
    public boolean canAttack(){
        return workingLaser;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setWorkingLaser(boolean workingLaser) {
        this.workingLaser = workingLaser;
    }

    @Override
    public void setNumAttacks(int numAttacks)  {
        this.numAttacks = numAttacks;
    }

    public void updateNumTimesBeenAttacked(){
        this.numTimesBeenAttacked += 1;
        this.workingLaser = false;
    }

    public void speak(){
        System.out.println("To Infinity and Beyond!");
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol ){
        boolean canRecruit = false;
        // any square in the same row as it
        if (toSquareRow == fromSquareRow) {
            canRecruit = true;
        }
        // two spaces up or down (same col, different row)
        else if (toSquareCol == fromSquareCol) {
            if (toSquareRow == fromSquareRow+2 || toSquareRow == fromSquareRow-2) {
                canRecruit = true;
            }
        }
        else {
            canRecruit = false;
        }
        return canRecruit;
    }
    @Override
    public boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                 int toSquareRow, int toSquareCol) {
        // can go anywhere on board, always true
        return true;
    }
    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                  int toSquareRow, int toSquareCol) {
        // You will implement this method in a later step
        // each Piece will have a different valid path
        return false;
    }

    public Piece spawn(){
        return null;
    }
    public boolean canSpawn(){
        return false;
    }
}
