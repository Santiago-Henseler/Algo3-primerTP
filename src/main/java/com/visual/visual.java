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

public class visual {

    private Scene escena;
    private VBox fondo;
    private menu menu;
    private tablero tablero;
    private footer footer;
    private Rectangle personaje;
   

    public visual(Stage escenario, vector2D dim){

        this.menu = new menu();
        this.tablero = new tablero();
        this.footer = new footer();

        this.fondo = new VBox(this.menu.getMenu());
        this.fondo.getChildren().add(this.tablero.getTablero(dim));
        this.fondo.getChildren().add(this.footer.getfooter());

        this.personaje = new Rectangle(15, 15);
        this.personaje.setX(9);
        this.personaje.setY(9);
        this.personaje.setStroke(Color.BLACK);
        this.personaje.setFill(Color.RED);

        this.tablero.addPersonaje(this.personaje);

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
    }

    public void moverPersonaje(){

        double posActualX = this.personaje.getTranslateX();
        double posActualY = this.personaje.getTranslateY();

        this.personaje.setTranslateX(posActualX + 15);
        this.personaje.setTranslateY(posActualY + 15);
    }
    
    public void onTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.tpButton.setOnAction(handler);
    }

    public void onWaitBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.waiButton.setOnAction(handler);
    }

    public void onSafeTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.waiButton.setOnAction(handler);
    }

    public void onTableroClick(EventHandler<MouseEvent> handler) {
        this.tablero.tablero.setOnMouseClicked(handler);
    }

}
