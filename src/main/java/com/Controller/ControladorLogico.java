package com.Controller;

import java.util.ArrayList;

import com.model.vector2D;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D vec){
        this.rango = vec;
        this.jugador = new jugador(new vector2D(0, 0));
        this.enemigos = new ArrayList<EntidadBase>();
    }

    public void iniciarJuego(){

        jugador.tp(rango);

    }

    public boolean hacerJugada(vector2D movimiento){
        Boolean a = true;

        jugador.movimiento(movimiento);
        
        /*
        * movimientos de los enemigos
        for(EntidadBase i: enemigos){
            i.movimiento(jugador.getPosicion());
        }
         */
        return a;
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