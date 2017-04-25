package Controladora;


import Modelo.Armas.*;
import Modelo.Defensas.BlindajeTanque;
import Modelo.Defensas.Casco;
import Modelo.Defensas.Chaleco;
import Modelo.Defensas.Defensa;
import Modelo.Ejercito;
import Modelo.IAtaque;
import Modelo.IDefensa;
import Modelo.Soldado;

import java.util.ArrayList;

public class ControladoraMain {
    //declaro objetos a utilizar en la controladora
    Ejercito ejercito1;
    Ejercito ejercito2;
    Ataques ataqueAvion;
    Ataques ataqueFusil;
    Ataques ataqueTanque;
    Ataques ataqueCañon;
    IAtaque ia1;
    IAtaque ia2;
    IAtaque ia3;
    IAtaque ia4;
    Defensa defensaCasco;
    Defensa defensaChaleco;
    Defensa defensaTanque;
    IDefensa id1;
    IDefensa id2;
    IDefensa id3;
    ArrayList<Soldado> ataca;
    ArrayList<Soldado> defiende;


    public ControladoraMain() {
        //Seteo dos ejercitos que van a enfrentarse luego, se crean vacios y luego se le asignan soldados
        ejercito1 = new Ejercito("Aleman");
        ejercito2 = new Ejercito("Ruso");
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
        Soldado s1 = new Soldado(ia1, id1);  //Tiene ataque de Avion, se defiende con Casco
        Soldado s2 = new Soldado(ia2, id2); //Tiene ataque con Fusil, se defiende con Chaleco
        Soldado s3 = new Soldado(ia2, id1);  //Tiene ataque con Fusil, se defiende con Casco
       //Agrego soldados a los ejercitos
        ejercito1.add(s1);
        ejercito2.add(s2);
        ejercito2.add(s3);
        enfrentar();
    }

    private void enfrentar(){


       ataca= ejercito1.getLista();
       int acumuladorAtaque1=0;
       int acumuladorDefensa1=0;
        System.out.printf("\n Alemanes \n");
        for (Soldado s: ataca) {
            acumuladorAtaque1+=s.getiAtaque();
            acumuladorDefensa1+=s.getiDefensa();
            System.out.printf("\n||||||||||");
        }

       defiende= ejercito2.getLista();
        int acumuladorAtaque2=0;
        int acumuladorDefensa2=0;

        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n Rusos \n");
        for (Soldado s: defiende) {
            acumuladorAtaque2+=s.getiAtaque();
            acumuladorDefensa2+=s.getiDefensa();
            System.out.printf("\n|||||||||||");
        }
        System.out.printf("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
        System.out.printf("\n ########     ALEMANES       #########");
        System.out.printf("\n El ataque del ejercito %s fue de %d",ejercito1.getEjercito(),acumuladorAtaque1);
        System.out.printf("\nLa defensa del ejercito %s fue de %d",ejercito1.getEjercito(),acumuladorDefensa1);
        System.out.printf("\n ########     RUSOS       #########");
        System.out.printf("\nEl ataque del ejercito %s fue de %d",ejercito2.getEjercito(),acumuladorAtaque2);
        System.out.printf("\nLa defensa del ejercito %s fue de %d",ejercito1.getEjercito(),acumuladorDefensa2);





    }

}
