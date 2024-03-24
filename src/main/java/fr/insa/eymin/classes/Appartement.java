package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Appartement {
    private int idAppartement;
    private int idNiveauAppartement;
    private Piece[] listePieces;

    public void afficher() {
    }

    public String toString() {
        String listeP = "";
        for (int i = 0; i < listePieces.length; i++) {
            if (listePieces[i] != null) {
                listeP += listePieces[i].getIdPiece() + ",";
            }
        }
        if (listeP.endsWith(",")) {
            listeP = listeP.substring(0, listeP.length() - 1);
        }
        return idAppartement + ";" + idNiveauAppartement + ";(" + listeP + ")";
    }

    public double surface() {
        return 0;
    }

    public double montantRevetement() {
        return 0;
    }

    // --------------------------------------------------------------------------------
    // Fonction pour creer les appartements

    public static Appartement[] creationAppartements(Piece[] pieces) {
        System.out.println("Creation des appartements");
        System.out.println(
                "Veuillez entrer les coordonnees des apartements du batiment avec le format suivant : idAppartement;idNiveauAppartement;idPiece(idPiece1,idPiece2,etc.). Entrez 'fin' pour terminer.");
        String entree = Lire.S();
        Appartement[] appartements = new Appartement[100];
        while (!entree.equals("fin")) {
            int idAppartement = Integer.parseInt(entree.split(";")[0]);
            int idNiveauAppartement = Integer.parseInt(entree.split(";")[1]);
            Piece[] listePiece = new Piece[100];
            String liste = entree.substring(entree.indexOf("(") + 1, entree.indexOf(")"));
            String[] listePieceString = liste.split(",");
            for (int i = 0; i < listePieceString.length; i++) {
                pieces[Integer.parseInt(listePieceString[i])].setIdAppartementPiece(idAppartement);
                listePiece[Integer.parseInt(listePieceString[i])] = pieces[Integer
                        .parseInt(listePieceString[i])];
            }
            Appartement appartement = new Appartement(idAppartement, idNiveauAppartement, listePiece);
            appartements[idAppartement] = appartement;
            entree = Lire.S();

        }
        for (int i = 0; i < appartements.length; i++) {
            if (appartements[i] != null) {
                System.out.println(appartements[i]);
            }
        }
        System.out.println("");
        return appartements;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Appartement(int idAppartement, int idNiveauAppartement, Piece[] listePieces) {
        this.idAppartement = idAppartement;
        this.idNiveauAppartement = idNiveauAppartement;
        this.listePieces = listePieces;
    }
}
