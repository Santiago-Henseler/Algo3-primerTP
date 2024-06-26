package com.visual;

import java.util.ArrayList;
import com.model.ControladorLogico.PERSONAJE;
import com.model.Vector2D;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Visual {

    private final Image SPRITE_PERSONAJE = new Image("file:src/main/java/com/resourses/Personaje.png");
    private final Color COLOR_PERSONAJE = Color.valueOf("#000000");
    private final Image SPRITE_ROBOT1 = new Image("file:src/main/java/com/resourses/Robot1.png");
    private final Color COLOR_ENEMIGO = Color.valueOf("#D35400");
    private final Image SPRITE_ROBOT2 = new Image("file:src/main/java/com/resourses/Robot2.png");
    private final Image SPRITE_FUEGO = new Image("file:src/main/java/com/resourses/Fuego.png");

    private Scene escena;
    private VBox fondo;
    private Header header;
    private Tablero tablero;
    private Footer footer;
    private Stage escenario;
    private ArrayList<Rectangle> personajes;

    public Visual(Stage escenario, Vector2D rang){

        this.escenario = escenario;

        this.header = new Header();
        this.tablero = new Tablero();
        this.footer = new Footer();

        this.fondo = new VBox(this.header.getHeader());
        this.fondo.setStyle("-fx-background-color:#D6DBDF");
        this.fondo.getChildren().add(this.tablero.getTablero(rang));
        this.fondo.getChildren().add(this.footer.getfooter());

        this.personajes = new ArrayList<Rectangle>();

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
    }

    /*
    Pre = Lista de posiciones y de enteros valida ( mismo largo e ints correctos )
    Post= Carga personajes en tablero
     */
    public void setPersonajes( ArrayList<Vector2D> pos_personajes, PERSONAJE[] clasificacion){
        // Limpiar todos los personajes
        for(Rectangle i: this.personajes){
            this.tablero.sacarEntidad(i);
        }

        // Crear nuevos
        for( int i=0; i < pos_personajes.size(); i++ ){
            Image sprite = SPRITE_PERSONAJE;
            Color borde = COLOR_PERSONAJE;
            if (clasificacion[i] == PERSONAJE.ROBOT1){
                sprite = SPRITE_ROBOT1;
                borde = COLOR_ENEMIGO;
            }
            else if (clasificacion[i] == PERSONAJE.ROBOT2){
                sprite = SPRITE_ROBOT2;
                borde = COLOR_ENEMIGO;
            }
            else if (clasificacion[i] == PERSONAJE.FUEGO){
                sprite = SPRITE_FUEGO;
                borde = COLOR_ENEMIGO;
            }
                

            Rectangle personaje = new Entidad(pos_personajes.get(i), sprite, borde).getEntidad();
            this.personajes.add(personaje);
            this.tablero.addEntidad(personaje);
        }
    }

    public void setInfo(int lvl, int score){
        this.header.setLvlScore(lvl, score);
    }

    public void redimensionarJuego(Vector2D rang){

        this.fondo.getChildren().remove(1);
        this.fondo.getChildren().add(1, this.tablero.getTablero(rang));
        
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

    public void onOpcionesClick(EventHandler<ActionEvent> handler) {
        this.header.op1.setOnAction(handler);
        this.header.op2.setOnAction(handler);
        this.header.op3.setOnAction(handler);
        this.header.op4.setOnAction(handler);
        this.header.op5.setOnAction(handler);
    }

}
