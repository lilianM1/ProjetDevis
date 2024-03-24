/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eymin.gui;

import fr.insa.eymin.*;
import fr.insa.eymin.classes.Lire;
import javafx.application.Application;
import static javafx.application.Application.launch;

import java.util.concurrent.CountDownLatch;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author lilou
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Create a label with the text "Hello World"
        // Create a label with the text "Hello World"
      

        // Create a point (small circle) and add it to the root pane
        Circle point = new Circle(50, 50, 1); // 50, 50 is the x,y position of the point and 5 is the radius
        root.getChildren().add(point);
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Plan du bâtiment");
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}
