package com.model;

public abstract class Robot extends EntidadBase {
    Robot( vector2D posicion){
        super(posicion);
    }

    public void setPosicion( vector2D posicion){
        super.setPosicion(posicion);
    }

    public abstract void movimiento( jugador jugador );

    public boolean colision( EntidadBase entidad ){
        if ( entidad instanceof jugador )
            return false;
        else
            return super.getPosicion().esIgual( entidad.getPosicion() ) ;
    }

}
