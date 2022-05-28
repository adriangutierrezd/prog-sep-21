package com.example.prog_sep_2021;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Administra la pantalla inicial
 */
public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Cambia a la escena de insertar artículos
     * @param event
     * @throws IOException
     */
    public void toSceneInsertArticle(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("insert-articles.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia a la escena de listar artículos
     * @param event
     * @throws IOException
     */
    public void toSceneShowArticle(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("show-articles.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cierra el programa
     */
    public void exit(){
        System.exit(0);
    }

}