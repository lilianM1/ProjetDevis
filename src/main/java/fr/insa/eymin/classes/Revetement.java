package fr.insa.eymin.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lombok.Data;

@Data
public class Revetement {
    private int idRevetement;
    private String designation;
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond;
    private double prixUnitaire;
    private static File revetementsFichier = new File(System.getProperty("user.dir") + "\\Donnees\\revetements.txt");

    public String toString() {
        return designation;
    }

    public static int comptageRevetements() {
        try (Scanner scanner = new Scanner(revetementsFichier)) {
            int nbRevetements = 0;
            while (scanner.hasNextLine()) { // Comptage du nombre de revetements
                nbRevetements++;
                scanner.nextLine();
            }
            scanner.close();
            return nbRevetements;

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : revetements.txt non trouve.");
            return 0;
        }
    }

    public static Revetement[] stockagRevetements(int nbRevetements) {
        try (Scanner scanner = new Scanner(revetementsFichier)) {
            Revetement[] revetements = new Revetement[nbRevetements]; // creation d'un tableau de nb de revetements
                                                                      // cases qui stockera des objets revetements
            scanner.nextLine();
            while (scanner.hasNextLine()) { // creation des objets revetements et stockage dans le tableau
                String ligneRevetement = scanner.nextLine();
                int idRevetement = Integer.parseInt(ligneRevetement.split(";")[0]);
                String designation = ligneRevetement.split(";")[1];
                Boolean pourMur = (Integer.parseInt(ligneRevetement.split(";")[2]) == 1);
                Boolean pourSol = (Integer.parseInt(ligneRevetement.split(";")[3]) == 1);
                Boolean pourPlafond = (Integer.parseInt(ligneRevetement.split(";")[4]) == 1);
                double prixUnitaire = Double.parseDouble(ligneRevetement.split(";")[5]);
                Revetement revetement = new Revetement(idRevetement, designation, pourMur, pourSol, pourPlafond,
                        prixUnitaire); // utilisation du constructeur de la classe Revetement avec les parametres
                                       // definis precedaments en decoupant la ligne a chaque semicolon
                revetements[idRevetement] = revetement;
                // si on veut acceder a un revetement on accede a la case du tableau
                // correspondant a l'id du revetement (la case 0 est donc vide comme les id
                // commencent a 1)
            }
            scanner.close();
            return revetements;
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : revetements.txt non trouve.");
            return null;
        }
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Revetement(int idRevetement, String designation, boolean pourMur, boolean pourSol, boolean pourPlafond,
            double prixUnitaire) {
        this.idRevetement = idRevetement;
        this.designation = designation;
        this.pourMur = pourMur;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.prixUnitaire = prixUnitaire;
    }
}
