package com.Controller;

import java.util.ArrayList;
import java.util.Random;

import com.model.model;
import com.model.vector2D;

public class ControladorLogico {
    
    private final vector2D rango;
    private ArrayList<Robot> robots;
    private ArrayList<EntidadBase> enemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D rang){
        this.rango = rang;
        this.jugador = new jugador(new vector2D(rang.getY()/2 -1, rang.getY()/2 -1));

        this.robots = new ArrayList<Robot>();
        this.enemigos = new ArrayList<EntidadBase>();

        Random rand = new Random();
        Robot nuevo;
        int x_robot; int y_robot;
        for ( int i =0; i < 4 ; i++){
            x_robot = 0;
            y_robot = 0;

            while ( ( x_robot == 0 ) && ( y_robot == 0 ) ){
                x_robot = rand.nextInt( this.rango.getX() );
                y_robot = rand.nextInt( this.rango.getY() );
            }

            nuevo = new Robot1( new vector2D( x_robot, y_robot ) ) ;
            this.robots.add( nuevo ) ;
            this.enemigos.add( nuevo );
        }
    }

    public vector2D hacerJugada(vector2D movimiento){

        vector2D nuevaPosicion = model.reescalarDistancia(movimiento, jugador.getPosicion());
        jugador.movimiento(nuevaPosicion);
        this.actualizarPosicionRobots();
        nuevaPosicion = (! this.revisarColisionJugador()) ? nuevaPosicion : null ;

        return nuevaPosicion;
    }

    public void esperarRobots(){
        this.hacerJugada(this.jugador.getPosicion());
    }

    private void actualizarPosicionRobots(){
        for ( int i = 0; i < robots.size(); i++ ){
            robots.get(i).movimiento( this.jugador );
        }
    }

    private boolean revisarColisionJugador(){
        int index = 0;
        while ( ! revisarColision( enemigos.get(index) , this.jugador) && index < enemigos.size()){
            index ++;
        }

        return ( index == enemigos.size() );
    }

    private boolean  revisarColision( EntidadBase p1, EntidadBase p2 ){
        return p1.getPosicion().esIgual( p2.getPosicion() );
    }

    private void  revisarColisionEnemigos(){
        for ( int i = 0; i < enemigos.size(); i++){
            for ( int j = i + 1; j < enemigos.size(); j++){
                if ( revisarColision( enemigos.get(i), enemigos.get(j))){
                    if ( !(enemigos.get(i) instanceof fuego) ){
                        enemigos.remove(i) ;

                        if ( !(enemigos.get(j - 1) instanceof fuego) ){
                            enemigos.remove(j - 1);
                        }
                    }
                }
            }
        }
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
        return true;
    }

}