package Modelo;


import java.util.ArrayList;

public class Ejercito implements IAtaque,IDefensa {
    //Clase que contiene una coleccion de Objetos de tipo Soldado, con un respectivo Nombre para poder
    //asignarle un ejercito

    ArrayList<Soldado> lista;
    String ejercito;

    //Constructor copia
    public Ejercito(ArrayList<Soldado> lista, String ejercito) {
        this.lista = lista;
        this.ejercito = ejercito;
    }

    //Constructor con nombre de ejercito
    public Ejercito(String ejercito) {
        this.lista = new ArrayList<Soldado>();
        this.ejercito = ejercito;
    }

    //Respectivos Get y Set para cada uno de los atributos de la clase
    public void add(Soldado soldado) {
        this.lista.add(soldado);
    }

    public ArrayList<Soldado> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Soldado> lista) {
        this.lista = lista;
    }

    public String getEjercito() {
        return ejercito;
    }

    public void setEjercito(String ejercito) {
        this.ejercito = ejercito;
    }

    public int atacar() {
        return 0;
    }

    public int defender() {
        return 0;
    }
}
