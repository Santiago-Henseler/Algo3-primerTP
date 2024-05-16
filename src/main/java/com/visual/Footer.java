package com.visual;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Footer {

    Button tpButton;
    Button tpSafeButton;
    Button waitButton;
    Label nivel;
    BorderPane footer;

    public BorderPane getfooter(){

        this.tpButton = new Button("Teleport Randomly");
        this.tpSafeButton = new Button("Teleport Safely");
        this.waitButton = new Button("Wait for Robots");
        this.tpButton.setFont(Font.font("Uroob", 20));
        this.tpSafeButton.setFont(Font.font("Uroob", 20));
        this.waitButton.setFont(Font.font("Uroob", 20));

        this.footer = new BorderPane();

        this.footer.setLeft(this.tpSafeButton);
        this.footer.setCenter(this.tpButton);
        this.footer.setRight(this.waitButton);
        this.footer.setStyle("-fx-background-color:#D6DBDF");
        this.footer.setPadding(new Insets(40));

        return this.footer;
    }
}
