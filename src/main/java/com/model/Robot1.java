package com.model;

import java.lang.Math;

public class Robot1 extends Robot {

    Robot1( vector2D posicion ){
        super(posicion);
    }

    public boolean colision( EntidadBase entidad ){
        return super.colision(entidad);
    }

    public void movimiento( jugador jugador ){
        vector2D delta = vector2D.resta( jugador.getPosicion(), this.getPosicion() );

        int aniadir_x; int aniadir_y;
        if ( delta.getX() == 0 ){
            aniadir_x = 0;
            aniadir_y = ( delta.getY() > 0) ? 1 : -1 ;
        }
        else if ( delta.getY() == 0){
            aniadir_y = 0;
            aniadir_x = ( delta.getX() > 0) ? 1 : -1 ;
        }
        else {
            float angulo = Math.abs(delta.getY()/delta.getX());
            aniadir_x = delta.getX()/Math.abs(delta.getX());    // Queda 1 o -1 segun sea delante o atras
            aniadir_y = delta.getY()/Math.abs(delta.getY());

            if (  angulo <= 0.414 ){ aniadir_y = 0; }
            else if ( angulo > 2.414 ){ aniadir_x = 0; }
        }

        super.setPosicion( new vector2D( aniadir_x, aniadir_y ) );
    }
}
