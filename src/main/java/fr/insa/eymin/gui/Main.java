/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eymin.gui;

import fr.insa.eymin.*;
import fr.insa.eymin.classes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 *
 * @author lilou
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane plan = new Pane();
        Object[] batimentTab = Projet.main();
        Coin[] coins = (Coin[]) batimentTab[0];
        Mur[] murs = (Mur[]) batimentTab[1];
        // Sol[] sols = (Sol[]) batimentTab[2];
        // Plafond[] plafonds = (Plafond[]) batimentTab[3];
        // Piece[] pieces = (Piece[]) batimentTab[4];
        // Appartement[] appartements = (Appartement[]) batimentTab[5];
        // Niveau[] niveaux = (Niveau[]) batimentTab[6];
        // Batiment[] batiments = (Batiment[]) batimentTab[7];

        Batiment.afficher(coins, murs, plan);

        double maxX = 0;
        double maxY = 0;

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] != null) {
                Circle coin = new Circle(coins[i].getCx() * 50 + 10, coins[i].getCy() * 50 + 10, 1);
                plan.getChildren().add(coin);
                maxX = Math.max(maxX, coins[i].getCx() * 50 + 10);
                maxY = Math.max(maxY, coins[i].getCy() * 50 + 10);
            }
        }

        Scene scene = new Scene(plan, maxX + 20, maxY + 20);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Plan du bâtiment");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
