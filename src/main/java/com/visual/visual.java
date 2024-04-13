package com.visual;

import com.model.vector2D;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class visual {

    private Scene escena;
    private VBox fondo;
    private menu menu;
    private tablero tablero;
    private footer footer;
   

    public visual(Stage escenario, vector2D dim){

        this.menu = new menu();
        this.tablero = new tablero();
        this.footer = new footer();

        this.fondo = new VBox(this.menu.getMenu());
        this.fondo.getChildren().add(this.tablero.getTablero(dim));
        this.fondo.getChildren().add(this.footer.getfooter());

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
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

}
