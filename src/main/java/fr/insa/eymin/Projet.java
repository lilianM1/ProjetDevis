package fr.insa.eymin;
/*
 * Projet.java
 * Creation d'un devis pour les revetements d'un batiment
 * Auteurs: Lilian EYMIN, Simon FONTAINE
 * Fevrier 2024
 * https://github.com/lilianM1/Projet_InfoM2
 * github : lilianM1, sfontaine01
 */

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.PrintWriter;
// import java.util.Scanner;

import fr.insa.eymin.classes.*;
import lombok.Data;

@Data
public class Projet {

    public static Object[] main() {
        System.out.println(System.getProperty("user.dir"));
        // -----------------------------------------------------------------------------------------
        // comptage du nombre de revetements (on pourra ajouter des revetements qui
        // seront pris en compte dans le fichier revetements.txt)
        // Et stockage des revetements dans un tableau

        int nbRevetements = Revetement.comptageRevetements();
        Revetement[] revetements = Revetement.stockagRevetements(nbRevetements);
        // -----------------------------------------------------------------------------------------
        /*
         * Creation du batiment
         * On commence par creer les coins puis les murs puis sols, plafonds, pieces,
         * appartement, niveau et enfin maison/immeuble
         * On procede dans cet ordre car plus on augmente l'echelle chaque element a
         * besoin des elements precedents pour etre cree (ie chaque element plus grand
         * contient les autres)
         * Chaque fonction de creation d'element est dans la classe de cet element et
         * permet de creer un tableau de cet element par appel du constructeur avec en
         * parametres l'entree de l'utilisateur de
         * chacun de ses parametres
         */
        System.out.println("Voulez vous creer un nouveau batiment ? [y/n]");
        char reponse = ' ';
        while (reponse != 'n' && reponse != 'y') {
            reponse = Lire.c();
            if (reponse == 'y') {
                reponse = ' ';
                Coin[] coins = Coin.creationCoins();
                Mur[] murs = Mur.creationMurs(coins, revetements, nbRevetements);
                Sol[] sols = Sol.creationSols(coins, revetements, nbRevetements);
                Plafond[] plafonds = Plafond.creationPlafonds(coins, revetements, nbRevetements);
                Piece[] pieces = Piece.creationPieces(murs);
                Appartement[] appartements = Appartement.creationAppartements(pieces);
                Niveau[] niveaux = Niveau.creationNiveaux(appartements);
                Batiment[] batiments = Batiment.creationBatiment(niveaux);
                Batiment.devisBatiment(murs, sols, plafonds, revetements, niveaux, appartements, pieces,
                        nbRevetements);

                // -----------------------------------------------------------------------------------------
                // Sauvegarde du batiment dans un fichier texte

                System.out.println("Voulez vous sauvegarder le batiment dans un fichier texte ? [y/n]");
                reponse = Lire.c();
                if (reponse == 'y') {
                    reponse = ' ';
                    Batiment.sauvegarder(coins, murs, sols, plafonds, pieces, appartements, niveaux, batiments);
                    return null;

                } else if (reponse == 'n') {
                    reponse = ' ';
                    System.out.println("Fin du programme");
                    return null;
                } else if (reponse != 'n' && reponse != 'y') {
                    System.out.println("Erreur de saisie");
                    System.out.println("Veuillez repondre par 'y' ou 'n'");
                    System.out.println("Voulez vous sauvegarder le batiment dans un fichier texte ? [y/n]");
                }

                // -----------------------------------------------------------------------------------------
                // Si l'utilisateur ne veut pas creer un nouveau batiment, on lui propose de
                // charger un batiment deja existant a partir d'un fichier texte

            } else if (reponse == 'n') {
                reponse = ' ';
                System.out.println("Voulez vous charger un batiment depuis un fichier texte ? [y/n]");
                while (reponse != 'n' && reponse != 'y') {
                    reponse = Lire.c();
                    if (reponse == 'y') {
                        reponse = ' ';
                        Object[] batimentTab = Batiment.lireBatiment(nbRevetements, revetements);
                        // Coin[] coins = (Coin[]) batimentTab[0];
                        Mur[] murs = (Mur[]) batimentTab[1];
                        Sol[] sols = (Sol[]) batimentTab[2];
                        Plafond[] plafonds = (Plafond[]) batimentTab[3];
                        Piece[] pieces = (Piece[]) batimentTab[4];
                        Appartement[] appartements = (Appartement[]) batimentTab[5];
                        Niveau[] niveaux = (Niveau[]) batimentTab[6];
                        // Batiment[] batiments = (Batiment[]) batimentTab[7];

                        // -----------------------------------------------------------------------------------------
                        Batiment.devisBatiment(murs, sols, plafonds, revetements, niveaux, appartements, pieces,
                                nbRevetements);
                        return batimentTab;
                        // -----------------------------------------------------------------------------------------

                    } else if (reponse == 'n') {
                        reponse = ' ';
                        System.out.println("Fin du programme");
                        return null;
                    } else if (reponse != 'n' && reponse != 'y') {
                        System.out.println("Erreur de saisie");
                        System.out.println("Veuillez repondre par 'y' ou 'n'");
                        System.out.println("Voulez vous charger un batiment depuis un fichier texte ? [y/n]");
                    }
                }
            } else if (reponse != 'n' && reponse != 'y') {
                System.out.println("Erreur de saisie");
                System.out.println("Veuillez repondre par 'y' ou 'n'");
                System.out.println("Voulez vous creer un nouveau batiment ? [y/n]");
            }
        }
        return null;
    }

    public static void coins() {

    }
}
