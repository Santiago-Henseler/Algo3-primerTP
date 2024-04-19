package com.model;

public class fuego extends EntidadBase {
    fuego( vector2D posicion ){
        super(posicion);
    }

    public void movimiento(vector2D vec) { return; }

    public boolean colision( EntidadBase entidad ){
        return false;
    }

}
