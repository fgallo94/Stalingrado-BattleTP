package Dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexion {
    private Connection conn;
    private Statement st;
    private static Conexion instancia;


    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Conexion() {
        try {

            this.verificarDriver();
           

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void conectar() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Stalingrado", "root", "fede432405");
        } catch (SQLException e) {
            System.err.println("SQLexception: " + e.getMessage());
            throw e;
        }
    }

    private void verificarDriver() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            throw e;
        }
    }

    public void desconectar() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Connection getConn() {
        return conn;
    }
}
