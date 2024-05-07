package com.Controller;

import com.model.ControladorLogico;
import com.model.ControladorLogico.ESTADOJUEGO;
import com.model.Vector2D;
import com.visual.Visual;

import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;

public class Controlador {

    private final Visual visual;
    private ControladorLogico logica;
    private Vector2D rang;
    private int level;
    private int score;

    public Controlador(Visual visual){
        this.visual = visual;
        this.level = 1;
        this.score = 0;
    }

    public void iniciarJuego(Vector2D rang, int level, int score){
        
        this.rang = rang;
        this.level = level;
        this.score = score;

        this.logica = new ControladorLogico(this.rang, this.level);

        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );

        this.visual.setInfo(this.level, this.score);
        this.setListeners();
    }

    public void iniciarJuego(Vector2D rang){
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

    private void redimensionarJuego(Vector2D rang){

        this.rang = rang;
        this.visual.redimensionarJuego(this.rang);
    
        this.iniciarJuego(this.rang);
    }

    private void funcionTp(){
        this.logica.tp();
        revisarEstadoJuego();
    }

    private void funcionSafeTp(){
        this.logica.safeTp();
        revisarEstadoJuego();
    }

    private void funcionEsperarRobots(){
        this.logica.esperarRobots();
        revisarEstadoJuego();
    }

    private void funcionClickTablero( Rectangle rect ){
        this.logica.hacerJugada(new Vector2D((int)rect.getX(), (int)rect.getY()));
        revisarEstadoJuego();
    }

    private void setListeners(){

        visual.onTpBtnClick(event -> funcionTp());

        visual.onWaitBtnClick(event -> funcionEsperarRobots());

        visual.onSafeTpBtnClick(event -> funcionSafeTp());

        visual.onTableroClick(event -> {
            Object target =  event.getTarget();

            if(target instanceof Rectangle){
                funcionClickTablero((Rectangle) target);
            }
        });

        visual.onOpcionesClick(event -> {
            MenuItem opcion = (MenuItem)event.getTarget();
            int opcionS = Integer.parseInt(opcion.getId());

            redimensionarJuego(new Vector2D(opcionS, opcionS));
        });
    }

    private void actualizarVisual(){
        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );
        this.visual.setInfo(this.level, this.score+ this.logica.getPuntaje());
    }
}