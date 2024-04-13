package com.Controller;

import com.model.vector2D;
import com.visual.visual;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
    }
}
