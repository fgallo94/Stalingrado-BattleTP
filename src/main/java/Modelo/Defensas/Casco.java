package Modelo.Defensas;


public class Casco extends Defensa {
    //Clase que hereda de la clase Defensa, tiene un nombre de defensa
    String nombre;

    //Constructor por defecto, que contiene una defensa de valor 5 y el nombre del casco
    public Casco() {
        super(5, "Casco de Guerra");

    }

    //Respectivo Get y Set de los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
