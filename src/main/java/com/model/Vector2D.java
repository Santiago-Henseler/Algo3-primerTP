package com.model;

public class Vector2D {

    private int x;
    private int y;

    public Vector2D(int x, int y){this.x = x;this.y = y;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public void setVec(Vector2D vec){this.x = vec.getX(); this.y = vec.getY();}

    public boolean esIgual(Vector2D vec){return (vec.getX() == this.x) && (vec.getY() == this.y);}

    public Vector2D distancia(Vector2D vec){return vec;}

    public void aniadir(Vector2D vec){this.x += vec.getX(); this.y += vec.getY();}

    public static Vector2D suma(Vector2D vec1, Vector2D vec2){return new Vector2D(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());}

    public static Vector2D resta(Vector2D vec1, Vector2D vec2){return new Vector2D(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY());}

    public static Vector2D reescalarDistancia(Vector2D v1, Vector2D v2){
        
        Vector2D resta = Vector2D.resta(v1, v2);

        int x = 0;int y = 0;

        if(resta.getX() > 0)x = 1;
        else if(resta.getX() < 0)x = -1;
        else x = 0;
        
        if(resta.getY() > 0)y = 1;
        else if(resta.getY() < 0)y = -1;
        else y = 0;

        return new Vector2D(x, y);
    }

}
