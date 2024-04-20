package com.model;

import com.Controller.Controlador;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){
        super(posicion);
    }

    public void movimiento(vector2D vec) { return; }

    /*
    Pre: EntidadBase valida
    Post: Verdadero si fuego muere en colision
     */
    public boolean colision( EntidadBase entidad ){
        return false;
    }

    public int tipo(){
        return Controlador.CODIGO_FUEGO;
    }

}
