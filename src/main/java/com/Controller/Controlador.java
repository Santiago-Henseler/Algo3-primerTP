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

    private final visual visual;
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

        ControladorLogico cl = new ControladorLogico(rang);

        this.visual.setRobots(cl.getPosRobots());

        this.setListeners(cl);
    }

    private void setListeners(ControladorLogico cl){

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.tp();

                if(cl.estadoJuego())
                    actualizarVisual(cl, mov);
                else
                    iniciarJuego(rang);
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.esperarRobots();

                if(cl.estadoJuego())
                    actualizarVisual(cl, null);
                else
                    iniciarJuego(rang);
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D mov = cl.safeTp();

                if(cl.estadoJuego())
                    actualizarVisual(cl, mov);
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
                        actualizarVisual(cl, mov);
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

    private void actualizarVisual(ControladorLogico cl, vector2D mov){

        if(mov != null)
            visual.moverPersonaje(mov);
        visual.setRobots(cl.getPosRobots());
        visual.setFuego(cl.getPosFuegos());
    }
}