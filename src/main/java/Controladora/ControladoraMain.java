package Controladora;


import Modelo.*;
import Modelo.Armas.*;
import Modelo.Defensas.BlindajeTanque;
import Modelo.Defensas.Casco;
import Modelo.Defensas.Chaleco;
import Vista.Odin;
import java.util.ArrayList;
import java.util.Random;

public class ControladoraMain {
    //declaro objetos a utilizar en la controladora
    private Ejercito ejercito1;
    private Ejercito ejercito2;
    private ArrayList<Soldado> ataca;
    private ArrayList<Soldado> defiende;


    public ControladoraMain() {
        //Seteo dos ejercitos que van a enfrentarse luego, se crean vacios y luego se le asignan soldados
        CampoBatalla campoB = new CampoBatalla();
        Odin o = new Odin();
        campoB.addObserver(o);
        ejercito1 = new Ejercito("Aleman");
        ejercito2 = new Ejercito("Ruso");
        campoB.setEjer1(ejercito1);
        campoB.setEjer2(ejercito2);
        ejercito1.setCampoB(campoB);
        ejercito2.setCampoB(campoB);

    }
    public void agregarSoldado(String ejercito,String ataque, String defensa){
        Soldado s = new Soldado();
        if(ataque == "Avion"){
            s.setiAtaque(new Avion());
        }else if(ataque == "Cañon"){
            s.setiAtaque(new Cañon());
        }else if(ataque == "Tanque"){
            s.setiAtaque(new Tanque());
        }else if(ataque== "Fusil"){
            s.setiAtaque(new Fusil());
        }
        if(defensa == "Blindaje Tanque"){
            s.setiDefensa(new BlindajeTanque());
        }else if(defensa=="Casco"){
            s.setiDefensa(new Casco());
        }else if(defensa== "Chaleco"){
            s.setiDefensa(new Chaleco());
        }
        if(ejercito == "Ruso"){
            ejercito2.add(s);
        }else{
            ejercito1.add(s);
        }
    }
    public void play() {
        //Cargo diferentes tipos de soldados con ataques y defensas diferentes
        //para realizar una serie de pruebas
        //Comienza la ejecucion de los Hilos con random basado en los milisegundos del reloj, tratando de hacer aleatorio el ingreso
        //del primer thread
        Random rand = new Random(System.currentTimeMillis());
        if (rand.nextInt() < rand.nextInt() + 100) {
            ejercito1.start();
            ejercito2.start();
        } else {
            ejercito2.start();
            ejercito1.start();
        }
    }
}