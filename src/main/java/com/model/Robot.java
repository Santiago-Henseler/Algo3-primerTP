package com.model;

import com.model.ControladorLogico.PERSONAJE;

public abstract class Robot extends EntidadBase {

    static public final double TANGENTE_22_5 = 0.41;
    static public final double TANGENTE_67_5 = 2.41;

    Robot( Vector2D posicion){super(posicion);}

    public void setPosicion( Vector2D posicion){super.setPosicion(posicion);}

    // Pre: Posicion de jugador valida
    // Post: Movimiento de robot
    public abstract void movimiento(Vector2D posicion_jugador);

    abstract public PERSONAJE tipo();

    /*
    Pre: EntidadBase valida
    Post: Verdadero si robot muere en colisión.
     */
    public boolean colision( EntidadBase entidad ){
        if ( entidad instanceof Jugador )
            return false;
        else
            return super.getPosicion().esIgual( entidad.getPosicion() ) ;
    }

}
