package com.model;

import com.Controller.Controlador.PERSONAJE;

public abstract class EntidadBase {

    private vector2D posicion;

    public EntidadBase(vector2D posicion){
        this.posicion = posicion;
    }

    public vector2D getPosicion(){return this.posicion;}

    public void setPosicion( vector2D posicion){ this.posicion = posicion; }

    abstract public PERSONAJE tipo();

    abstract public boolean colision( EntidadBase entidad );

    abstract public void movimiento(vector2D posicion);
}

