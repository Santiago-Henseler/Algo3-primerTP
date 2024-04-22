package com.visual;

import com.model.vector2D;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class entidad {

    Rectangle entidad;

    public entidad(vector2D rang, Image img){
        this.entidad = new Rectangle(15, 15);
        this.entidad.setX(rang.getX());
        this.entidad.setY(rang.getY());
        this.entidad.setStroke(Color.BLACK);
        this.entidad.setFill(new ImagePattern(img));
    }

    public Rectangle getEntidad(){
        return this.entidad;
    }
}