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

    public void iniciarJuego(){

        ControladorLogico cl = new ControladorLogico(new vector2D(20, 20));

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                vector2D pos = cl.tp();
                visual.moverPersonaje(pos);
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
                cl.safeTp();
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
    }

}
