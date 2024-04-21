package com.main;

import javafx.application.Application;
import javafx.stage.Stage;

import com.Controller.Controlador;
import com.model.vector2D;
import com.visual.visual;

public class App extends Application {

    @Override
    public void start(Stage stage){
        vector2D rang = new vector2D(Controlador.TAMANIO_HORIZONTAL, Controlador.TAMANIO_VERTICAL);
        visual visual = new visual(stage, rang);
        Controlador controlador = new Controlador(visual);
        controlador.iniciarJuego(rang, 1,0);
    }

    public static void main(String[] args) {
        launch();
    }

}