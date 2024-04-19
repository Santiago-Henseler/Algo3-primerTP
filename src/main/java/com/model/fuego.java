package com.model;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){
        super(posicion);
    }

    public void movimiento(jugador jugador) { ; }

    public boolean colision( EntidadBase entidad ){
        return false;   // El fuego es inmortal !!!
    }

}
