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

        ControladorLogico cl = new ControladorLogico(new vector2D(0, 0));

        visual.onTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
            }
        });

        visual.onWaitBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.hacerJugada(new vector2D(0, 0));
            }
        });

        visual.onSafeTpBtnClick(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cl.hacerJugada(new vector2D(0, 0));
            }
        });

        visual.onTableroClick(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object target =  event.getTarget();

                if(target instanceof Rectangle){
                    Rectangle rect = (Rectangle)target;

                    if(cl.hacerJugada(new vector2D((int)rect.getX(), (int)rect.getY())))
                        visual.moverPersonaje();
                }
            }
        });
    }

}
