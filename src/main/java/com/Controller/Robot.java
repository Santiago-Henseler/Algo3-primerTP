package com.Controller;
import com.model.vector2D;

public abstract class Robot extends EntidadBase {
    Robot( vector2D posicion){
        super(posicion);
    }

    public void setPosicion( vector2D posicion){
        super.setPosicion(posicion);
    }

    public abstract void movimiento( jugador jugador );

    public void colision( jugador jugador ){

    }

    public void colision( fuego fuego ){

    }

    public void colision( Robot robot ){

    }

}
