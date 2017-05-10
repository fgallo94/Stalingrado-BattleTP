package Modelo;


import java.util.ArrayList;

public class Ejercito extends Thread {
    //Clase que contiene una coleccion de Objetos de tipo Soldado, con un respectivo Nombre para poder
    //asignarle un ejercito
    CampoBatalla campoB;
    ArrayList<Soldado> lista;
    String ejercito;

    //Constructor copia
    public Ejercito(ArrayList<Soldado> lista, String ejercito) {
        this.lista = lista;
        this.ejercito = ejercito;
        this.campoB=null;
    }

    //Constructor con nombre de ejercito
    public Ejercito(String ejercito) {
        this.lista = new ArrayList<Soldado>();
        this.ejercito = ejercito;
        this.campoB=null;
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

    public CampoBatalla getCampoB() {
        return campoB;
    }

    public void setCampoB(CampoBatalla campoB) {
        this.campoB = campoB;
    }

    public void run (){
        while(!campoB.isTermino()){
            try {
                campoB.enfrentar(this.ejercito);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
