package com.Controller;

import com.model.vector2D;
import com.visual.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controlador {

    private final visual visual;

    public Controlador(visual visual){
        this.visual = visual;
    }

    public void redimencionarJuego(vector2D rang){

        visual.redimencionarTablero(rang);

        iniciarJuego(rang);

    }

    public void iniciarJuego(vector2D rang){

        ControladorLogico cl = new ControladorLogico(rang);

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                visual.moverPersonaje(cl.tp());
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.esperarRobots();
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
            }
        });

        visual.onTableroClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object target =  event.getTarget();

                if(target instanceof Rectangle){
                    Rectangle rect = (Rectangle)target;

                    vector2D mov = cl.hacerJugada(new vector2D((int)rect.getX(), (int)rect.getY()));
                    visual.moverPersonaje(mov);


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

}
