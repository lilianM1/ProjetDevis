package fr.insa.eymin.classes;

import lombok.Data;

@Data
public class Porte extends Ouverture {
    public Porte(int idOuverture, double dimX, double dimY) {
        super(idOuverture, dimX, dimY);
    }
}
