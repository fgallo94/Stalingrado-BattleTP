package Modelo.Armas;


public class Tanque extends Ataques {
    //Clase que hereda de la clase Padre Ataques, tiene un nombre de ataque
    String nombre;

    //Constructor por defecto, contiene un ataque de valor 25 y un nombre descriptivo
    public Tanque() {
        super(25, "Panzer");
    }

    //Respectivos Get y Set de cada atributo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
