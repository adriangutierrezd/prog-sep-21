package com.example.prog_sep_2021;

import com.example.prog_sep_2021.bbdd.ConnectionDB;
import com.example.prog_sep_2021.utils.Database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InsertArticlesController implements Initializable {


    @FXML
    ComboBox cb_proveedores;

    @FXML
    Text nif_text_field;

    @FXML
    Text direccion_text_field;

    @FXML
    Text poblacion_text_field;

    @FXML
    TextField cod_barras_text_field;

    @FXML
    TextField denominacion_text_field;

    @FXML
    TextField pcompra_text_field;

    @FXML
    TextField pvp_text_field;

    @FXML
    TextField unidades_text_field;

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
    TableColumn<InsertArticlesTable, String> nif;

    @FXML
    TableView<InsertArticlesTable> tableView;

    static Connection connection;


    /**
     * Abre la conexión a la base de datos, carga los proveedores en el ComboBox y los artículos en la tabla
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Nos conectamos a la BBDD
        connection = ConnectionDB.openConnection();

        // Cargamos los proveedores en el ComboBox
        cb_proveedores.setItems(FXCollections.observableArrayList(ConnectionDB.Operations.getSuppliers()));

        loadData();
    }

    /**
     * Este método se ejecuta cuando el usuario selecciona un proveedor del ComboBox
     */
    public void supplierSelected(){
        ArrayList<String> supplierInfo = ConnectionDB.Operations.getSupplierInfo(cb_proveedores.getValue().toString());

        // Seteamos la información del proveedor en los campos
        nif_text_field.setText(supplierInfo.get(0));
        direccion_text_field.setText(supplierInfo.get(2));
        poblacion_text_field.setText(supplierInfo.get(3));
    }

    @FXML
    public void createArticle(){
        if(!Database.articleExists(connection, cod_barras_text_field.getText())){
            ConnectionDB.Operations.insertArticle(
                    cod_barras_text_field.getText(),
                    denominacion_text_field.getText(),
                    Double.valueOf(pcompra_text_field.getText()),
                    Double.valueOf(pvp_text_field.getText()),
                    Integer.valueOf(unidades_text_field.getText()),
                    nif_text_field.getText());
            loadData();
        }
    }


    /**
     * Carga la tabla con los artículos vendidos
     */
    public void loadData(){
        cbarra.setCellValueFactory(new PropertyValueFactory<>("cbarra"));
        pcompra.setCellValueFactory(new PropertyValueFactory<>("pcompra"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        unidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        denominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        tableView.setItems(ConnectionDB.Operations.getAllArticles());
    }

}
