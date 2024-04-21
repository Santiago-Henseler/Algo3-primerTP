package com.visual;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class header {

    Label label;
    BorderPane header;
    MenuButton opciones;
    MenuItem op1;
    MenuItem op2;
    MenuItem op3;
    MenuItem op4;
    MenuItem op5;

    public BorderPane getHeader(){
        this.label = new Label("Level: 1    Score: 0");
        this.label.setFont(Font.font("Uroob", 20));

        this.opciones = new MenuButton("=");

        this.opciones.setMnemonicParsing(true);

        this.op1 = new MenuItem("10X10");
        this.op1.setId("10");
        this.op2 = new MenuItem("15X15");
        this.op2.setId("15");
        this.op3 = new MenuItem("20X20");
        this.op3.setId("20");
        this.op4 = new MenuItem("25X25");
        this.op4.setId("25");
        this.op5 = new MenuItem("30X30");
        this.op5.setId("30");
       
        this.opciones.getItems().addAll(op1, op2, op3, op4, op5);

        this.header = new BorderPane();

        this.header.setCenter( this.label );
        this.header.setRight(opciones);
        this.header.setPadding(new Insets(10));
        this.header.setStyle("-fx-background-color:#D6DBDF");

        return this.header;
    }

    public void setLvlScore(int lvl, int score){
        this.label.setText("Level: "+lvl+"    "+"Score: "+score);
    }
}
