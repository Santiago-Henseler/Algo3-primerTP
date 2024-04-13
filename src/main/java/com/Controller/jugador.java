package com.Controller;

import com.model.vector2D;

public class jugador extends EntidadBase{

    private int safeTp;

    public jugador(vector2D posicion){
        super(posicion);
        this.safeTp = 1;
    }

    public Boolean tieneSafeTp(){return safeTp >=1;}

    @Override
    public void movimiento(vector2D vec) {
        this.getPosicion().aniadir(vec);
    }

    public Boolean safeTp(){
        return true;
    }

    public void tp(vector2D rango){
        int x = (int) Math.floor(Math.random()*(rango.getX()+1));
        int y = (int) Math.floor(Math.random()*(rango.getY()+1));
        this.getPosicion().setVec(new vector2D(x, y));
    }

}
