package fr.insa.eymin.classes;

import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import lombok.Data;

@Data
public class Batiment {
    private int idBatiment;
    private Niveau[] listeNiveaux;

    public static void afficher(Coin[] coins, Mur[] murs, Pane plan) {
        Coin.afficher(coins, plan);
        Mur.afficher(murs, plan);
    }

    public static void sauvegarder(Coin[] coins, Mur[] murs, Sol[] sols, Plafond[] plafonds, Piece[] pieces,
            Appartement[] appartements, Niveau[] niveaux, Batiment[] batiments) {
        try (PrintWriter writer = new PrintWriter(
                new File(System.getProperty("user.dir") + "\\Donnees\\SauvegardeBatiment.txt"))) {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] != null) {
                    writer.println("Coin;" + coins[i].toString());
                }
            }
            for (int i = 0; i < murs.length; i++) {
                if (murs[i] != null) {
                    writer.println("Mur;" + murs[i].toString());
                }
            }
            for (int i = 0; i < sols.length; i++) {
                if (sols[i] != null) {
                    writer.println("Sol;" + sols[i].toString());
                }
            }
            for (int i = 0; i < plafonds.length; i++) {
                if (plafonds[i] != null) {
                    writer.println("Plafond;" + plafonds[i].toString());
                }
            }
            for (int i = 0; i < pieces.length; i++) {
                if (pieces[i] != null) {
                    writer.println("Piece;" + pieces[i].toString());
                }
            }
            for (int i = 0; i < appartements.length; i++) {
                if (appartements[i] != null) {
                    writer.println("Appartement;" + appartements[i].toString());
                }
            }
            for (int i = 0; i < niveaux.length; i++) {
                if (niveaux[i] != null) {
                    writer.println("Niveau;" + niveaux[i].toString());
                }
            }
            for (int i = 0; i < batiments.length; i++) {
                if (batiments[i] != null) {
                    writer.println("Batiment;" + batiments[i].toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur s'est produite lors de la tentative d'écriture du fichier.");
            e.printStackTrace();
        }
    }

    public static Object[] lireBatiment(int nbRevetements, Revetement[] revetements) {
        // -----------------------------------------------------------------------------------------
        // Chargement du batiment depuis un fichier texte
        System.out.println("Entrez le nom du fichier texte");
        String nomFichier = Lire.S();
        Coin[] coins = new Coin[100];
        Mur[] murs = new Mur[100];
        Sol[] sols = new Sol[100];
        Plafond[] plafonds = new Plafond[100];
        Piece[] pieces = new Piece[100];
        Appartement[] appartements = new Appartement[100];
        Niveau[] niveaux = new Niveau[100];
        Batiment[] batiments = new Batiment[100];
        try (Scanner scanner = new Scanner(
                new File(System.getProperty("user.dir") + "\\Donnees\\" + nomFichier + ".txt"))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (!ligne.trim().isEmpty()) {
                    switch (ligne.split(";")[0]) {
                        case "Coin":
                            int idCoin = Integer.parseInt(ligne.split(";")[1]);
                            double cx = Double.parseDouble(ligne.split(";")[2]);
                            double cy = Double.parseDouble(ligne.split(";")[3]);
                            Coin coin = new Coin(idCoin, cx, cy);
                            coins[idCoin] = coin;
                            break;
                        case "Mur":
                            int idMur = Integer.parseInt(ligne.split(";")[1]);
                            int idCoinDebut = Integer.parseInt(ligne.split(";")[2]);
                            int idCoinFin = Integer.parseInt(ligne.split(";")[3]);
                            int nbrePortes = Integer.parseInt(ligne.split(";")[4]);
                            int nbreFenetres = Integer.parseInt(ligne.split(";")[5]);
                            Revetement[] listeRevetement = new Revetement[nbRevetements + 1];
                            String liste = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
                            String[] listeRevetementString = liste.split(",");
                            for (int i = 0; i < listeRevetementString.length; i++) {
                                if (revetements[Integer.parseInt(listeRevetementString[i])].isPourMur() == false) {
                                    System.out.println("Le revetement n°" + listeRevetementString[i]
                                            + " n'est pas adapté pour le mur n°" + idMur + ".");
                                    System.out.println(
                                            "Veuillez verifier votre fichier et entrer un revetement adapté pour un mur.");
                                    System.exit(0);
                                }

                                listeRevetement[Integer
                                        .parseInt(listeRevetementString[i])] = revetements[Integer
                                                .parseInt(listeRevetementString[i])];
                            }
                            Mur mur = new Mur(idMur, coins[idCoinDebut], coins[idCoinFin], nbrePortes,
                                    nbreFenetres,
                                    listeRevetement);
                            murs[idMur] = mur;
                            break;
                        case "Sol":
                            int idSol = Integer.parseInt(ligne.split(";")[1]);
                            Coin[] listeCoin = new Coin[100];
                            liste = ligne.split(";")[2];
                            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
                            String[] listeCoinString = liste.split(",");
                            for (int i = 0; i < listeCoinString.length; i++) {
                                listeCoin[Integer.parseInt(listeCoinString[i])] = coins[Integer
                                        .parseInt(listeCoinString[i])];
                            }
                            Revetement[] listeRevetements = new Revetement[nbRevetements + 1];
                            liste = ligne.split(";")[3];
                            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
                            listeRevetementString = liste.split(",");
                            for (int i = 0; i < listeRevetementString.length; i++) {
                                if (revetements[Integer.parseInt(listeRevetementString[i])].isPourSol() == false) {
                                    System.out.println("Le revetement n°" + listeRevetementString[i]
                                            + " n'est pas adapté pour le sol n°" + idSol + ".");
                                    System.out.println(
                                            "Veuillez verifier votre fichier et entrer un revetement adapté pour un sol.");
                                    System.exit(0);
                                }
                                listeRevetements[Integer
                                        .parseInt(listeRevetementString[i])] = revetements[Integer
                                                .parseInt(listeRevetementString[i])];
                            }
                            Sol sol = new Sol(idSol, listeCoin, listeRevetements);
                            sols[idSol] = sol;
                            break;
                        case "Plafond":
                            int idPlafond = Integer.parseInt(ligne.split(";")[1]);
                            listeCoin = new Coin[100];
                            liste = ligne.split(";")[2];
                            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
                            listeCoinString = liste.split(",");
                            for (int i = 0; i < listeCoinString.length; i++) {
                                listeCoin[Integer.parseInt(listeCoinString[i])] = coins[Integer
                                        .parseInt(listeCoinString[i])];
                            }
                            listeRevetements = new Revetement[nbRevetements + 1];
                            liste = ligne.split(";")[3];
                            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
                            listeRevetementString = liste.split(",");
                            for (int i = 0; i < listeRevetementString.length; i++) {
                                if (revetements[Integer.parseInt(listeRevetementString[i])].isPourPlafond() == false) {
                                    System.out.println("Le revetement n°" + listeRevetementString[i]
                                            + " n'est pas adapté pour le plafond n°" + idPlafond + ".");
                                    System.out.println(
                                            "Veuillez verifier votre fichier et entrer un revetement adapté pour un plafond.");
                                    System.exit(0);
                                }
                                listeRevetements[Integer
                                        .parseInt(listeRevetementString[i])] = revetements[Integer
                                                .parseInt(listeRevetementString[i])];
                            }
                            Plafond plafond = new Plafond(idPlafond, listeCoin, listeRevetements);
                            plafonds[idPlafond] = plafond;
                            break;
                        case "Piece":
                            int idPiece = Integer.parseInt(ligne.split(";")[1]);
                            idSol = Integer.parseInt(ligne.split(";")[2]);
                            idPlafond = Integer.parseInt(ligne.split(";")[3]);
                            Mur[] listeMur = new Mur[100];
                            liste = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
                            String[] listeMurString = liste.split(",");
                            for (int i = 0; i < listeMurString.length; i++) {
                                murs[Integer.parseInt(listeMurString[i])].setIdPieceMur(idPiece);
                                listeMur[Integer.parseInt(listeMurString[i])] = murs[Integer
                                        .parseInt(listeMurString[i])];
                            }
                            Piece piece = new Piece(idPiece, idSol, idPlafond, listeMur);
                            pieces[idPiece] = piece;
                            break;
                        case "Appartement":
                            int idAppartement = Integer.parseInt(ligne.split(";")[1]);
                            int idNiveauAppartement = Integer.parseInt(ligne.split(";")[2]);
                            Piece[] listePiece = new Piece[100];
                            liste = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
                            String[] listePieceString = liste.split(",");
                            for (int i = 0; i < listePieceString.length; i++) {
                                pieces[Integer.parseInt(listePieceString[i])].setIdAppartementPiece(idAppartement);
                                listePiece[Integer.parseInt(listePieceString[i])] = pieces[Integer
                                        .parseInt(listePieceString[i])];
                            }
                            Appartement appartement = new Appartement(idAppartement, idNiveauAppartement,
                                    listePiece);
                            appartements[idAppartement] = appartement;
                            break;
                        case "Niveau":
                            int idNiveau = Integer.parseInt(ligne.split(";")[1]);
                            double hauteurSousPlafond = Double.parseDouble(ligne.split(";")[2]);
                            Appartement[] listeAppartement = new Appartement[100];
                            liste = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
                            String[] listeAppartementString = liste.split(",");
                            for (int i = 0; i < listeAppartementString.length; i++) {
                                listeAppartement[Integer
                                        .parseInt(listeAppartementString[i])] = appartements[Integer
                                                .parseInt(listeAppartementString[i])];
                            }
                            Niveau niveau = new Niveau(idNiveau, hauteurSousPlafond, listeAppartement);
                            niveaux[idNiveau] = niveau;
                            break;
                        case "Batiment":
                            int idBatiment = Integer.parseInt(ligne.split(";")[1]);
                            Niveau[] listeNiveau = new Niveau[100];
                            liste = ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"));
                            String[] listeNiveauString = liste.split(",");
                            for (int i = 0; i < listeNiveauString.length; i++) {
                                listeNiveau[Integer.parseInt(listeNiveauString[i])] = niveaux[Integer
                                        .parseInt(listeNiveauString[i])];
                            }
                            Batiment batiment = new Batiment(idBatiment, listeNiveau);
                            batiments[idBatiment] = batiment;
                            break;
                        default:
                            System.out.println("Erreur de lecture du fichier");
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur s'est produite lors de la tentative de lecture du fichier.");
        }
        Object[] batimentTab = new Object[8];
        batimentTab[0] = coins;
        batimentTab[1] = murs;
        batimentTab[2] = sols;
        batimentTab[3] = plafonds;
        batimentTab[4] = pieces;
        batimentTab[5] = appartements;
        batimentTab[6] = niveaux;
        batimentTab[7] = batiments;
        return batimentTab;

        // -----------------------------------------------------------------------------------------
    }

    public static String devisBatiment(Mur[] murs, Sol[] sols, Plafond[] plafonds, Revetement[] revetements,
            Niveau[] niveaux, Appartement[] appartements, Piece[] pieces, int nbRevetements) {
        String devis = "";
        try (PrintWriter writer = new PrintWriter(new File(System.getProperty("user.dir") + "\\Donnees\\Devis.txt"))) {
            float prixTotal = 0;
            double[] surfaceTotaleRevetementMur = Mur.surfaceTotaleRevetement(murs, niveaux, appartements, pieces,
                    nbRevetements);
            double[] surfaceTotaleRevetementSol = Sol.surfaceTotaleRevetement(sols, nbRevetements);
            double[] surfaceTotaleRevetementPlafond = Plafond.surfaceTotaleRevetement(plafonds, nbRevetements);
            double[] surfaceTotaleRevetement = new double[nbRevetements];
            double[] prixParRevetement = new double[nbRevetements];
            for (int i = 0; i < nbRevetements; i++) {
                surfaceTotaleRevetement[i] = surfaceTotaleRevetementMur[i] + surfaceTotaleRevetementSol[i]
                        + surfaceTotaleRevetementPlafond[i];
            }
            for (int i = 0; i < nbRevetements; i++) {
                if (surfaceTotaleRevetement[i] != 0) {
                    prixParRevetement[i] = surfaceTotaleRevetement[i] * revetements[i].getPrixUnitaire();
                    prixTotal += prixParRevetement[i];
                    writer.println("Le revetement n°" + i + " (" + revetements[i].getDesignation()
                            + ") couvrira une surface totale de "
                            + (float) surfaceTotaleRevetement[i] + " m² pour un prix total de "
                            + (float) prixParRevetement[i]
                            + "€");
                    devis += "Le revetement n°" + i + " (" + revetements[i].getDesignation()
                            + ") couvrira une surface totale de "
                            + (float) surfaceTotaleRevetement[i] + " m² pour un prix total de "
                            + (float) prixParRevetement[i]
                            + "€";
                    devis += "\n";
                }
            }
            writer.println("");
            devis += "\n";
            writer.println("Le prix total du batiment sera de " + prixTotal + "€");
            devis += "Le prix total du batiment sera de " + prixTotal + "€";
            System.out.println("Retrouvez le Devis dans :" + System.getProperty("user.dir") + "\\Donnees\\Devis.txt");
            return devis;
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur s'est produite lors de la tentative d'écriture du devis.");
            e.printStackTrace();
            return null;
        }
    }

    public void dessiner() {
    }

    public String toString() {
        String listeN = "";
        for (int i = 0; i < listeNiveaux.length; i++) {
            if (listeNiveaux[i] != null) {
                listeN += listeNiveaux[i].getIdNiveau() + ",";
            }
        }
        if (listeN.endsWith(",")) {
            listeN = listeN.substring(0, listeN.length() - 1);
        }
        return idBatiment + ";(" + listeN + ")";
    }

    // -----------------------------------------------------------------------------------
    // Fonction pour creer le batiment

    public static Batiment[] creationBatiment(Niveau[] niveaux) {
        System.out.println("Creation du batiment");
        System.out.println(
                "Veuillez entrer les parametres du batiment avec le format suivant : idBatiment;idNiveau(idNiveau1,idNiveau2,etc.). Entrez 'fin' pour terminer.");
        String entree = Lire.S();
        Batiment[] batiments = new Batiment[100];
        int idBatiment = Integer.parseInt(entree.split(";")[0]);
        Niveau[] listeNiveau = new Niveau[100];
        String liste = entree.substring(entree.indexOf("(") + 1, entree.indexOf(")"));
        String[] listeNiveauString = liste.split(",");
        for (int i = 0; i < listeNiveauString.length; i++) {
            listeNiveau[Integer.parseInt(listeNiveauString[i])] = niveaux[Integer
                    .parseInt(listeNiveauString[i])];
        }
        Batiment batiment = new Batiment(idBatiment, listeNiveau);
        batiments[idBatiment] = batiment;
        entree = Lire.S();

        for (int i = 0; i < batiments.length; i++) {
            if (batiments[i] != null) {
                System.out.println(batiments[i]);
            }
        }
        System.out.println("");
        return batiments;
    }

    // -----------------------------------------------------------------------------------
    // Constructeur

    public Batiment(int idBatiment, Niveau[] listeNiveaux) {
        this.idBatiment = idBatiment;
        this.listeNiveaux = listeNiveaux;
    }
}
