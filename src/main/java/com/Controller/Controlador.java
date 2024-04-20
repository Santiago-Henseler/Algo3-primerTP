package com.Controller;

import java.util.Vector;

import com.model.ControladorLogico;
import com.model.vector2D;
import com.visual.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controlador {

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

    public Controlador(visual visual){
        this.visual = visual;
    }

    public void redimencionarJuego(vector2D rang){

        this.rang = rang;
        this.visual.redimencionarJuego(this.rang);
    
        iniciarJuego(this.rang);
    }

    public void iniciarJuego(vector2D rang){
        
        this.rang = rang;

        this.logica = new ControladorLogico(rang);

        this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );
//        this.visual.setRobots(this.logica.getPosRobots());

        this.setListeners(this.logica);
    }

    private void setListeners(ControladorLogico cl){

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.tp();

                if(cl.estadoJuego())
                    actualizarVisual(mov);
                else
                    iniciarJuego(rang);
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.esperarRobots();

                if(cl.estadoJuego())
                    actualizarVisual(mov);
                else
                    iniciarJuego(rang);
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.safeTp();

                if(cl.estadoJuego())
                    actualizarVisual(mov);
                else
                    iniciarJuego(rang);
            }
        });

        visual.onTableroClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object target =  event.getTarget();

                if(target instanceof Rectangle){
                    Rectangle rect = (Rectangle)target;

                    vector2D mov = cl.hacerJugada(new vector2D((int)rect.getX(), (int)rect.getY()));
     
                    if(cl.estadoJuego())
                        actualizarVisual(mov);
                    else
                        iniciarJuego(rang);
                }
            }
        });

        visual.onOpcionesClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                redimencionarJuego(new vector2D(10, 10));
            }
        });
    }

    private void actualizarVisual(vector2D mov){

        if(mov != null){
            this.visual.moverPersonaje(mov);
            this.visual.setPersonajes( this.logica.getPosPersonajes(), this.logica.getTipoPersonajes() );
//            visual.setRobots( this.logica.getPosRobots());
//            visual.setFuego( this.logica.getPosFuegos());
        }
        else{
            // Termino el juego. Deberia haber un menu pero bueno que se yo viste
            this.iniciarJuego(this.rang);
        }
    }
}