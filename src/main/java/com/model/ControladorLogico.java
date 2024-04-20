package com.model;

import com.Controller.Controlador;

import java.util.ArrayList;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final ArrayList<vector2D> fuegos;
    private final jugador jugador;

    public ControladorLogico(vector2D rango){
        this.rango = rango;
        this.jugador = new jugador(new vector2D(rango.getY()/2 -1, rango.getY()/2 -1));

        this.enemigos = new ArrayList<EntidadBase>();
        this.fuegos = new ArrayList<vector2D>();

        int x_robot; int y_robot;
        for ( int i = 0; i <  rango.getX()/2 ; i++){
            // Generar posicion de robots. No chequea si hay un robot o jugador ahi
            // Habria que programar para que cree robots de forma segura ...
            x_robot = (int) Math.round(Math.random()*(rango.getX()-1));
            y_robot = (int) Math.round(Math.random()*(rango.getY()-1));
            vector2D pos = new vector2D( x_robot, y_robot );

            if ( Math.random() < Controlador.PROBABILIDAD_ROBOT_2 )
                this.enemigos.add(new Robot2(pos));
            else
                this.enemigos.add(new Robot1(pos)) ;
        }
    }

    public ArrayList<vector2D> getPosPersonajes(){
        ArrayList<vector2D> posPersonajes = new ArrayList<vector2D>();

        posPersonajes.add(this.jugador.getPosicion());
        for(EntidadBase i: enemigos)
            posPersonajes.add(i.getPosicion());

        return posPersonajes;
    }

    public int[] getTipoPersonajes(){
        int[] tipoPersonajes = new int[this.enemigos.size() + 1];

        tipoPersonajes[0] = this.jugador.tipo();
        for(int i = 0; i < this.enemigos.size(); i++){
            tipoPersonajes[i + 1] = this.enemigos.get(i).tipo();
        }

        return tipoPersonajes;
    }

    public ArrayList<vector2D> getPosRobots(){
        ArrayList<vector2D> posRobots = new ArrayList<vector2D>();

        for(EntidadBase i: enemigos)
            if(i instanceof Robot)
                posRobots.add(i.getPosicion());

        return posRobots;
    }

    public ArrayList<vector2D> getPosFuegos(){
        return fuegos;
    }

    public vector2D hacerJugada(vector2D movimiento){
        // Mover jugador
        vector2D dReescalado = vector2D.reescalarDistancia(movimiento, this.jugador.getPosicion());
        vector2D nuevaPosicion = vector2D.suma(this.jugador.getPosicion(), dReescalado);
        jugador.movimiento(nuevaPosicion);

        // Logica del juego
        this.actualizarPosicionEnemigos();
        this.revisarColisionEnemigos();
        if (this.revisarColisionJugador())
            nuevaPosicion = null;

        return nuevaPosicion;
    }

    public vector2D esperarRobots(){
        return this.hacerJugada(this.jugador.getPosicion());
    }

    public vector2D tp(){
        vector2D nuevaPos = this.jugador.tp(rango);
        this.actualizarPosicionEnemigos();

        return nuevaPos;
    }

    public vector2D safeTp(){

        if(!this.jugador.tieneSafeTp())
            return null;

        vector2D nuevaPos = this.jugador.tp(this.rango);

        if(revisarColision(jugador))
            return this.safeTp();

        this.jugador.rmSafeTp();
        this.actualizarPosicionEnemigos();

        return nuevaPos;
    }

    private void actualizarPosicionEnemigos(){
        for (EntidadBase i: this.enemigos){
            i.movimiento(this.jugador.getPosicion());
        }
    }

    private void revisarColisionEnemigos(){
        // Revisa enemigos que colisionan y elimina los que no, colocando fuego donde si

        ArrayList<EntidadBase> eliminados = new ArrayList<EntidadBase>();
        ArrayList<EntidadBase> nuevosFuegos = new ArrayList<EntidadBase>();
        
        for (EntidadBase i: this.enemigos){
            if (revisarColision(i)){
                eliminados.add(i);
                nuevosFuegos.add(new fuego(i.getPosicion()));
                this.fuegos.add(i.getPosicion());
            }
        }

        this.enemigos.removeAll(eliminados);
        this.enemigos.addAll(nuevosFuegos);
    }

    private boolean revisarColision(EntidadBase e){
        
        Boolean colisiona = false;

        for(EntidadBase i: this.enemigos){
            if(e == i)
                continue;
            colisiona = e.colision(i) ? true:colisiona;
        }
        
        return colisiona;
    }

    public boolean revisarColisionJugador(){
        boolean colision = false;
        int index = 0;

        while ( !colision && ( index < this.enemigos.size() ) ){
            colision = this.jugador.colision( this.enemigos.get(index));
            if (!colision)
                index++;
        }

        return colision;
    }

    public boolean estadoJuego(){
        return ( !this.getPosRobots().isEmpty() && ( this.jugador != null )) ;
    }

}