package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Coin {
    private int idCoin;
    private double cx;
    private double cy;

    public void afficher() {
    }

    public String toString() {
        return idCoin + ";" + cx + ";" + cy;
    }

    // --------------------------------------------------------------------------------
    // Fonction pour creer les coins

    public static Coin[] creationCoins() {
        System.out.println("Creation du batiment");
        System.out.println("Creation des coins");
        System.out.println(
                "Veuillez entrer les coordonnees des coins du batiment avec le format suivant : idCoin;cx;cy. Entrez 'fin' pour terminer.");

        String entree = Lire.S();
        Coin[] coins = new Coin[100];
        while (!entree.equals("fin")) {
            int idCoin = Integer.parseInt(entree.split(";")[0]);
            double cx = Double.parseDouble(entree.split(";")[1]);
            double cy = Double.parseDouble(entree.split(";")[2]);
            Coin coin = new Coin(idCoin, cx, cy);
            coins[idCoin] = coin;
            entree = Lire.S();
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] != null) {
                System.out.println(coins[i]);
            }
        }
        System.out.println("");
        return coins;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Coin(int idCoin, double cx, double cy) {
        this.idCoin = idCoin;
        this.cx = cx;
        this.cy = cy;
    }

}
