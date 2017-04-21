package Modelo.Armas;

public class Fusil extends Ataques {
    //Clase que hereda de la clase Padre Ataques, tiene un nombre de ataque
    String nombre;

    //Constructor por defecto, contiene un ataque de valor 5 y un nombre descriptivo
    public Fusil() {
        super(5, "Rifle de Guerra");

    }

    //Respectivos Get y Set de cada atributo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
