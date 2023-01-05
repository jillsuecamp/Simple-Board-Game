public interface Recruiter {
    public abstract int getNumRecruits();

    public abstract void setNumRecruits(int numRecruits);

    public abstract boolean validRecruitPath(int rowPieceRecruiting, int colPieceRecruiting, int rowPieceRecruited, int colPieceRecruited);


}
