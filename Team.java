import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Team {
    // PARTNER: JILLIAN CAMP
    // member fields
    private String teamColor;
    ArrayList<Piece> teamPieces = new ArrayList<Piece>();
    ArrayList<Piece> teamRemovedPieces = new ArrayList<Piece>();

    // constructor
    public Team(String teamColor, ArrayList<Piece> teamPieces) {
        this.teamColor = teamColor;
        this.teamPieces = teamPieces;
    }

    // accessor methods
    public String getTeamColor() {
        return this.teamColor;
    }

    public ArrayList<Piece> getTeamPieces() {
        return this.teamPieces;
    }

    public ArrayList<Piece> getTeamRemovedPieces() {
        return this.teamRemovedPieces;
    }

    public Piece getFirstPieceRemovedPiece() {
        return this.teamRemovedPieces.get(0);
    }

    // mutator methods:
    // removePieceFromTeam iterates through a Piece array and when it comes across
    // one that matches the parameter piece, it removes it
    public void removePieceFromTeam(Piece piece) {
        for (int i = 0; i < teamPieces.size(); i++) {
            if (teamPieces.get(i) == piece) {
                teamRemovedPieces.add(piece);
                teamPieces.remove(piece);
            }
        }
    }
    // addPieceToTeam adds piece to the teamPieces array
    public void addPieceToTeam(Piece piece) {
        teamPieces.add(piece);
        piece.teamColor = this.teamColor;
    }

    // toString
    @Override
    public String toString() {
        String output = "Team " + this.teamColor + " Pieces :\n";
        for (int i = 0; i < teamPieces.size(); i++) {
            output = output + teamPieces.get(i) + " ";
        }
        return output;

    }


}
