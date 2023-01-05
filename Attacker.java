public interface Attacker {
    public abstract int getNumAttacks();
    public abstract void setNumAttacks(int num);
    public abstract boolean validAttackPath(int rowPieceAttacking, int colPieceAttacking, int rowPieceAttacked, int colPieceAttacked );

}
