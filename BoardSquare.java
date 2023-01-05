public class BoardSquare {
    // PARTNER: JILLIAN CAMP
    // member fields
    private boolean empty;
    private Piece currentPiece;
    private String color;

    // constructor; automatically sets empty to true!!
    public BoardSquare(String color){
        this.color = color;
        this.empty = true;
    }

    //accessors
    public Piece getPiece(){
        return this.currentPiece;
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public String getSquareColor(){
        return this.color;
    }

    // mutators:
    // setPiece takes a current piece parameter and sets it to the objects current piece,
    // sets boardSquare to not empty
    public void setPiece(Piece currentPiece){
        this.currentPiece = currentPiece;
        this.empty = false;
    }
    // removePiece sets object's current piece to null and sets empty to true
    public Piece removePiece(){
        Piece temp = this.currentPiece;
        this.currentPiece = null;
        this.empty = true;
        return temp;
    }

    // toString override
    public String toString(){
        String str = "";
        if (empty){
            str = str + "-------";
        }
        else{
            str = str + "-" + currentPiece.toString() + "-";

        }
        return str;
    }
}
