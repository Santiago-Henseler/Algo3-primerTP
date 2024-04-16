package com.model;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){
        super(posicion);
        System.out.println("Instanciando fuego");
    }

    public void movimiento(jugador jugador) { ; }

    public boolean colision( EntidadBase entidad ){
        return false;   // El fuego es inmortal !!!
    }

}
