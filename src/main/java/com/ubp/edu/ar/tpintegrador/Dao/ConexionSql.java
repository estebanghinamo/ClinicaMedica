/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubp.edu.ar.tpintegrador.Dao;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author agustin
 */
@Getter
public final class ConexionSql {
    private static final String URL = "jdbc:mysql://localhost:3307/trabajo_integrador";
    // Usuario de la base de datos
    private static final String USER = "root";
    // Contraseña de la base de datos
    private static final String PASSWORD = "esteban123";

    // Variable estática para la instancia única
    private static ConexionSql instance=null;
    // Variable para la conexión
    private Connection connection=null;


    private ConexionSql() {
        getConnection();
    }

    public static ConexionSql getInstancia() {
        if (instance == null) {
            synchronized (ConexionSql.class) {
                if (instance == null) {
                    instance = new ConexionSql();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return connection;
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = null;
    }

}
