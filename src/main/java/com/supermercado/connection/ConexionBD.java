package com.supermercado.connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    
    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //System.out.println("Conexión exitosa");
            return conexion;
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
