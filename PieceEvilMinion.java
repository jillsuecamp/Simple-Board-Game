public class PieceEvilMinion extends PieceMinion implements Attacker, Recruiter {
    // PARTNER: JILLIAN CAMP
    // variables uncommon with pieceMinion and Piece
    private int numAttacks;
    private boolean hungry;

    // constant uncommon with pieceMinion
    public static int MAX_NUM_ATTACKS = 4;

    // 7 param constructor calling super class
    public PieceEvilMinion(char symbol, String teamColor, int numRecruits, int numAttacks, int numTimesSpawned, boolean hidden, boolean original) {
        super(symbol, teamColor, numRecruits, numTimesSpawned, hidden, original);
        this.numAttacks = numAttacks;
        updateHungry();
    }

    // 0 param constructor calling 7 param constructor
    public PieceEvilMinion() {
        this('E',"NON",0,0,0,false,true);
    }

    // uncommon accessor methods with pieceMinion
    public boolean canAttack() {
        return hungry;
    }
    @Override
    public int getNumAttacks() {
        return this.numAttacks;
    }

    @Override
    public int getNumRecruits(){
        return this.numRecruits;

    }
    // uncommon mutator methods with pieceMinion
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    @Override
    public void setNumRecruits(int numRecruits){
        this.numRecruits = numRecruits;
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol ){
        boolean canAttack = false;
        if (toSquareCol == fromSquareCol) {
            canAttack = true;
        }
        else if (toSquareRow == fromSquareRow) {
            if (toSquareCol == fromSquareCol+2 || toSquareCol == fromSquareCol-2) {
                canAttack = true;
            }
        }
        else {
            canAttack = false;
        }
        return canAttack;
    }

    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
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
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol){
        boolean canSpawn = false;
        if (toSquareCol == fromSquareCol) {
            canSpawn = true;
        }
        else if (toSquareRow == fromSquareRow) {
            if (toSquareCol == fromSquareCol+2 || toSquareCol == fromSquareCol-2) {
                canSpawn = true;
            }
        }
        else {
            canSpawn = false;
        }
        return canSpawn;
    }

    public void setNumTimesSpawned(int numTimesSpawned) {
        this.numTimesSpawned = numTimesSpawned;
    }

    // updates hungry, evilMinion is always hungry unless he has reached max attacks
    public void updateHungry() {
        if (this.numAttacks < MAX_NUM_ATTACKS) {
            this.hungry = true;
        }
        else {
            this.hungry = false;
        }
    }

    public void speak() {
        System.out.println("Roar!");
    }

    public boolean validMovePath() {
        return true;
    }

    // spawns a mini evilMinion
    public PieceEvilMinion spawn() {
        this.numTimesSpawned += 1;
        return new PieceEvilMinion(Character.toLowerCase(this.symbol),this.teamColor,
                1,0,0,false,false);
    }
}
