package com.model;

import java.util.ArrayList;

public class ControladorLogico {
    
    private final vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final ArrayList<vector2D> posEnemigos;
    private final jugador jugador;
    
    public ControladorLogico(vector2D rango){
        this.rango = rango;
        this.jugador = new jugador(new vector2D(rango.getY()/2 -1, rango.getY()/2 -1));

        this.enemigos = new ArrayList<EntidadBase>();
        this.posEnemigos = new ArrayList<vector2D>();

        int x_robot; int y_robot;
        for ( int i = 0; i <  rango.getX()/2 ; i++){
            x_robot = (int) Math.round(Math.random()*(rango.getX()-1));
            y_robot = (int) Math.round(Math.random()*(rango.getY()-1));
            vector2D pos = new vector2D( x_robot, y_robot );

            this.enemigos.add( new Robot1(pos));
            this.posEnemigos.add(pos);
        }
    }

    public vector2D hacerJugada(vector2D movimiento){

        vector2D dReescalado = vector2D.reescalarDistancia(movimiento, jugador.getPosicion());
        
        vector2D nuevaPosicion = vector2D.suma(jugador.getPosicion(), dReescalado);

        jugador.movimiento(nuevaPosicion);
        
        this.actualizarPosicionRobots();

        //nuevaPosicion = ( ! this.revisarColisionJugador()) ? nuevaPosicion : null;

        return nuevaPosicion;
    }

    public ArrayList<vector2D> posicionEnemigos(){
        ArrayList<vector2D> posEnemigos = new ArrayList<vector2D>();

        for(EntidadBase i: enemigos){
            posEnemigos.add(i.getPosicion());
        }

        return posEnemigos;
    }

    private void actualizarPosicionRobots(){
        // metodo para actualizar posicion de enemigos
        for ( int i = 0; i < enemigos.size(); i++ ){
            enemigos.get(i).movimiento( this.jugador );
        }
        //this.revisarColisionEnemigos();
    }

    private void revisarColisionEnemigos(){
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
                    enemigos.remove(j - 1);
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

    public void esperarRobots(){
        this.hacerJugada(this.jugador.getPosicion());
    }


    private boolean revisarColisionJugador(){
        
        // Revisa con todos los enemigos si hay colision
        if (enemigos.isEmpty())
            return false;
        else {
            int index = 0;
            while ( !jugador.colision( enemigos.get(index)) && index < enemigos.size()){
                index ++;
            }

            return index == enemigos.size();
        }
    }

    public vector2D tp(){
        vector2D nuevaPos = this.jugador.tp(rango);

        this.actualizarPosicionRobots();

        return nuevaPos;
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

    public ArrayList<vector2D> posicionRobot(){
        ArrayList<vector2D> resultado = new ArrayList<vector2D>();
        for ( int i = 0; i < enemigos.size(); i++){
            resultado.add( enemigos.get(i).getPosicion() );
        }
        return resultado;
    }
}