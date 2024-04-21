package com.model;

import com.Controller.Controlador;
import com.Controller.Controlador.ESTADOJUEGO;

import java.util.ArrayList;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final jugador jugador;

    private int puntaje;
    private int totRobots;

    public ControladorLogico(vector2D rango, int level){
        this.rango = rango;
        this.jugador = new jugador(new vector2D(rango.getX()/2 -1, rango.getY()/2 -1));
        this.jugador.setSafeTp(level);

        this.enemigos = new ArrayList<EntidadBase>();

        this.puntaje = 0;
        this.totRobots = rango.getX()/2; 

        
        for ( int i = 0; i <  rango.getX()/2 ; i++){

            int tipo = Math.random() < Controlador.PROBABILIDAD_ROBOT_2*level?1:0;

            this.enemigos.add(setRobot(tipo));

        }
    }

    private EntidadBase setRobot(int tipo){
        int x_robot; int y_robot;

        x_robot = (int) Math.round(Math.random()*(rango.getX()-1));
        y_robot = (int) Math.round(Math.random()*(rango.getY()-1));

        vector2D pos = new vector2D( x_robot, y_robot );

        EntidadBase nuevoRobot = tipo == 0?new Robot1(pos):new Robot2(pos); 

        if(revisarColision(nuevoRobot) || pos.esIgual(new vector2D(rango.getX()/2 -1, rango.getY()/2 -1)))
            return setRobot(tipo);

        return nuevoRobot;
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
        for(int i = 0; i < this.enemigos.size(); i++)
            tipoPersonajes[i + 1] = this.enemigos.get(i).tipo();
        
        return tipoPersonajes;
    }

    public int getPuntaje(){return this.puntaje;};

    /*
    public ArrayList<vector2D> getPosRobots(){
        ArrayList<vector2D> posRobots = new ArrayList<vector2D>();

        for(EntidadBase i: enemigos)
            if(i instanceof Robot)
                posRobots.add(i.getPosicion());

        return posRobots;
    }

    public ArrayList<vector2D> getPosFuegos(){
        return fuegos;
    } */

    public vector2D hacerJugada(vector2D movimiento){
        // Mover jugador
        vector2D dReescalado = vector2D.reescalarDistancia(movimiento, this.jugador.getPosicion());
        vector2D nuevaPosicion = vector2D.suma(this.jugador.getPosicion(), dReescalado);
        jugador.movimiento(nuevaPosicion);

        // Logica del juego
        this.actualizarPosicionEnemigos();
        this.revisarColisionEnemigos();

        Boolean colisiona = this.revisarColision(this.jugador);

        if(colisiona)
            this.jugador.setVida(false);

        return colisiona?null:nuevaPosicion;
    }

    public vector2D esperarRobots(){
        return this.hacerJugada(this.jugador.getPosicion());
    }

    public vector2D tp(){
        vector2D nuevaPos = this.jugador.tp(rango);
        this.actualizarPosicionEnemigos();

        Boolean colisiona = this.revisarColision(this.jugador);

        if(colisiona)
            this.jugador.setVida(false);

        return colisiona?null:nuevaPos;
    }

    public vector2D safeTp(){

        if(!this.jugador.tieneSafeTp())
            return null;

        vector2D nuevaPos = this.jugador.tp(this.rango);

        if(revisarColision(jugador))
            return this.safeTp();

        this.jugador.rmSafeTp();

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
        ArrayList<fuego> nuevFuegos = new ArrayList<fuego>();

        for (EntidadBase i: this.enemigos)
            if (revisarColision(i)){
                eliminados.add(i);
                nuevFuegos.add(new fuego(i.getPosicion()));
            }

        this.puntaje += eliminados.size();
        this.enemigos.removeAll(eliminados);
        this.enemigos.addAll(nuevFuegos);
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

    /* 
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
    */

    public ESTADOJUEGO estadoJuego(){

        if(this.totRobots <= this.puntaje)
            return ESTADOJUEGO.VICTORIA;
        
        if(!this.jugador.getVida())
            return ESTADOJUEGO.DERROTA;

        return ESTADOJUEGO.ACTIVO;
    }

}