package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Plafond {
    private int idPlafond;
    private Coin[] listeCoins;
    private Revetement[] listeRevetements;

    public String toString() {
        String listeC = "";
        for (int i = 0; i < listeCoins.length; i++) {
            if (listeCoins[i] != null) {
                listeC += listeCoins[i].getIdCoin() + ",";
            }
        }
        if (listeC.endsWith(",")) {
            listeC = listeC.substring(0, listeC.length() - 1);
        }
        String listeR = "";
        for (int i = 0; i < listeRevetements.length; i++) {
            if (listeRevetements[i] != null) {
                listeR += listeRevetements[i].getIdRevetement() + ",";
            }
        }
        if (listeR.endsWith(",")) {
            listeR = listeR.substring(0, listeR.length() - 1);
        }
        return idPlafond + ";(" + listeC + ");(" + listeR + ")";
    }

    public static double surface(int idPlafond, Plafond[] plafonds) {
        int n = 0;
        double X[] = new double[100];
        double Y[] = new double[100];
        Coin[] listeDesCoins = plafonds[idPlafond].listeCoins;
        int a = 0;
        double refX = 0;
        double refY = 0;
        int nbCoins = 0;
        for (Coin coin : listeDesCoins) {
            if (coin != null) {
                refX += coin.getCx();
                refY += coin.getCy();
                nbCoins++;
            }
        }
        refX /= nbCoins;
        refY /= nbCoins;

        for (int i = 0; i < listeDesCoins.length - 1; i++) {
            for (int j = 0; j < listeDesCoins.length - i - 1; j++) {
                if (listeDesCoins[j] != null && listeDesCoins[j + 1] != null) {
                    double angle1 = Math.atan2(listeDesCoins[j].getCy() - refY, listeDesCoins[j].getCx() - refX);
                    double angle2 = Math.atan2(listeDesCoins[j + 1].getCy() - refY,
                            listeDesCoins[j + 1].getCx() - refX);
                    if (angle1 > angle2) {
                        Coin temp = listeDesCoins[j];
                        listeDesCoins[j] = listeDesCoins[j + 1];
                        listeDesCoins[j + 1] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < listeDesCoins.length; i++) {
            if (listeDesCoins[i] != null) {
                X[a] = listeDesCoins[i].getCx();
                Y[a] = listeDesCoins[i].getCy();
                n++;
                a++;
            }
        }
        double surface = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            surface += (X[j] + X[i]) * (Y[j] - Y[i]);

            j = i;
        }

        return Math.abs(surface / 2.0);
    }

    public static double montantRevetement(int idPlafond, Plafond[] plafonds, int nbRevetements) {
        Plafond plafondCalcule = plafonds[idPlafond];
        Revetement[] listeRevetement = plafondCalcule.listeRevetements;
        double montantRevetement = 0;
        double surface = Plafond.surface(idPlafond, plafonds);
        for (int i = 0; i < listeRevetement.length; i++) {
            if (listeRevetement[i] != null) {
                montantRevetement += listeRevetement[i].getPrixUnitaire();
            }
        }
        montantRevetement *= surface;

        return montantRevetement;
    }

    public static double[] surfaceTotaleRevetement(Plafond[] plafonds, int nbRevetements) {
        double[] surfaceTotaleRevetement = new double[nbRevetements];
        for (int i = 0; i < plafonds.length; i++) {
            if (plafonds[i] != null) {
                Plafond plafondCalcule = plafonds[i];
                Revetement[] listeRevetement = plafondCalcule.listeRevetements;
                double surface = Plafond.surface(i, plafonds);
                for (int j = 0; j < listeRevetement.length; j++) {
                    if (listeRevetement[j] != null) {
                        surfaceTotaleRevetement[j] += surface;
                    }
                }
            }
        }

        return surfaceTotaleRevetement;
    }
    // --------------------------------------------------------------------------------
    // Fonction pour creer les plafonds

    public static Plafond[] creationPlafonds(Coin[] coins, Revetement[] revetements, int nbRevetements) {
        System.out.println("Creation des plafonds");
        System.out.println(
                "Veuillez entrer les coordonnees des plafonds du batiment avec le format suivant : idPlafond;idCoin(idCoin1,idCoin2,etc.);idRevetements(idRevetement1,idRevetement2,etc.). Entrez 'fin' pour terminer.");
        String entree = Lire.S();
        Plafond[] plafonds = new Plafond[100];
        while (!entree.equals("fin")) {
            int idPlafond = Integer.parseInt(entree.split(";")[0]);
            Coin[] listeCoin = new Coin[100];
            String liste = entree.split(";")[1];
            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
            String[] listeCoinString = liste.split(",");
            for (int i = 0; i < listeCoinString.length; i++) {
                listeCoin[Integer.parseInt(listeCoinString[i])] = coins[Integer
                        .parseInt(listeCoinString[i])];
            }
            Revetement[] listeRevetements = new Revetement[nbRevetements + 1];
            liste = entree.split(";")[2];
            liste = liste.substring(liste.indexOf("(") + 1, liste.indexOf(")"));
            String[] listeRevetementString = liste.split(",");
            for (int i = 0; i < listeRevetementString.length; i++) {
                listeRevetements[Integer.parseInt(listeRevetementString[i])] = revetements[Integer
                        .parseInt(listeRevetementString[i])];
            }
            Plafond plafond = new Plafond(idPlafond, listeCoin, listeRevetements);
            plafonds[idPlafond] = plafond;
            entree = Lire.S();

        }
        for (int i = 0; i < plafonds.length; i++) {
            if (plafonds[i] != null) {
                System.out.println(plafonds[i]);
            }
        }
        System.out.println("");
        return plafonds;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Plafond(int idPlafond, Coin[] listeCoins, Revetement[] listeRevetements) {
        this.idPlafond = idPlafond;
        this.listeCoins = listeCoins;
        this.listeRevetements = listeRevetements;
    }
}
