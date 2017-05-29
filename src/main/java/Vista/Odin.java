package Vista;

import Dao.DaoBatalla;
import Modelo.Ejercito;
import Modelo.Soldado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Odin implements Observer {
    DaoBatalla dao = new DaoBatalla();
    FormVista f1=new FormVista();
    //Cuando creamos el Observer se muestra por pantalla los resultados de las batallas anteriores, llamando al
    //dao.traerResultados()
    public Odin() {
        try {
            ArrayList<String> lista = dao.traerResultados();
            if (lista != null) {
                System.out.println("Resultado de batallas anteriores \n\n");
                f1.actualizarHistorial(lista);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //En el update del Observer checkea si lo que se envio en el Object o es una instancia de String o de Ejercito, si es
    //instancia de String genera una salida por pantalla en base a ese string, si es una instancia de Ejercito hace una llamada
    //a la base de datos con los datos del ejercito y los resultados de la batalla
    public void update(Observable observable, Object o) {

        if (o instanceof String) {

            try {
                Thread.sleep(500);
            } catch (Exception es) {
                es.printStackTrace();
            }
            System.out.println(o);
        } else if (o instanceof Ejercito) {
            Ejercito aux = (Ejercito) o;
            if (aux.getEjercito() == "Aleman") {
                int acumuladorMuertes = 0;
                try {
                    ArrayList<Soldado> auxSoldado = aux.getLista();
                    for (Soldado s : auxSoldado) {
                        if (s.getEstado() == 0) {
                            acumuladorMuertes += 1;
                        }
                    }

                    dao.guardarResultado(aux, acumuladorMuertes, 2);
                } catch (Exception z) {
                    z.printStackTrace();
                }
            } else {
                try {
                    int acumuladorMuertes = 0;
                    ArrayList<Soldado> auxSoldado = aux.getLista();
                    for (Soldado s : auxSoldado) {
                        if (s.getEstado() == 0) {
                            acumuladorMuertes += 1;
                        }
                    }

                    dao.guardarResultado(aux, 2, acumuladorMuertes);
                } catch (Exception z) {
                    z.printStackTrace();
                }
            }
        }
    }
}

