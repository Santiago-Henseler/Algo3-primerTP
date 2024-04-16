package com.model;

public class vector2D {

    private int x;
    private int y;

    public vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public void setVec(vector2D vec){
        this.x = vec.getX();
        this.y = vec.getY();
    }

    public boolean esIgual(vector2D vec){return (vec.getX()-this.x)+(vec.getY()-this.y) == 0;}

    public vector2D distancia(vector2D vec){return vec;}

    public void aniadir(vector2D vec){
        this.x += vec.getX();
        this.y += vec.getY();
    }

    public static vector2D suma(vector2D vec1, vector2D vec2){
        return new vector2D(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }

    public static vector2D resta(vector2D vec1, vector2D vec2){
        return new vector2D(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY());
    }

    public static vector2D reescalarDistancia(vector2D v1, vector2D v2){
        
        vector2D resta = vector2D.resta(v1, v2);

        int x = 0;
        int y = 0;

        if(resta.getX() > 0)x = 1;
        else if(resta.getX() < 0)x = -1;
        else x = 0;
        
        if(resta.getY() > 0)y = 1;
        else if(resta.getY() < 0)y = -1;
        else y = 0;

        return new vector2D(x, y);
    }

}
