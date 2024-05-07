package com.model;

import com.model.ControladorLogico.PERSONAJE;

public class Fuego extends EntidadBase {
    Fuego( Vector2D posicion ){super(posicion);}

    public void movimiento(Vector2D vec) {  }

    /*
    Pre: EntidadBase valida
    Post: Verdadero si fuego muere en colision
     */
    public boolean colision( EntidadBase entidad ){return false;}

    public PERSONAJE tipo(){return ControladorLogico.PERSONAJE.FUEGO;}
}
