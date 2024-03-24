package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Ouverture {
    private int idOuverture;
    private double dimX;
    private double dimY;

    public void afficher() {
    }

    public String toString() {
        return idOuverture + ";" + dimX + ";" + dimY;
    }

    public double surface() {
        return 0;
    }

    // --------------------------------------------------------------------------------
    // Constructeur

    public Ouverture(int idOuverture, double dimX, double dimY) {
        this.idOuverture = idOuverture;
        this.dimX = dimX;
        this.dimY = dimY;
    }
}
