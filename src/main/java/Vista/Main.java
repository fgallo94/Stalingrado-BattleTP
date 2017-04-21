package Vista;


import Modelo.Armas.*;
import Modelo.Defensas.BlindajeTanque;
import Modelo.Defensas.Casco;
import Modelo.Defensas.Chaleco;
import Modelo.Defensas.Defensa;
import Modelo.IAtaque;
import Modelo.IDefensa;
import Modelo.Soldado;

public class Main {

    public static void main(String[] args) {
        //Seteo todos los tipos de ataque disponible en Objetos padres de tipo "Ataques"
        Ataques ataqueAvion = new Avion();
        Ataques ataqueFusil = new Fusil();
        Ataques ataqueTanque = new Tanque();
        Ataques ataqueCañon = new Cañon();
        // Asigno Interfaces de ataque a cada tipo de ataques cargados previamente
        IAtaque ia1 = ataqueAvion;
        IAtaque ia2 = ataqueFusil;
        IAtaque ia3 = ataqueTanque;
        IAtaque ia4 = ataqueCañon;
        //Seteo todos los tipos de defensas disponibles en Objetos padres de tipo "Defensa"
        Defensa defensaCasco = new Casco();
        Defensa defensaChaleco = new Chaleco();
        Defensa defensaTanque = new BlindajeTanque();
        //Asigno interfases de defensa a cada tipo de defensa disponible
        IDefensa id1 = defensaCasco;
        IDefensa id2 = defensaChaleco;
        IDefensa id3 = defensaTanque;
        //Cargo diferentes tipos de soldados con ataques y defensas diferentes
        //para realizar una serie de pruebas
        Soldado s1 = new Soldado(ia1, id1);  //Tiene ataque de Avion, se defiende con Casco
        Soldado s2 = new Soldado(ia2, id2); //Tiene ataque con Fusil, se defiende con Chaleco
        Soldado s3 = new Soldado(ia2, id1);  //Tiene ataque con Fusil, se defiende con Casco

    }
}
