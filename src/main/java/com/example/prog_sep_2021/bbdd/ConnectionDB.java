package com.example.prog_sep_2021.bbdd;


import com.example.prog_sep_2021.InsertArticlesTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

/**
 * Almacena los métodos relacionados con la BBDD
 * @author Adrián Gutiérrez de la Osa
 */
public class ConnectionDB {

    private static Connection connection = null;


    /**
     * Genera la conexión a la base de datos
     * @return Connection
     */
    public static Connection openConnection(){
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/examen_2021_sep",
                    "root",
                    "");
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        return connection;
    }

    /**
     * Cierra la conexión a la base de datos
     */
    public static void closeConnection(){
        try{
            if(connection != null) connection.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }

    /**
     * Contiene las operaciones con la BBDD
     */
    public static class Operations{


        /**
         * Obtiene los nombres de los proveedores
         * @return ArrayList con los nombres
         */
        public static ArrayList<String> getSuppliers(){
            ArrayList<String> result = new ArrayList<>();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT nombre FROM proveedores");

                // Guardamos los resultados en el ArrayList
                while (resultSet.next()) {
                    result.add(resultSet.getString(1));
                }

            }catch(SQLException sqle){
                sqle.printStackTrace();
            }

            return result;
        }

        /**
         * Obtiene los datos de un proveedor a partir de su nombre
         * @param name Nombre del proveedor
         * @return ArrayList con los datos del proveedor
         */
        public static ArrayList<String> getSupplierInfo(String name){
            ArrayList<String> result = new ArrayList<>();
            try{
                String sql = "SELECT * FROM proveedores WHERE nombre = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Guardamos los resultados en el ArrayList
                while (resultSet.next()) {
                    result.add(resultSet.getString(1));
                    result.add(resultSet.getString(2));
                    result.add(resultSet.getString(3));
                    result.add(resultSet.getString(4));
                    result.add(resultSet.getString(5));
                }

            }catch(SQLException sqle){
                sqle.printStackTrace();
            }

            return result;
        }


        /**
         * Inserta un nuevo artículo en la base de datos
         * @param cbarra Código de barras
         * @param denominacion Denominación
         * @param pcompra Precio de compra
         * @param pvp P.V.P
         * @param unidades Unidades
         * @param nif NIF
         */
        public static void insertArticle(String cbarra, String denominacion, Double pcompra, Double pvp, int unidades, String nif){
            try{
                String sql = "INSERT INTO articulos (cbarra, denominacion, pcompra, pvp, unidades, nif) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cbarra);
                preparedStatement.setString(2, denominacion);
                preparedStatement.setDouble(3, pcompra);
                preparedStatement.setDouble(4, pvp);
                preparedStatement.setInt(5, unidades);
                preparedStatement.setString(6, nif);

                preparedStatement.executeUpdate();

            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }

        /**
         * Genera una lista con los objetos para la tabla de artículos comprados
         * @return Objeto con la colección de datos para el TableView
         */
        public static ObservableList<InsertArticlesTable> getAllArticles(){
            ObservableList<InsertArticlesTable> objList = FXCollections.observableArrayList();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM articulos ORDER BY denominacion ASC");
                while(resultSet.next()){
                    objList.add(new InsertArticlesTable(
                            resultSet.getString("cbarra"),
                            resultSet.getString("denominacion"),
                            resultSet.getString("pcompra"),
                            resultSet.getString("pvp"),
                            resultSet.getString("unidades"),
                            resultSet.getString("nif")

                    ));
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }

            return objList;
        }


        /**
         * Genera una lista con los objetos para la tabla de artículos comprados
         * @param name Nombre del proveedor del que se consultan artículos
         * @return Objeto con la colección de datos para el TableView
         */
        public static ObservableList<InsertArticlesTable> getAllArticles(String name){
            ObservableList<InsertArticlesTable> objList = FXCollections.observableArrayList();
            try{
                String sql = "SELECT * FROM articulos WHERE nif = ? ORDER BY denominacion ASC";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Obtenemos el nif del proveedor utilizando un método anterior
                preparedStatement.setString(1, getSupplierInfo(name).get(0));
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    objList.add(new InsertArticlesTable(
                            resultSet.getString("cbarra"),
                            resultSet.getString("denominacion"),
                            resultSet.getString("pcompra"),
                            resultSet.getString("pvp"),
                            resultSet.getString("unidades"),
                            resultSet.getString("nif")
                    ));
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }

            return objList;
        }



    }


}
