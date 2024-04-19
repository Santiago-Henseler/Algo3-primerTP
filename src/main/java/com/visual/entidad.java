package com.visual;

import com.model.vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class entidad {

    Rectangle entidad;

    public entidad(vector2D rang, Color color){
        this.entidad = new Rectangle(15, 15);
        this.entidad.setX(rang.getX());
        this.entidad.setY(rang.getY());
        this.entidad.setStroke(Color.BLACK);
        this.entidad.setFill(color);
    }

    public Rectangle getEntidad(){
        return this.entidad;
    }
}