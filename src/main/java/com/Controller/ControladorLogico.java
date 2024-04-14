package com.Controller;

import java.util.ArrayList;

import com.model.model;
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

    public vector2D hacerJugada(vector2D movimiento){

        vector2D nuevaPosicion = model.reescalarDistancia(movimiento, jugador.getPosicion());

        jugador.movimiento(nuevaPosicion);

        return nuevaPosicion;
    }

    public void esperarRobots(){
        this.hacerJugada(this.jugador.getPosicion());
    }

    public vector2D tp(){
        return this.jugador.tp(rango);
    }

    public vector2D safeTp(){

        if(!this.jugador.tieneSafeTp())
            return null;

        this.jugador.tp(rango);
        return null;
    }

    public boolean estadoJuego(){
        Boolean estado = jugador.getVida();

        return estado;
    }

}