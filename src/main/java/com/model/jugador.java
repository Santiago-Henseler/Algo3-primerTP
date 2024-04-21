package com.model;

import com.Controller.Controlador;

public class jugador extends EntidadBase {

    private int safeTp;
    private Boolean vida;

    public jugador(vector2D posicion){
        super(posicion);
        this.vida = true;
    }

    public Boolean tieneSafeTp(){return safeTp >=1;}

    public void movimiento(vector2D vec) {this.getPosicion().setVec(vec); }

    public Boolean getVida(){return this.vida;};

    public void setVida(Boolean vida){this.vida = vida;};

    public void setSafeTp(int cant){this.safeTp = cant;};

    public int tipo(){
        return Controlador.CODIGO_JUGADOR;
    }

    public void rmSafeTp(){this.safeTp--;}

    public vector2D tp(vector2D rango){
        int x = (int) Math.floor(Math.random()*(rango.getX()-2));
        int y = (int) Math.floor(Math.random()*(rango.getY()-2));
        vector2D nuevaPosicion = new vector2D(x, y);
    
        this.getPosicion().setVec(nuevaPosicion);

        return nuevaPosicion;
    }

    public boolean colision( EntidadBase entidad ){
        // Se exceptua el choque jugador con jugador, es un juego en solitario
        return super.getPosicion().esIgual( entidad.getPosicion() ) ;
    }

}
