package Modelo.Defensas;


public class BlindajeTanque extends Defensa {
    //Clase que hereda de la clase Defensa, tiene un nombre de defensa
    String nombre;

    //Constructor por defecto, que contiene una defensa de valor 25 y el nombre del tanque
    public BlindajeTanque() {
        super(25, "Acero antibalas del tanque");

    }

    //Respectivos Get y Set de los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
