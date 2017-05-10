package Controladora;


import Modelo.*;
import Modelo.Armas.*;
import Modelo.Defensas.BlindajeTanque;
import Modelo.Defensas.Casco;
import Modelo.Defensas.Chaleco;
import Modelo.Defensas.Defensa;
import Vista.Odin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ControladoraMain {
    //declaro objetos a utilizar en la controladora
    private Ejercito ejercito1;
    private Ejercito ejercito2;
    private Ataques ataqueAvion;
    private Ataques ataqueFusil;
    private Ataques ataqueTanque;
    private Ataques ataqueCañon;
    private IAtaque ia1;
    private IAtaque ia2;
    private IAtaque ia3;
    private IAtaque ia4;
    private Defensa defensaCasco;
    private Defensa defensaChaleco;
    private Defensa defensaTanque;
    private IDefensa id1;
    private IDefensa id2;
    private IDefensa id3;
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
        //Seteo todos los tipos de ataque disponible en Objetos padres de tipo "Ataques"
        ataqueAvion = new Avion();
        ataqueFusil = new Fusil();
        ataqueTanque = new Tanque();
        ataqueCañon = new Cañon();
        // Asigno Interfaces de ataque a cada tipo de ataques cargados previamente
        ia1 = ataqueAvion;
        ia2 = ataqueFusil;
        ia3 = ataqueTanque;
        ia4 = ataqueCañon;
        //Seteo todos los tipos de defensas disponibles en Objetos padres de tipo "Defensa"
        defensaCasco = new Casco();
        defensaChaleco = new Chaleco();
        defensaTanque = new BlindajeTanque();
        //Asigno interfases de defensa a cada tipo de defensa disponible
        id1 = defensaCasco;
        id2 = defensaChaleco;
        id3 = defensaTanque;
    }

    public void play() {
        //Cargo diferentes tipos de soldados con ataques y defensas diferentes
        //para realizar una serie de pruebas

        Soldado s1 = new Soldado(ia2, id1);  //Tiene ataque con Fusil, se defiende con Casco
        Soldado s2 = new Soldado(ia2, id2); //Tiene ataque con Fusil, se defiende con Chaleco
        Soldado s3 = new Soldado(ia2, id1);  //Tiene ataque con Fusil, se defiende con Casco
        Soldado s4 = new Soldado(ia2, id2);  //Tiene ataque con Fusil, se defiende con Chaleco
        //Agrego soldados a los ejercitos
        ejercito1.add(s1);
        ejercito2.add(s2);
        ejercito2.add(s3);
        ejercito1.add(s4);

        //Comienza la ejecucion de los Hilos con random basado en los milisegundos del reloj, tratando de hacer aleatorio el ingreso
        //del primer thread
        Random rand = new Random(System.currentTimeMillis());

        if(rand.nextInt()<rand.nextInt()+100){
            ejercito1.start();
            ejercito2.start();
        }else{
            ejercito2.start();
            ejercito1.start();
        }
        }

}