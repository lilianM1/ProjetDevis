package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Niveau {
    private int idNiveau;
    private double hauteurSousPlafond;
    private Appartement[] listeApparts;

    public void afficher() {
    }

    public String toString() {
        String listeA = "";
        for (int i = 0; i < listeApparts.length; i++) {
            if (listeApparts[i] != null) {
                listeA += listeApparts[i].getIdAppartement() + ",";
            }
        }
        if (listeA.endsWith(",")) {
            listeA = listeA.substring(0, listeA.length() - 1);
        }
        return idNiveau + ";" + hauteurSousPlafond + ";(" + listeA + ")";
    }

    public double surface() {
        return 0;
    }

    public double montantRevetement() {
        return 0;
    }

    // --------------------------------------------------------------------------------
    // Fonction pour creer les niveaux

    public static Niveau[] creationNiveaux(Appartement[] appartements) {
        System.out.println("Creation des niveaux");
        System.out.println(
                "Veuillez entrer les coordonnees des niveaux du batiment avec le format suivant : idNiveau;hauteurSousPlafond;idAppartement(idAppart1,idAppart2,etc.). Entrez 'fin' pour terminer.");
        String entree = Lire.S();
        Niveau[] niveaux = new Niveau[100];
        while (!entree.equals("fin")) {
            int idNiveau = Integer.parseInt(entree.split(";")[0]);
            double hauteurSousPlafond = Double.parseDouble(entree.split(";")[1]);
            Appartement[] listeAppartement = new Appartement[100];
            String liste = entree.substring(entree.indexOf("(") + 1, entree.indexOf(")"));
            String[] listeAppartementString = liste.split(",");
            for (int i = 0; i < listeAppartementString.length; i++) {
                listeAppartement[Integer.parseInt(listeAppartementString[i])] = appartements[Integer
                        .parseInt(listeAppartementString[i])];
            }
            Niveau niveau = new Niveau(idNiveau, hauteurSousPlafond, listeAppartement);
            niveaux[idNiveau] = niveau;
            entree = Lire.S();

        }
        for (int i = 0; i < niveaux.length; i++) {
            if (niveaux[i] != null) {
                System.out.println(niveaux[i]);
            }
        }
        System.out.println("");
        return niveaux;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Niveau(int idNiveau, double hauteurSousPlafond, Appartement[] listeApparts) {
        this.idNiveau = idNiveau;
        this.hauteurSousPlafond = hauteurSousPlafond;
        this.listeApparts = listeApparts;
    }
}
