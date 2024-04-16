package com.visual;

import com.model.vector2D;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class visual {

    private Scene escena;
    private VBox fondo;
    private header header;
    private tablero tablero;
    private footer footer;
    private Rectangle personaje;

    public visual(Stage escenario, vector2D dim){

        this.header = new header();
        this.tablero = new tablero();
        this.footer = new footer();

        this.fondo = new VBox(this.header.getHeader());
        this.fondo.setStyle("-fx-background-color:#D6DBDF");
        this.fondo.getChildren().add(this.tablero.getTablero(dim));
        this.fondo.getChildren().add(this.footer.getfooter());

        this.personaje = new Rectangle(15, 15);
        this.personaje.setX(9);
        this.personaje.setY(9);
        this.personaje.setStroke(Color.BLACK);
        this.personaje.setFill(Color.RED);


        this.tablero.addEntidad(this.personaje);

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
    }

    public void moverPersonaje(vector2D mov){

        double posActualX = this.personaje.getTranslateX();
        double posActualY = this.personaje.getTranslateY();

        this.personaje.setTranslateX(posActualX + (15*mov.getX()));
        this.personaje.setTranslateY(posActualY + (15*mov.getY()));

    }
    
    public void redimencionarTablero(vector2D rang){

        this.fondo.getChildren().remove(1);
        this.fondo.getChildren().add(1, this.tablero.getTablero(rang));
        this.tablero.sacarEntidad(this.personaje);

        this.personaje = new Rectangle(15, 15);
        this.personaje.setX(rang.getX()/2 -1);
        this.personaje.setY(rang.getY()/2 -1);
        this.personaje.setStroke(Color.BLACK);
        this.personaje.setFill(Color.YELLOW);

        this.tablero.addEntidad(this.personaje);

    }

    public void onTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.tpButton.setOnAction(handler);
    }

    public void onWaitBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.waitButton.setOnAction(handler);
    }

    public void onSafeTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.tpSafeButton.setOnAction(handler);
    }

    public void onTableroClick(EventHandler<MouseEvent> handler) {
        this.tablero.tablero.setOnMouseClicked(handler);
    }

    public void onOpcionesClick(EventHandler<MouseEvent> handler) {
        this.header.opciones.setOnMouseClicked(handler);
    }

}
