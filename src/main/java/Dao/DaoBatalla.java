package Dao;


import Modelo.Ejercito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoBatalla {

        private Conexion conn = Conexion.getInstancia();

        public void guardarResultado (Ejercito e,int muertesAlemania,int muertesRusia) throws Exception{
            String sq = "insert into Logs(descripcion,muertes_alemania,muertes_rusia) values (?,?,?)";
            String ej= ("Gano "+e.getEjercito());

            try{
                conn.conectar();
                PreparedStatement st = conn.getConn().prepareStatement(sq);
                st.setString(1,ej);
                st.setInt(2,muertesAlemania);
                st.setInt(3,muertesRusia);
                st.executeUpdate();

            }catch (SQLException es){
                es.printStackTrace();
            }finally{
                try {
                    conn.desconectar();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }

        public ArrayList<String> traerResultados() throws Exception{
            ArrayList<String> lista= new ArrayList<String>();
            String sq= "select * from Logs";
            try{
                conn.conectar();
                PreparedStatement st=conn.getConn().prepareStatement(sq);
                ResultSet rs= st.executeQuery();
                if(rs == null){
                    System.out.println(" No hay registros en la base de datos");
                }
                while(rs.next()){
                    String s= new String(rs.getString("descripcion")+" Muertes Alemania "+ rs.getInt("muertes_alemania")+ " Muertes Rusia "+rs.getInt("muertes_rusia")+"   "+rs.getDate("fecha"));
                    lista.add(s);
                }
            }catch(SQLException a){
                a.printStackTrace();
            }finally {
                try{
                    conn.desconectar();
                }catch (Exception b){
                    b.printStackTrace();
                }
            }
            return lista;
        }


    }
