package com.model;

import com.Controller.Controlador;
import com.Controller.Controlador.PERSONAJE;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){super(posicion);}

    public void movimiento(vector2D vec) {  }

    /*
    Pre: EntidadBase valida
    Post: Verdadero si fuego muere en colision
     */
    public boolean colision( EntidadBase entidad ){return false;}

    public PERSONAJE tipo(){return Controlador.PERSONAJE.FUEGO;}
}
