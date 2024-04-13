package com.visual;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class footer {

    Button tpButton;
    Button tpSafeButton;
    Button waiButton;
    Label nivel;
    BorderPane footer;

    public BorderPane getfooter(){

        this.tpButton = new Button("Teleport Randomly");
        this.tpSafeButton = new Button("Teleport Safely");
        this.waiButton = new Button("Wait for Robots");

        this.footer = new BorderPane();

        this.footer.setLeft(this.tpSafeButton);
        this.footer.setCenter(this.tpButton);
        this.footer.setRight(this.waiButton);
        this.footer.setPadding(new Insets(10));
        this.footer.setStyle("-fx-background-color:#D6DBDF");

        return this.footer;
    }
}
