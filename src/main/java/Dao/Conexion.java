package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    //Contiene un objeto de tipo connection que viene de jdbc y una instancia estatica de nuestra clase
    private Connection conn;
    private static Conexion instancia;

    //Patron Singleton
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    //Verifica los drivers en la conexion
    public Conexion() {
        try {

            this.verificarDriver();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Conecta la base de datos con getConnection y los datos de nuestra base de datos, en caso de no poder ejectura la Excepcion
    public void conectar() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Stalingrado", "root", "fede432405");
        } catch (SQLException e) {
            System.err.println("SQLexception: " + e.getMessage());
            throw e;
        }
    }

    //verifica con el class.forName si el driver es el correcto
    private void verificarDriver() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            throw e;
        }
    }

    //Intenta desconectar la base de datos, en caso de no poder arroja excepcion
    public void desconectar() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //get que retorna la conexion
    public Connection getConn() {
        return conn;
    }
}
