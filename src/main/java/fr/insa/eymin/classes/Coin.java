package fr.insa.eymin.classes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import lombok.Data;

@Data
public class Coin {
    private int idCoin;
    private double cx;
    private double cy;

    public static void afficher(Coin[] coins, Pane plan) {
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] != null) {
                Circle coin = new Circle(coins[i].getCx() * 50 + 10, coins[i].getCy() * 50 + 10, 0);
                plan.getChildren().add(coin);
            }

        }
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
