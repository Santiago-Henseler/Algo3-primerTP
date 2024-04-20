package com.visual;

import java.util.ArrayList;
import java.util.Arrays;

import com.Controller.Controlador;
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

    private final Color COLOR_PERSONAJE = Color.RED;
    private final Color COLOR_ROBOT1 = Color.YELLOW;
    private final Color COLOR_ROBOT2 = Color.ORANGE;
    private final Color COLOR_FUEGO = Color.BLACK;

    private Scene escena;
    private VBox fondo;
    private header header;
    private tablero tablero;
    private footer footer;
//    private Rectangle personaje;
    private Stage escenario;
    private ArrayList<Rectangle> personajes;
    private int[] clasificacion_personajes;

    public visual(Stage escenario, vector2D rang){

        this.escenario = escenario;

        this.header = new header();
        this.tablero = new tablero();
        this.footer = new footer();

        this.fondo = new VBox(this.header.getHeader());
        this.fondo.setStyle("-fx-background-color:#D6DBDF");
        this.fondo.getChildren().add(this.tablero.getTablero(rang));
        this.fondo.getChildren().add(this.footer.getfooter());

//        this.personaje = new entidad(new vector2D(rang.getX()/2-1, rang.getY()/2-1), COLOR_PERSONAJE).getEntidad();
        this.personajes = new ArrayList<Rectangle>();

        this.escena = new Scene(this.fondo, 650, 480);
        escenario.setScene(this.escena);
        escenario.setTitle("ROBOTS");
        escenario.setResizable(false);
        escenario.show();
    }

    /*
    public void moverPersonaje(vector2D mov){

        this.tablero.sacarEntidad(this.personaje);

        this.personaje = new entidad(mov, COLOR_PERSONAJE).getEntidad();

        this.tablero.addEntidad(this.personaje);

    }
    */

    /*
    Pre = Lista de posiciones y de enteros valida ( mismo largo e ints correctos )
    Post= Carga personajes en tablero
     */
    public void setPersonajes( ArrayList<vector2D> pos_personajes, int[] clasificacion){
        // Limpiar todos los personajes
        for(Rectangle i: this.personajes){
            this.tablero.sacarEntidad(i);
        }

        // Crear nuevos
        Rectangle personaje;
        Color color = COLOR_PERSONAJE;
        for( int i=0; i < pos_personajes.size(); i++ ){
            if ( clasificacion[i] == Controlador.CODIGO_ROBOT_1){
                color = COLOR_ROBOT1;
            }
            else if ( clasificacion[i] == Controlador.CODIGO_ROBOT_2){
                color = COLOR_ROBOT2;
            }
            else if ( clasificacion[i] == Controlador.CODIGO_FUEGO ){
                color = COLOR_FUEGO;
            }

            personaje = new entidad(pos_personajes.get(i), color).getEntidad();
            this.personajes.add(personaje);
            this.tablero.addEntidad(personaje);
        }

    }

    /*
    public void setRobots(ArrayList<vector2D> posEnemigos){

        for(Rectangle i: this.personajes){
            this.tablero.sacarEntidad(i);
        }

        setEnemigo(posEnemigos, COLOR_ROBOT1);
    }

    public void setFuego(ArrayList<vector2D> posFuego){
        setEnemigo(posFuego, COLOR_FUEGO);
    }

    public void setEnemigo(ArrayList<vector2D> posEnemigos, Color color){
        Rectangle enemigo;

        for(vector2D i: posEnemigos){
            enemigo = new entidad(i, color).getEntidad();
            this.enemigos.add(enemigo);
            this.tablero.addEntidad(enemigo);
        }
    }
    */

    public void redimencionarJuego(vector2D rang){

        this.fondo.getChildren().remove(1);
        this.fondo.getChildren().add(1, this.tablero.getTablero(rang));
        
//        moverPersonaje(new vector2D(rang.getX()/2-1, rang.getY()/2-1));

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
