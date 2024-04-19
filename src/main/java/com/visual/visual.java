package com.visual;

import java.util.ArrayList;

import com.model.vector2D;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class visual {

    private Scene escena;
    private VBox fondo;
    private header header;
    private tablero tablero;
    private footer footer;
    private Rectangle personaje;
    private Stage escenario;
    private ArrayList<Rectangle> enemigos;

    public visual(Stage escenario, vector2D rang){

        this.escenario = escenario;

        this.header = new header();
        this.tablero = new tablero();
        this.footer = new footer();

        this.fondo = new VBox(this.header.getHeader());
        this.fondo.setStyle("-fx-background-color:#D6DBDF");
        this.fondo.getChildren().add(this.tablero.getTablero(rang));
        this.fondo.getChildren().add(this.footer.getfooter());
    
        this.personaje = new entidad(new vector2D(rang.getX()/2-1, rang.getY()/2-1), Color.RED).getEntidad();
        this.enemigos = new ArrayList<Rectangle>();

        this.tablero.addEntidad(this.personaje);

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
    }

    public void moverPersonaje(vector2D mov){

        this.tablero.sacarEntidad(this.personaje);

        this.personaje = new entidad(mov, Color.RED).getEntidad();

        this.tablero.addEntidad(this.personaje);

    }

    public void moverRobots(ArrayList<vector2D> posEnemigos){
        
        for(Rectangle i: this.enemigos){
            this.tablero.sacarEntidad(i);
        }
        
        setRobots(posEnemigos);
    }

    public void setRobots(ArrayList<vector2D> posEnemigos){
        
        for(vector2D i: posEnemigos){
            Rectangle enemigo = new entidad(i, Color.YELLOW).getEntidad();
            this.enemigos.add(enemigo);
            this.tablero.addEntidad(enemigo);
        }
    }
    
    public void redimencionarJuego(vector2D rang){

        this.fondo.getChildren().remove(1);
        this.fondo.getChildren().add(1, this.tablero.getTablero(rang));
        
        moverPersonaje(new vector2D(rang.getX()/2-1, rang.getY()/2-1));

        escenario.setHeight(350 + rang.getY()*10);
    }

    public void onTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.tpButton.setOnAction(handler);
    }

    public void onWaitBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.waitButton.setOnAction(handler);
    }

    public void onSafeTpBtnClick(EventHandler<ActionEvent> handler) {
        this.footer.tpSafeButton.setOnAction(handler);
    }

    public void onTableroClick(EventHandler<MouseEvent> handler) {
        this.tablero.tablero.setOnMouseClicked(handler);
    }

    public void onOpcionesClick(EventHandler<MouseEvent> handler) {
        this.header.opciones.setOnMouseClicked(handler);
    }

}
