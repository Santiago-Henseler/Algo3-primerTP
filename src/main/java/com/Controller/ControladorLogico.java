package com.Controller;

import java.util.ArrayList;
import java.util.Random;

import com.model.model;
import com.model.vector2D;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D rang){
        this.rango = rang;
        this.jugador = new jugador(new vector2D(rang.getY()/2 -1, rang.getY()/2 -1));

        this.enemigos = new ArrayList<EntidadBase>();

        Random rand = new Random();
        int x_robot; int y_robot;
        for ( int i =0; i < 0 ; i++){
            x_robot = 0;
            y_robot = 0;

            while ( ( x_robot == 0 ) && ( y_robot == 0 ) ){
                x_robot = rand.nextInt( this.rango.getX() );
                y_robot = rand.nextInt( this.rango.getY() );
            }

            this.enemigos.add( new Robot1( new vector2D( x_robot, y_robot ) ));
        }
    }

    public vector2D hacerJugada(vector2D movimiento){

        vector2D nuevaPosicion = model.reescalarDistancia(movimiento, jugador.getPosicion());
        jugador.movimiento(nuevaPosicion);
        this.actualizarPosicionRobots();

        nuevaPosicion = ( ! this.revisarColisionJugador()) ? nuevaPosicion : null ;
        revisarColisionEnemigos();

        return nuevaPosicion;
    }

    public void esperarRobots(){
        this.hacerJugada(this.jugador.getPosicion());
    }

    private void actualizarPosicionRobots(){
        // metodo para actualizar posicion de enemigos
        for ( int i = 0; i < enemigos.size(); i++ ){
            enemigos.get(i).movimiento( this.jugador );
        }
    }

    private boolean revisarColisionJugador(){
        // Revisa con todos los enemigos si hay colision
        if (enemigos.isEmpty()){
            return false;
        }
        else {
            int index = 0;
            while ( !jugador.colision( enemigos.get(index) ) && index < enemigos.size()){
                index ++;
            }

            return ( index == enemigos.size() );
        }
    }

    private void  revisarColisionEnemigos(){
        // Revisa enemigos que colisionan y elimina los que no, colocando fuego donde si
        boolean eliminar_primero = false;
        boolean eliminar_segundo = false;

        for ( int i = 0; i < enemigos.size(); i++){
            for ( int j = i + 1; j < enemigos.size(); j++){
                eliminar_primero = enemigos.get(i).colision(enemigos.get(j));
                eliminar_segundo = enemigos.get(j).colision(enemigos.get(i));

                if ( eliminar_primero && eliminar_segundo ){
                    enemigos.add( new fuego( enemigos.get(i).getPosicion() )) ;
                    enemigos.remove(i);
                    enemigos.remove(j - 1 );
                }
                else if ( eliminar_primero ){
                    enemigos.add( new fuego( enemigos.get(i).getPosicion() )) ;
                    enemigos.remove(i);
                }
                else if (eliminar_segundo){
                    enemigos.add( new fuego( enemigos.get(j).getPosicion() )) ;
                    enemigos.remove(j);
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