package com.visual;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class header {

    Label label;
    BorderPane header;
    MenuButton opciones;

    public BorderPane getHeader(){
        this.label = new Label("Level: 1    Score: 0");
        this.label.setFont(Font.font("Uroob", 20));

        this.opciones = new MenuButton("=");

        this.header = new BorderPane();

        this.header.setCenter( this.label );
        this.header.setRight(opciones);
        this.header.setPadding(new Insets(10));
        this.header.setStyle("-fx-background-color:#D6DBDF");

        return this.header;
    }
}
