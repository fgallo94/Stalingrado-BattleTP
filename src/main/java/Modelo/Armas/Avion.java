package Modelo.Armas;


public class Avion extends Ataques {
    //Clase que hereda de la clase Padre Ataques, tiene un nombre de ataque
    String nombre;

    //Constructor por defecto, contiene un ataque de valor 40 y un nombre descriptivo
    public Avion() {
        super(40, "Avioneta de guerra");

    }

    //Respectivos Get y Set de cada atributo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
