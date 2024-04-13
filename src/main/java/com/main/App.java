package com.main;

import javafx.application.Application;
import javafx.stage.Stage;

import com.Controller.Controlador;
import com.model.vector2D;
import com.visual.visual;

public class App extends Application {


    @Override
    public void start(Stage stage){
        vector2D rang = new vector2D(20, 20);
        visual visual = new visual(stage, rang);
        Controlador controlador = new Controlador(visual);
        controlador.iniciarJuego();
    }

    public static void main(String[] args) {
        launch();
    }

}