package fr.insa.eymin.classes;

import java.lang.Math;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import lombok.Data;

@Data
public class Mur {
    private int idMur;
    private int idPieceMur;
    private Coin coinDebut;
    private Coin coinFin;
    private int nbPortes;
    private int nbFenetres;
    private Revetement[] listeRevetements;
    private Mur[] murs = new Mur[100];

    public static void afficher(Mur[] murs, Pane plan) {
        for (int i = 0; i < murs.length; i++) {
            if (murs[i] != null) {
                Line mur = new Line(murs[i].getCoinDebut().getCx() * 50 + 10, murs[i].getCoinDebut().getCy() * 50 + 10,
                        murs[i].getCoinFin().getCx() * 50 + 10, murs[i].getCoinFin().getCy() * 50 + 10);
                plan.getChildren().add(mur);
            }
        }

    }

    public String toString() {
        String listeR = "";
        for (int i = 0; i < listeRevetements.length; i++) {
            if (listeRevetements[i] != null) {
                listeR += listeRevetements[i].getIdRevetement() + ",";
            }
        }
        if (listeR.endsWith(",")) {
            listeR = listeR.substring(0, listeR.length() - 1);
        }
        return idMur + ";" + coinDebut.getIdCoin() + ";" + coinFin.getIdCoin() + ";" + nbPortes + ";" + nbFenetres
                + ";(" + listeR
                + ")";
    }

    public static double surface(int idMur, Niveau[] niveaux, Appartement[] appartements, Piece[] pieces, Mur[] murs,
            int nbPortes, int nbFenetres) {
        int idPieceMur = murs[idMur].idPieceMur;
        Coin coinDebut = murs[idMur].coinDebut;
        Coin coinFin = murs[idMur].coinFin;
        double hauteur = niveaux[appartements[pieces[idPieceMur].getIdAppartementPiece()].getIdNiveauAppartement()]
                .getHauteurSousPlafond();
        double longueur = Math.sqrt(
                Math.pow(coinFin.getCx() - coinDebut.getCx(), 2) + Math.pow(coinFin.getCy() - coinDebut.getCy(), 2));
        double surface = hauteur * longueur;
        surface -= nbPortes * (0.90 * 2.10) + nbFenetres * (1.20 * 1.20);
        return surface;
    }

    public static double[] surfaceTotaleRevetement(Mur[] murs, Niveau[] niveaux,
            Appartement[] appartements, Piece[] pieces, int nbRevetements) {
        double[] surfaceTotaleRevetement = new double[nbRevetements];
        for (int i = 0; i < murs.length; i++) {
            if (murs[i] != null) {
                Mur murCalcule = murs[i];
                Revetement[] listeRevetement = murCalcule.listeRevetements;
                double surface = Mur.surface(murCalcule.idMur, niveaux, appartements, pieces, murs, murCalcule.nbPortes,
                        murCalcule.nbFenetres);
                for (int j = 0; j < listeRevetement.length; j++) {
                    if (listeRevetement[j] != null) {
                        surfaceTotaleRevetement[j] += surface;
                    }
                }
            }
        }

        return surfaceTotaleRevetement;
    }

    public static double montantRevetement(int idMur, Mur[] murs, Niveau[] niveaux,
            Appartement[] appartements, Piece[] pieces, int nbRevetements) {
        Mur murCalcule = murs[idMur];
        Revetement[] listeRevetement = murCalcule.listeRevetements;
        double montantRevetement = 0;
        double[] surfaceTotaleRevetement = new double[nbRevetements];
        double surface = Mur.surface(idMur, niveaux, appartements, pieces, murs, murCalcule.nbPortes,
                murCalcule.nbFenetres);
        for (int i = 0; i < listeRevetement.length; i++) {
            if (listeRevetement[i] != null) {
                montantRevetement += listeRevetement[i].getPrixUnitaire();
                surfaceTotaleRevetement[i] += surface;
            }
        }
        montantRevetement *= surface;

        return montantRevetement;
    }

    // --------------------------------------------------------------------------------
    // Fonction pour creer les murs

    public static Mur[] creationMurs(Coin[] coins, Revetement[] revetements, int nbRevetements) {
        System.out.println("Creation des murs");
        System.out.println(
                "Veuillez entrer les coordonnees des murs du batiment avec le format suivant : idMur;idcoinDebut;idCoinFin;nbrePortes;nbreFenetres;idRevetement(idRevetement1,idRevetement2,etc.). Entrez 'fin' pour terminer.");
        String entree = Lire.S();
        Mur[] murs = new Mur[100];
        while (!entree.equals("fin")) {
            int idMur = Integer.parseInt(entree.split(";")[0]);
            int idCoinDebut = Integer.parseInt(entree.split(";")[1]);
            int idCoinFin = Integer.parseInt(entree.split(";")[2]);
            int nbrePortes = Integer.parseInt(entree.split(";")[3]);
            int nbreFenetres = Integer.parseInt(entree.split(";")[4]);
            Revetement[] listeRevetement = new Revetement[nbRevetements + 1];
            String liste = entree.substring(entree.indexOf("(") + 1, entree.indexOf(")"));
            String[] listeRevetementString = liste.split(",");
            for (int i = 0; i < listeRevetementString.length; i++) {
                listeRevetement[Integer.parseInt(listeRevetementString[i])] = revetements[Integer
                        .parseInt(listeRevetementString[i])];
            }
            Mur mur = new Mur(idMur, coins[idCoinDebut], coins[idCoinFin], nbrePortes, nbreFenetres,
                    listeRevetement);
            murs[idMur] = mur;
            entree = Lire.S();

        }
        for (int i = 0; i < murs.length; i++) {
            if (murs[i] != null) {
                System.out.println(murs[i]);
            }
        }
        System.out.println("");
        return murs;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Mur(int idMur, Coin coinDebut, Coin coinFin, int nbPortes, int nbFenetres, Revetement[] listeRevetements) {
        this.idMur = idMur;
        this.coinDebut = coinDebut;
        this.coinFin = coinFin;
        this.nbPortes = nbPortes;
        this.nbFenetres = nbFenetres;
        this.listeRevetements = listeRevetements;
    }
}
