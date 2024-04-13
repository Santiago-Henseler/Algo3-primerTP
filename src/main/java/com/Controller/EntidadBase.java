package com.Controller;

import com.model.vector2D;

public abstract class EntidadBase {

    private final vector2D posicion;
    private boolean vivo;
    
    public EntidadBase(vector2D posicion){
        this.posicion = posicion;
        this.vivo = true;
    }

    public void setVida(Boolean estado){this.vivo = estado;}  

    public Boolean getVida(){return this.vivo;}

    public vector2D getPosicion(){return this.posicion;}

    abstract void movimiento(vector2D vec);

}

