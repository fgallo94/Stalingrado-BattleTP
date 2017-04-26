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
import java.util.Iterator;

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

    private void enfrentar() {

        //Traigo el ataque y la defensa total de cada uno de los soldados en el ejercito1 y los acumulo
        ataca = ejercito1.getLista();
        int acumuladorAtaque1 = 0;
        int acumuladorDefensa1 = 0;
        System.out.printf("\n Alemanes \n");
        for (Soldado s : ataca) {
            acumuladorAtaque1 += s.getiAtaque();
            acumuladorDefensa1 += s.getiDefensa();
            System.out.printf("\n||||||||||");
        }


        //Traigo el ataque y la defensa total de cada uno de los soldados en el ejercito2 y los acumulo
        defiende = ejercito2.getLista();
        int acumuladorAtaque2 = 0;
        int acumuladorDefensa2 = 0;
        System.out.printf("\n Rusos \n");
        for (Soldado s : defiende) {
            acumuladorAtaque2 += s.getiAtaque();
            acumuladorDefensa2 += s.getiDefensa();
            System.out.printf("\n|||||||||||");
        }
        //funcion que crea espacios en la consola

        saltoLinea();


        //Salida por pantalla de los ataques y defensas
        System.out.printf("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
        System.out.printf("\n ########     ALEMANES       #########");
        System.out.printf("\n El ataque del ejercito %s fue de %d", ejercito1.getEjercito(), acumuladorAtaque1);
        System.out.printf("\nLa defensa del ejercito %s fue de %d", ejercito1.getEjercito(), acumuladorDefensa1);
        System.out.printf("\n ########     RUSOS       #########");
        System.out.printf("\nEl ataque del ejercito %s fue de %d", ejercito2.getEjercito(), acumuladorAtaque2);
        System.out.printf("\nLa defensa del ejercito %s fue de %d", ejercito1.getEjercito(), acumuladorDefensa2);


        int dañoCausado1 = acumuladorAtaque1 - acumuladorDefensa2; //daño causado de alemania
        int dañoCausado2 = acumuladorAtaque2 - acumuladorDefensa1; //daño causado de rusia

        //Llamada a funciones que imprimen resultados de bajas como ademas eliminan de las listas
        // los soldados muertos
        resultadoBatalla(ejercito1, dañoCausado2);
        resultadoBatalla(ejercito2, dañoCausado1);

        //Condicional para saber como continua nuestra batalla
        if ((ejercito1.getLista().size()==0) && (ejercito2.getLista().size()==0)) {
            System.out.printf("\n AMBOS EJERCITOS MURIERON, FUE UN EMPATE");
        } else if (ejercito1.getLista().size()==0) {
            System.out.printf("\n GANO EL EJERCITO %s", ejercito2.getEjercito());
        } else if (ejercito2.getLista().size()==0) {
            System.out.printf("\n GANO EL EJERCITO %s", ejercito1.getEjercito());
        } else {
            enfrentar(); //llamada recursiva en caso de que ambos ejercitos sigan con vida
        }

    }


    //Funcion que crea espacios en la consola, meramente visual
    private void saltoLinea() {
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
    }

    //Recibe como parametro un ejercito y el daño que recibio, crea una lista auxiliar, utiliza acumulador de muertes
    //y de heridos, recorre en la lista, si el dañoTotal sigue siendo superior a 0 realiza una accion correspondiente
    //en caso de ser mayor de 10 significa que nuestro soldado murio, se remueve de la lista y se le resta 10 al acumulador
    //para acercar la condicion de corte y suma 1 el acumuladorMuertes, en caso de que el daño sea superior a 0 e inferior a 10 quiere decir
    //que nuestro soldado resulto herido, ahora ese soldado tiene menos vida y se descuenta del dañoTotal, ademas
    //suma 1 en acumuladorDañados

    //Luego muestra por pantalla en caso de que exista
    private void resultadoBatalla(Ejercito e, int dañoTotal) {
        ArrayList<Soldado> lista = e.getLista();
        int acumuladorMuertes = 0;
        int acumuladorDañados = 0;
        Iterator iterator = e.getLista().iterator();
        while (iterator.hasNext()) {
            if (dañoTotal > 0) {
                if (dañoTotal >= 10) {
                    Soldado s = (Soldado) iterator.next();
                    dañoTotal -= s.getVida();
                    iterator.remove();
                    acumuladorMuertes++;
                } else {
                    Soldado s = (Soldado) iterator.next();
                    s.setVida((s.getVida() - dañoTotal));
                    dañoTotal -= s.getVida();
                    acumuladorDañados++;
                }
            }
        }
        e.setLista(lista);
        if (acumuladorMuertes != 0) {
            System.out.printf("\n Muertes ejercito %s: %d  \n", e.getEjercito(), acumuladorMuertes);
        }
        if (acumuladorDañados != 0) {
            System.out.printf("\n Heridos ejercito %s: %d \n", e.getEjercito(), acumuladorDañados);
        }
    }

}
