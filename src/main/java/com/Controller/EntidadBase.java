package com.Controller;

import com.model.vector2D;

public abstract class EntidadBase {

    private vector2D posicion;

    public EntidadBase(vector2D posicion){
        this.posicion = posicion;
    }

    public vector2D getPosicion(){return this.posicion;}

    public void setPosicion( vector2D posicion){ this.posicion = posicion; }

    public abstract void movimiento( jugador jugador );

    public abstract void colision( EntidadBase entidad );
}

