package com.visual;

import com.model.Vector2D;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Entidad {

    Rectangle entidad;

    public Entidad(Vector2D rang, Image img, Color borde){
        this.entidad = new Rectangle(15, 15);
        this.entidad.setX(rang.getX());
        this.entidad.setY(rang.getY());
        this.entidad.setStroke(borde);
        this.entidad.setFill(new ImagePattern(img));
    }

    public Rectangle getEntidad(){
        return this.entidad;
    }
}