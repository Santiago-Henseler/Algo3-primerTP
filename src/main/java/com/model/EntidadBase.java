package com.model;

import com.model.ControladorLogico.PERSONAJE;

public abstract class EntidadBase {

    private Vector2D posicion;

    public EntidadBase(Vector2D posicion){
        this.posicion = posicion;
    }

    public Vector2D getPosicion(){return this.posicion;}

    public void setPosicion( Vector2D posicion){ this.posicion = posicion; }

    abstract public PERSONAJE tipo();

    abstract public boolean colision( EntidadBase entidad );

    abstract public void movimiento(Vector2D posicion);
}

