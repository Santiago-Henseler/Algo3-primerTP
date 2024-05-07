package com.model;

import java.util.ArrayList;

public class ControladorLogico {

    static public enum ESTADOJUEGO{ACTIVO, VICTORIA, DERROTA}
    static public enum PERSONAJE{ROBOT1, ROBOT2, FUEGO, JUGADOR}

    public final double PROBABILIDAD_ROBOT_2 = 0.2;
 
    private final Vector2D rango;
    private final ArrayList<EntidadBase> enemigos;
    private final Jugador jugador;

    private int puntaje;
    private int totRobots;

    public ControladorLogico(Vector2D rango, int level){
        this.rango = rango;
        this.jugador = new Jugador(new Vector2D(rango.getX()/2 -1, rango.getY()/2 -1));
        this.jugador.setSafeTp(level);

        this.enemigos = new ArrayList<>();

        this.puntaje = 0;
        this.totRobots = rango.getX()/2; 

        
        for ( int i = 0; i <  rango.getX()/2 ; i++){

            int tipo = Math.random() < this.PROBABILIDAD_ROBOT_2*level?1:0;

            this.enemigos.add(setRobot(tipo));

        }
    }

    private EntidadBase setRobot(int tipo){
        int x_robot; int y_robot;

        x_robot = (int) Math.round(Math.random()*(rango.getX()-1));
        y_robot = (int) Math.round(Math.random()*(rango.getY()-1));

        Vector2D pos = new Vector2D( x_robot, y_robot );

        EntidadBase nuevoRobot = tipo == 0?new Robot1(pos):new Robot2(pos); 

        if(revisarColision(nuevoRobot) || pos.esIgual(new Vector2D(rango.getX()/2 -1, rango.getY()/2 -1)))
            return setRobot(tipo);

        return nuevoRobot;
    }

    public ArrayList<Vector2D> getPosPersonajes(){
        ArrayList<Vector2D> posPersonajes = new ArrayList<>();

        posPersonajes.add(this.jugador.getPosicion());
        for(EntidadBase i: enemigos)
            posPersonajes.add(i.getPosicion());

        return posPersonajes;
    }

    public int getPuntaje(){return this.puntaje;}

    public PERSONAJE[] getTipoPersonajes(){
        PERSONAJE[] tipoPersonajes = new PERSONAJE[this.enemigos.size() + 1];

        tipoPersonajes[0] = this.jugador.tipo();
        for(int i = 0; i < this.enemigos.size(); i++)
            tipoPersonajes[i + 1] = this.enemigos.get(i).tipo();
        
        return tipoPersonajes;
    }

    public Vector2D hacerJugada(Vector2D movimiento){
        // Mover jugador
        Vector2D dReescalado = Vector2D.reescalarDistancia(movimiento, this.jugador.getPosicion());
        Vector2D nuevaPosicion = Vector2D.suma(this.jugador.getPosicion(), dReescalado);
        jugador.movimiento(nuevaPosicion);

        // Logica del juego
        this.actualizarPosicionEnemigos();
        this.revisarColisionEnemigos();

        boolean colisiona = this.revisarColision(this.jugador);

        if(colisiona)
            this.jugador.setVida(false);

        return colisiona?null:nuevaPosicion;
    }

    public Vector2D esperarRobots(){
        return this.hacerJugada(this.jugador.getPosicion());
    }

    public Vector2D tp(){
        Vector2D nuevaPos = this.jugador.tp(rango);
        this.actualizarPosicionEnemigos();
        this.revisarColisionEnemigos();

        boolean colisiona = this.revisarColision(this.jugador);

        if(colisiona)
            this.jugador.setVida(false);

        return colisiona?null:nuevaPos;
    }

    public Vector2D safeTp(){

        if(!this.jugador.tieneSafeTp())
            return null;

        Vector2D nuevaPos = this.jugador.tp(this.rango);

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

    // Revisa enemigos que colisionan y elimina los que no, colocando fuego donde si
    private void revisarColisionEnemigos(){
       
        ArrayList<EntidadBase> eliminados = new ArrayList<>();
        ArrayList<Fuego> nuevFuegos = new ArrayList<>();

        for (EntidadBase i: this.enemigos)
            if (revisarColision(i)){
                eliminados.add(i);
                nuevFuegos.add(new Fuego(i.getPosicion()));
            }

        this.puntaje += eliminados.size();
        this.enemigos.removeAll(eliminados);
        this.enemigos.addAll(nuevFuegos);
    }

    private boolean revisarColision(EntidadBase e){
        
        boolean colisiona = false;

        for(EntidadBase i: this.enemigos){
            if(e == i)
                continue;
            colisiona = e.colision(i) || colisiona;
        }
        
        return colisiona;
    }

    public ESTADOJUEGO estadoJuego(){

        if(this.totRobots <= this.puntaje)
            return ESTADOJUEGO.VICTORIA;
        
        if(!this.jugador.getVida())
            return ESTADOJUEGO.DERROTA;

        return ESTADOJUEGO.ACTIVO;
    }

}