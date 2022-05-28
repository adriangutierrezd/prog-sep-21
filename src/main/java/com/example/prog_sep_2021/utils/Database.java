package com.example.prog_sep_2021.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contiene métodos de utilidad para la BBDD
 * @author Adrián Gutiérrez
 */
public class Database {

    /**
     * Indica si un código de barras ya está registrado en la BBDD
     * @param connection Conexión a la BBDD
     * @param cbarra Código de barras a consultar
     * @return true si el código ya existe, false si está libre.
     */
    public static boolean articleExists(Connection connection, String cbarra){
        boolean result = false;

        try{
            String sql = "SELECT cbarra FROM articulos WHERE cbarra = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cbarra);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) result = true;

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }


        return result;
    }

}
