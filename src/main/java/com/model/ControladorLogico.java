package com.model;

import java.util.ArrayList;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D rang){
        this.rango = rang;
        this.jugador = new jugador(new vector2D(rang.getY()/2 -1, rang.getY()/2 -1));
        this.enemigos = new ArrayList<EntidadBase>();
    }

    public vector2D hacerJugada(vector2D movimiento){

        vector2D nuevaPosicion = vector2D.reescalarDistancia(movimiento, jugador.getPosicion());

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