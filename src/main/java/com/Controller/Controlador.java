package com.Controller;

import com.model.ControladorLogico;
import com.model.vector2D;
import com.visual.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controlador {

    static public enum ESTADOJUEGO{ACTIVO, VICTORIA, DERROTA}
    static public enum PERSONAJE{ROBOT1, ROBOT2, FUEGO, JUGADOR}

    static public final double PROBABILIDAD_ROBOT_2 = 0.2;

    static public final int TAMANIO_HORIZONTAL = 20;
    static public final int TAMANIO_VERTICAL = 20;

    private final visual visual;
    private ControladorLogico logica;
    private vector2D rang;
    private int level;
    private int score;

    public Controlador(visual visual){
        this.visual = visual;
        this.level = 1;
        this.score = 0;
    }

    public void iniciarJuego(vector2D rang, int level, int score){
        
        this.rang = rang;
        this.level = level;
        this.score = score;

        this.logica = new ControladorLogico(this.rang, this.level);

        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );

        this.visual.setInfo(this.level, this.score);
        this.setListeners();
    }

    public void iniciarJuego(vector2D rang){
        iniciarJuego(rang, 1, 0);
    }

    private void revisarEstadoJuego(){
        if(this.logica.estadoJuego() == ESTADOJUEGO.ACTIVO){
            this.actualizarVisual();
        }
        else if(this.logica.estadoJuego() == ESTADOJUEGO.VICTORIA){
            this.score += this.logica.getPuntaje();
            this.iniciarJuego(rang, level+1, this.score);
        }
        else
            this.iniciarJuego(rang);
    }

    private void redimencionarJuego(vector2D rang){

        this.rang = rang;
        this.visual.redimencionarJuego(this.rang);
    
        this.iniciarJuego(this.rang);
    }

    private void funcionTp(){
        this.logica.tp();
        revisarEstadoJuego();
    }

    private void funcionSafeTp(){
        vector2D mov = this.logica.safeTp();

        if(mov == null)
            return;

        revisarEstadoJuego();
    }

    private void funcionEsperarRobots(){
        this.logica.esperarRobots();
        revisarEstadoJuego();
    }

    private void funcionClickTablero( Rectangle rect ){
        this.logica.hacerJugada(new vector2D((int)rect.getX(), (int)rect.getY()));
        revisarEstadoJuego();
    }

    private void setListeners(){

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                funcionTp();
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                funcionEsperarRobots();
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                funcionSafeTp();
            }
        });

        visual.onTableroClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object target =  event.getTarget();

                if(target instanceof Rectangle){
                    funcionClickTablero((Rectangle) target);
                }
            }
        });

        visual.onOpcionesClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MenuItem opcion = (MenuItem)event.getTarget();
                int opcionS = Integer.parseInt(opcion.getId());

                redimencionarJuego(new vector2D(opcionS, opcionS));
            }
        });
    }

    private void actualizarVisual(){
        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );
        this.visual.setInfo(this.level, this.score+ this.logica.getPuntaje());
    }
}