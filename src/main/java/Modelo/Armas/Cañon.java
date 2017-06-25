package Modelo.Armas;

public class Cañon extends Ataques {
    //Clase que hereda de la clase Padre Ataques, tiene un nombre de ataque
    String nombre;

    //Constructor por defecto, contiene un ataque de valor 15 y un nombre descriptivo
    public Cañon() {
        super(20, "Cañon de disparos");

    }

    //Respectivos Get y Set de cada atributo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
