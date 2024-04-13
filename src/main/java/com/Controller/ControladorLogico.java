package com.Controller;

import java.util.ArrayList;

import com.model.vector2D;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D vec){
        this.rango = vec;
        this.jugador = new jugador(new vector2D(9, 9));
        this.enemigos = new ArrayList<EntidadBase>();
    }

    public void iniciarJuego(){

        jugador.tp(rango);

    }

    public boolean hacerJugada(vector2D movimiento){
        Boolean a = true;

        jugador.movimiento(reescalarDistancia(movimiento));

        /*
        * movimientos de los enemigos
        for(EntidadBase i: enemigos){
            i.movimiento(jugador.getPosicion());
        }
         */
        return a;
    }


    private vector2D reescalarDistancia(vector2D movimiento){
        
        vector2D resta = vector2D.resta(movimiento, jugador.getPosicion());

        int x = 0;
        int y = 0;

        if(resta.getX() > 0)x = 1;
        else if(resta.getX() < 0)x = -1;
        
        if(resta.getY() < 0)y = 1;
        else if(resta.getY() > 0)y = -1;

        return new vector2D(x, y);
    }

    public boolean estadoJuego(){
        Boolean estado = jugador.getVida();

        /*
        for(EntidadBase i: enemigos){
            if(!i.getVida())
                estado = false;
        }
        */

        return estado;
    }

}