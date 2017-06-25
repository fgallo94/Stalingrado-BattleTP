package Controladora;


import Modelo.*;
import Modelo.Armas.*;
import Modelo.Defensas.BlindajeTanque;
import Modelo.Defensas.Casco;
import Modelo.Defensas.Chaleco;
import Vista.Odin;


import java.util.Random;

public class ControladoraMain {
    //declaro objetos a utilizar en la controladora
    private Ejercito ejercito1;
    private Ejercito ejercito2;

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

    public void play() {
        //Cargo diferentes tipos de soldados con ataques y defensas diferentes
        //para realizar una serie de pruebas
        cargarEjercitos();

        //Comienza la ejecucion de los Hilos
        ejercito1.start();
        ejercito2.start();

    }

    public void cargarEjercitos() {
        Soldado s1 = new Soldado(new Fusil(), new Casco());  //Tiene ataque con Fusil, se defiende con Casco
        Soldado s2 = new Soldado(new Cañon(), new Chaleco()); //Tiene ataque con Fusil, se defiende con Chaleco
        Soldado s3 = new Soldado(new Fusil(), new Casco());  //Tiene ataque con Fusil, se defiende con Casco
        Soldado s4 = new Soldado(new Cañon(), new Chaleco());  //Tiene ataque con Fusil, se defiende con Chaleco
        Soldado s5 = new Soldado(new Tanque(), new BlindajeTanque());
        Soldado s6 = new Soldado(new Avion(), new BlindajeTanque());
        Soldado s7 = new Soldado(new Tanque(), new BlindajeTanque());
        Soldado s8 = new Soldado(new Avion(), new BlindajeTanque());

        //Agrego soldados a los ejercitos
        ejercito1.add(s1);
        ejercito1.add(s4);
        ejercito1.add(s5);
        ejercito1.add(s6);

        ejercito2.add(s7);
        ejercito2.add(s8);
        ejercito2.add(s2);
        ejercito2.add(s3);


    }
}