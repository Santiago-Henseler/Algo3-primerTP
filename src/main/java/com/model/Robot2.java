package com.model;

import com.model.ControladorLogico.PERSONAJE;

import java.lang.Math;

public class Robot2 extends Robot {
    private static final int DISTANCIA_AVANCE_RAPIDO = 2;
    private static final int DISTANCIA_AVANCE_LENTO = 1;

    Robot2( Vector2D posicion ){super(posicion);}

    public boolean colision( EntidadBase entidad ){return super.colision(entidad);}

    public PERSONAJE tipo(){return PERSONAJE.ROBOT2;}

    public void movimiento(Vector2D posicion_jugador){
        Vector2D delta = Vector2D.resta( posicion_jugador, this.getPosicion() );

        // Calcular distancia a moverse en ambos ejes
        int aniadir_x = 0; int aniadir_y = 0;
        if ( delta.getX() == 0 ){
            aniadir_y = ( Math.abs(delta.getY()) > 1) ? DISTANCIA_AVANCE_RAPIDO:DISTANCIA_AVANCE_LENTO;
        }
        else if ( delta.getY() == 0){
            aniadir_x = ( Math.abs(delta.getX()) > 1) ? DISTANCIA_AVANCE_RAPIDO:DISTANCIA_AVANCE_LENTO;
        }
        else {
            float tangente = Math.abs(delta.getY()/delta.getX());
            if (  tangente < Robot.TANGENTE_67_5 ){
                // Se mueve en x si el angulo esta entre 0 y 67.5 ( tres cuartos de 90 )
                aniadir_x = ( Math.abs(delta.getX()) > 1) ? DISTANCIA_AVANCE_RAPIDO:DISTANCIA_AVANCE_LENTO;
            }
            if ( tangente > Robot.TANGENTE_22_5 ){
                // Se mueve en y si el angulo esta entre 22.5 y 90 ( un cuarto de 90 )
                aniadir_y = ( Math.abs(delta.getY()) > 1) ? DISTANCIA_AVANCE_RAPIDO:DISTANCIA_AVANCE_LENTO;
            }
        }

        // Distancia positiva o negativa
        aniadir_x = ( delta.getX() > 0) ? aniadir_x : -aniadir_x  ;
        aniadir_y = ( delta.getY() > 0) ? aniadir_y : -aniadir_y  ;

        super.setPosicion(Vector2D.suma(new Vector2D(aniadir_x, aniadir_y), this.getPosicion()));
    }
}
