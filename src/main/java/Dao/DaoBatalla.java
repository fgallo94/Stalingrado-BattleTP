package Dao;


import Modelo.Ejercito;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoBatalla {
    //Genera una instancia de la clase Conexion
    private Conexion conn = Conexion.getInstancia();

    //Metodo para guardar resultado de la batalla, recibe ejercito, contador de muertes de cada lado,
    //Se genera una query para insertar dejando los values con signo de pregunta para prevenir la injeccion SQL
    // a la vez se genera otro string auxiliar para ser mas prolijo :) en base a ejercito.nombre
    public void guardarResultado(Ejercito e, int muertesAlemania, int muertesRusia) throws Exception {
        String sq = "insert into Logs(descripcion,muertes_alemania,muertes_rusia) values (?,?,?)";
        String ej = ("Gano " + e.getEjercito());
        //Se genera un objeto de tipo PreparedStatement que proviene de Connection, se setea cada ? con valores
        // que sean necesarios, el primero con ejercito, el segundo con muertes alemania, el tercero con muertes rusia
        //se ejecuta el update de la query
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setString(1, ej);
            st.setInt(2, muertesAlemania);
            st.setInt(3, muertesRusia);
            st.executeUpdate();

        }
        //Se ejecuta excepcion en caso de error
        catch (SQLException es) {
            es.printStackTrace();
        }
        //con clausula finally nos aseguramos que se cierre la conexion a la base de datos
        finally {
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    //Se genera un metodo para traer los resultados de las batallas, no recibe parametros, se genera un ArrayList de String
    //que va a ser nuestro retorno del metodo, se genera la Query que luego pasaremos al Prepared statement, un simple Select
    public ArrayList<String> traerResultados() throws Exception {
        ArrayList<String> lista = new ArrayList<String>();
        String sq = "select * from Logs";

        //Conecta con la base de datos, genera un preparedstatement para ejecutar la query, se declara un ResultSet que es
        //una clase que nos permite obtener las Rows afectadas por la consulta a la base de datos,
        //una vez obtenidas las Rows chequeamos que no sea null, y si no es null la recorremos y vamos generando un
        //String por cada Row que contiene los datos de nuestra base de datos, y los agregamos a la ArrayList que luego
        //retornamos
        try {
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            }
            while (rs.next()) {
                String s = new String(rs.getString("descripcion") + " Muertes Alemania " + rs.getInt("muertes_alemania") + " Muertes Rusia " + rs.getInt("muertes_rusia") + "   " + rs.getDate("fecha"));
                lista.add(s);
            }
        } catch (SQLException a) {
            a.printStackTrace();
        }
        //Con clausula finally se asegura que la base de datos se cierre
        finally {
            try {
                conn.desconectar();
            } catch (Exception b) {
                b.printStackTrace();
            }
        }
        return lista;
    }


}
