package Vista;

import Dao.DaoBatalla;
import Modelo.Ejercito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Odin implements Observer {
    DaoBatalla dao= new DaoBatalla();

    public Odin(){
        try {
            ArrayList<String> lista=dao.traerResultados();
            if(lista != null){
                System.out.println("Resultado de batallas anteriores \n\n");
            }
            for (String s: lista) {
                System.out.println(s+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(Observable observable, Object o) {

        if(o instanceof String){
            System.out.println(o);
        }else if(o instanceof Ejercito){
            Ejercito aux = (Ejercito)o;
            if (aux.getEjercito() == "Alemania"){
                try {
                    dao.guardarResultado(aux, 0, 2);
                }catch (Exception z) {
                    z.printStackTrace();
                }
            }else{
                try {
                    dao.guardarResultado(aux, 2, 0);
                }catch (Exception z) {
                    z.printStackTrace();
                }
            }
        }
    }
}
