package com.model;

import com.model.ControladorLogico.PERSONAJE;

public class Jugador extends EntidadBase {

    private int safeTp;
    private Boolean vida;

    public Jugador(Vector2D posicion){
        super(posicion);
        this.vida = true;
    }

    public Boolean tieneSafeTp(){return safeTp >=1;}

    public void movimiento(Vector2D vec) {this.getPosicion().setVec(vec); }

    public Boolean getVida(){return this.vida;}

    public void setVida(Boolean vida){this.vida = vida;}

    public void setSafeTp(int cant){this.safeTp = cant;}

    public PERSONAJE tipo(){return PERSONAJE.JUGADOR;}

    public void rmSafeTp(){this.safeTp--;}

    public Vector2D tp(Vector2D rango){
        int x = (int) Math.floor(Math.random()*(rango.getX()-2));
        int y = (int) Math.floor(Math.random()*(rango.getY()-2));
        Vector2D nuevaPosicion = new Vector2D(x, y);
    
        this.getPosicion().setVec(nuevaPosicion);

        return nuevaPosicion;
    }

    // Se exceptua el choque jugador con jugador, es un juego en solitario
    public boolean colision( EntidadBase entidad ){return super.getPosicion().esIgual( entidad.getPosicion() );}

}
