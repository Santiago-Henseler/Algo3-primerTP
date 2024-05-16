package com.main;

import javafx.application.Application;
import javafx.stage.Stage;

import com.Controller.Controlador;
import com.model.Vector2D;
import com.visual.Visual;

public class App extends Application {

    static public final int TAMANIO_HORIZONTAL = 20;
    static public final int TAMANIO_VERTICAL = 20;

    @Override
    public void start(Stage stage){
        Vector2D rang = new Vector2D(TAMANIO_HORIZONTAL, TAMANIO_VERTICAL);
        Visual visual = new Visual(stage, rang);
        Controlador controlador = new Controlador(visual);
        controlador.iniciarJuego(rang);
    }

    public static void main(String[] args) {
        launch();
    }

}