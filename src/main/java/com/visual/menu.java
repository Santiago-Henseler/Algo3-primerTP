package com.visual;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;

public class menu {

    Button btn;
    Label nivel;
    BorderPane menu;
    MenuButton opciones;

    public BorderPane getMenu(){
        this.nivel = new Label("Nivel");

        this.btn = new Button("click");
        this.opciones = new MenuButton("=");

        this.menu = new BorderPane();

        this.menu.setCenter(btn);
        this.menu.setRight(opciones);
        this.menu.setPadding(new Insets(10));
        this.menu.setStyle("-fx-background-color:#D6DBDF");

        return this.menu;
    }
}
