public class PieceMinion extends Piece implements Recruiter {
    // PARTNER: JILLIAN CAMP
    protected int numRecruits;
    protected int numTimesSpawned;

    public static int MAX_NUM_SPAWNED = 3;

    public PieceMinion(char symbol, String teamColor,
                       int numRecruits, int numTimesSpawned,
                       boolean hidden, boolean original) {
        super(symbol,teamColor,hidden,original);
        this.numRecruits = numRecruits;
        this.numTimesSpawned = numTimesSpawned;
    }

    public PieceMinion(){
        this('M',"- -",
                0,0,
                false,true);
    }


    @Override
    public int getNumRecruits() {
        return numRecruits;
    }
    public int getNumTimesSpawned() {
        return numTimesSpawned;
    }

    @Override
    public void setNumRecruits(int numRecruits) {
        this.numRecruits = numRecruits;
    }

    public void speak(){
        System.out.println("Bello!");
    }

    public boolean validMovePath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        boolean canMove = false;
        if (toSquareCol == fromSquareCol) {
            canMove = true;
        }
        else if (toSquareRow == fromSquareRow) {
            if (toSquareCol == fromSquareCol+2 || toSquareCol == fromSquareCol-2) {
                canMove = true;
            }
        }
        else {
            canMove = false;
        }
        return canMove;
    }

    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        boolean canRecruit = false;
        if (toSquareCol == fromSquareCol) {
            canRecruit = true;
        }
        else if (toSquareRow == fromSquareRow) {
            if (toSquareCol == fromSquareCol+2 || toSquareCol == fromSquareCol-2) {
                canRecruit = true;
            }
        }
        else {
            canRecruit = false;
        }
        return canRecruit;
    }

    @Override
    public boolean validSpawnPath(int xPieceRecruiting, int yPieceRecruiting, int xPieceRecruited, int yPieceRecruited) {
        return true;
    }

    public PieceMinion spawn(){
        return new PieceMinion(Character.toLowerCase(this.symbol),
                this.teamColor,1,
                0,
                false,
                false);
    }


    public boolean canSpawn(){
        return original && numTimesSpawned < MAX_NUM_SPAWNED;
    }
}
