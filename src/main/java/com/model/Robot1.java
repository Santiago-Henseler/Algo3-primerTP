package com.model;

import com.model.ControladorLogico.PERSONAJE;

import java.lang.Math;

public class Robot1 extends Robot {

    private static final int DISTANCIA_AVANCE = 1;

    Robot1( Vector2D posicion ){super(posicion);}

    public boolean colision( EntidadBase entidad ){return super.colision(entidad);}

    public PERSONAJE tipo(){return PERSONAJE.ROBOT1;}

    public void movimiento(Vector2D posicion_jugador){
        Vector2D delta = Vector2D.resta( posicion_jugador, this.getPosicion() );

        int aniadir_x = 0; int aniadir_y = 0;
        if ( delta.getX() == 0 ){
            aniadir_y = ( delta.getY() > 0) ? DISTANCIA_AVANCE : -DISTANCIA_AVANCE ;
        }
        else if ( delta.getY() == 0){
            aniadir_x = ( delta.getX() > 0) ? DISTANCIA_AVANCE : -DISTANCIA_AVANCE ;
        }
        else {
            float tangente = Math.abs(delta.getY()/delta.getX());
            if (  tangente < Robot.TANGENTE_67_5 ){
                // Se mueve en x si el angulo esta entre 0 y 67.5 ( tres cuartos de 90 )
                aniadir_x = delta.getX()/Math.abs(delta.getX());
            }
            if ( tangente > Robot.TANGENTE_22_5 ){
                // Se mueve en y si el angulo esta entre 22.5 y 90 ( un cuarto de 90 )
                aniadir_y = delta.getY()/Math.abs(delta.getY());
            }
        }

        super.setPosicion(Vector2D.suma(new Vector2D(aniadir_x, aniadir_y), this.getPosicion()));
    }
}
