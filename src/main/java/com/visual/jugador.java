package com.visual;

import com.model.vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class jugador {

    Rectangle jugador;

    public jugador(vector2D rang){
        this.jugador = new Rectangle(15, 15);
        this.jugador.setX(rang.getX()/2 -1);
        this.jugador.setY(rang.getY()/2 -1);
        this.jugador.setStroke(Color.BLACK);
        this.jugador.setFill(Color.RED);
        
    }

    public Rectangle getEntidad(){
        return this.jugador;
    }
}