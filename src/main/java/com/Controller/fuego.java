package com.Controller;

import com.model.vector2D;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){
        super(posicion);
        System.out.println("Instanciando fuego");
    }

    public void movimiento(jugador jugador) { ; }

    public void colision( EntidadBase entidadBase){ ; }
}
