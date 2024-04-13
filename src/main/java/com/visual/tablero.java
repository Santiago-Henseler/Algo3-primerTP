package com.visual;

import com.model.vector2D;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class tablero {

    private GridPane tablero;

    public GridPane getTablero(vector2D dim){
        this.tablero = new GridPane();

        this.tablero.setStyle("-fx-background-color:#E5E7E9");
        this.tablero.setPadding(new Insets(0, 0, 0, 0));

        for(int i = 0; i < dim.getX(); i++){
            for(int j = 0; j < dim.getY(); j++){
                Rectangle rectangle = new Rectangle(15, 15);
                
                if(i%2 == 0)
                    if(j%2 == 0)
                        rectangle.setFill(Color.valueOf("#5499C7"));
                    else
                        rectangle.setFill(Color.valueOf("#D6EAF8"));
                else
                    if(j%2 != 0)
                        rectangle.setFill(Color.valueOf("#5499C7"));
                else
                    rectangle.setFill(Color.valueOf("#D6EAF8"));
               

                this.tablero.add(rectangle, i, j);
            }
        }

        Rectangle rectangle = new Rectangle(10, 10);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.RED);

        this.tablero.setAlignment(Pos.CENTER);

        return this.tablero;
    }

}
