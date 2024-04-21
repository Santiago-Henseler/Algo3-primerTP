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

    static public final int CODIGO_ROBOT_1 = 0;
    static public final int CODIGO_ROBOT_2 = 1;
    static public final int CODIGO_FUEGO = 2;
    static public final int CODIGO_JUGADOR = 3;

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

    private void redimencionarJuego(vector2D rang){

        this.rang = rang;
        this.visual.redimencionarJuego(this.rang);
    
        this.iniciarJuego(this.rang, 1, 0);
    }

    public void iniciarJuego(vector2D rang, int level, int score){
        
        this.rang = rang;
        this.level = level;
        this.score = score;

        this.logica = new ControladorLogico(this.rang, this.level);

        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );

        this.visual.setInfo(this.level, this.score);
        this.setListeners(this.logica);
    }

    private void setListeners(ControladorLogico cl){

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.tp();

                if(cl.estadoJuego() == ESTADOJUEGO.ACTIVO)
                    actualizarVisual();
                else if(cl.estadoJuego() == ESTADOJUEGO.VICTORIA)
                    iniciarJuego(rang, level+1, score);
                else
                    iniciarJuego(rang, 1, 0);
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.esperarRobots();

                if(cl.estadoJuego() == ESTADOJUEGO.ACTIVO)
                    actualizarVisual();
                else if(cl.estadoJuego() == ESTADOJUEGO.VICTORIA)
                    iniciarJuego(rang, level+1, score);
                else
                    iniciarJuego(rang, 1, 0);
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.safeTp();

                if(mov == null)
                    return;

                if(cl.estadoJuego() == ESTADOJUEGO.ACTIVO)
                    actualizarVisual();
                else if(cl.estadoJuego() == ESTADOJUEGO.VICTORIA)
                    iniciarJuego(rang, level+1, score);
                else
                    iniciarJuego(rang, 1, 0);
            }
        });

        visual.onTableroClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object target =  event.getTarget();

                if(target instanceof Rectangle){
                    Rectangle rect = (Rectangle)target;

                    cl.hacerJugada(new vector2D((int)rect.getX(), (int)rect.getY()));
     
                    if(cl.estadoJuego() == ESTADOJUEGO.ACTIVO)
                        actualizarVisual();
                    else if(cl.estadoJuego() == ESTADOJUEGO.VICTORIA)
                        iniciarJuego(rang, level+1, score);
                    else
                        iniciarJuego(rang, 1, 0);
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
        this.visual.setInfo(this.level,  this.logica.getPuntaje());
    }
}