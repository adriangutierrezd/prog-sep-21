package com.example.prog_sep_2021;

import com.example.prog_sep_2021.bbdd.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowArticlesController implements Initializable {


    @FXML
    ComboBox cb_proveedores;

    @FXML
    TableColumn<InsertArticlesTable, String> cbarra;

    @FXML
    TableColumn<InsertArticlesTable, String> denominacion;

    @FXML
    TableColumn<InsertArticlesTable, String> unidades;

    @FXML
    TableColumn<InsertArticlesTable, String> pcompra;

    @FXML
    TableColumn<InsertArticlesTable, String> pvp;

    @FXML
    TableView<InsertArticlesTable> tableView;


    /**
     * Abre la conexión a la base de datos y carga los proveedores en el ComboBox
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Nos conectamos a la BBDD
        ConnectionDB.openConnection();

        // Cargamos los proveedores en el ComboBox
        cb_proveedores.setItems(FXCollections.observableArrayList(ConnectionDB.Operations.getSuppliers()));
    }


    /**
     * Carga los artículos en el TableView
     */
    @FXML
    public void loadData(){

        cbarra.setCellValueFactory(new PropertyValueFactory<>("cbarra"));
        pcompra.setCellValueFactory(new PropertyValueFactory<>("pcompra"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        unidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        denominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        tableView.setItems(ConnectionDB.Operations.getAllArticles(cb_proveedores.getValue().toString()));

    }

}
