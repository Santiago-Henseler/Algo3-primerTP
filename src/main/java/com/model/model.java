package com.model;

public class model {

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
