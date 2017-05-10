package Modelo;


import java.util.Observable;
import java.util.Random;
//clase que extiende de Observable
public class CampoBatalla extends Observable{
    private Ejercito ejer1;
    private Ejercito ejer2;
    private Ejercito atacanteG;
    private Ejercito defensorG;
    private boolean termino;
    private boolean disponible;


    //Constructor que setea null y condiciones por defecto a los booleans
    public CampoBatalla() {
        ejer1 = null;
        ejer2 = null;
        termino = false;
        disponible = true;
    }
    //Respectivos getters and setters
    public Ejercito getEjer1() {
        return ejer1;
    }

    public void setEjer1(Ejercito ejer1) {
        this.ejer1 = ejer1;
    }

    public Ejercito getEjer2() {
        return ejer2;
    }

    public void setEjer2(Ejercito ejer2) {
        this.ejer2 = ejer2;
    }

    boolean isTermino() {
        return termino;
    }
    //Metodo sincronizado que recibe a los Threads, en caso de que el boolean disponible sea igual a True ingresa y setea
    //ese disponible en false,si ingresa aleman, aleman ataca y defiende rusia, en caso contrario se invierte
    // hace una llamada al metodo atacar, llama al metodo sleepThread y notifica a los demas Threads cuando
    //termina sus tareas
    synchronized void enfrentar(String nombre) throws InterruptedException {
        while (!disponible) {
            wait();
        }
        disponible = false;
        if (nombre.equals(ejer1.getEjercito())) {
            atacanteG = ejer1;
            defensorG = ejer2;
        } else {
            atacanteG = ejer2;
            defensorG = ejer1;

        }
        atacar();
        disponible = true;
        sleepThread();
        notifyAll();
    }

    //Metodo que duerme el Thread de 2 seg a 1
    private synchronized void sleepThread(){
        Random rand = new Random();
        int rando = rand.nextInt(2000) + 1000;
                try{
                    Thread.sleep(rando);
                }catch(Exception e){
                    e.printStackTrace();
                }
    }

    //Metodo atacar, compara si la batalla no finalizo para hacer sus respectivas tareas, si no finalizo, trae el ataque del agresot
    //la defensa del defensor, hace llamada a saltoLinea(), llama al metodo mostrarAtaqueTurno() que nos da resultado parcial de la batalla,
    //guarda en un int el daño del agresor y llama a resultadoBatallaSinRemover() enviando el defensor y los puntos de daño
    // para finalizar llama mostrarContadorFinal() que nos dice como va la batalla y si finalizo
    private synchronized void atacar() {
    if (!termino) {
        int ataque = traerAtaque();
        int defensa = traerDefensa();
        saltoLinea();
        mostrarAtaqueTurno(ataque, defensa);
        int dañoCausado = ataque - defensa;
        resultadoBatallaSinRemover(defensorG, dañoCausado);
        mostrarContadorFinal();
        }
    }
    //Metodo para traer ataque del atacante, tiene ademas salida por pantalla mediante notifyObservers(), trae el ataque de
    //cada uno de los soldados de nuestro ejercito, y retorna ese acumulador
    private synchronized int traerAtaque() {
        int acumuladorAtaque = 0;
        setChanged();
        notifyObservers(" "+atacanteG.getEjercito()+"\n");
        for (Soldado s : atacanteG.getLista()) {
            if (s.getEstado() != 0) {
                acumuladorAtaque += s.getiAtaque();
                setChanged();
                notifyObservers("\n |||||||||||\n");
            }
        }
        if(atacanteG.getEjercito().equals("Aleman")){
            setChanged();
            notifyObservers("BONUS DE ATAQUE POR SER NAZI Y COMER LEVERWURST \n");
            return acumuladorAtaque+5;
        }
        return acumuladorAtaque;
    }
    //Metodo para traer defensa del defensor, tiene ademas salida por pantalla mediante el notifyObservers(), trae la defensa
    //de cada uno de los soldados de nuestro ejercito y retorna el acumulador
    private synchronized int traerDefensa() {
        int acumuladorDefensa = 0;
        setChanged();
        notifyObservers(" "+defensorG.getEjercito()+"\n");
        for (Soldado s : defensorG.getLista()) {
            if (s.getEstado() != 0) {
                acumuladorDefensa += s.getiDefensa();
                setChanged();
                notifyObservers("\n |||||||||||\n");
            }
        }
        if(defensorG.getEjercito().equals("Ruso")){
            setChanged();
            notifyObservers("BONUS DE DEFENSA POR SER RUSO Y TOMAR VODKA \n");
            return acumuladorDefensa+5;
        }
        return acumuladorDefensa;
    }

    //Salida por pantalla de los ataques y defensas

    private synchronized void mostrarAtaqueTurno(int acumA, int acumD) {

        setChanged();
        notifyObservers(" "+atacanteG.getEjercito()+"\n");
        setChanged();
        notifyObservers("\n El ataque del ejercito "+atacanteG.getEjercito()+  "fue de "+ acumA);
        setChanged();
        notifyObservers("\n La defensa del otro ejercito fue de "+ acumD);

    }

    //Metodo que decide como sigue la batalla, en caso de que ambos mueran, o alguno de ellos muera, se muestra
    //quien gano y los contadores de las muertes de cada uno de ellos
    private void mostrarContadorFinal() {
        int acumuladorMuertes1 = 0;
        int acumuladorVivos1 = 0;
        for (Soldado s : ejer1.getLista()) {
            if (s.getEstado() == 0) {
                acumuladorMuertes1++;
            } else {
                acumuladorVivos1++;
            }
        }

        int acumuladorMuertes2 = 0;
        int acumuladorVivos2 = 0;
        for (Soldado s : ejer2.getLista()) {
            if (s.getEstado() == 0) {
                acumuladorMuertes2++;
            } else {
                acumuladorVivos2++;
            }
        }

        if ((acumuladorVivos1 == 0) && (acumuladorVivos2 == 0)) {
            setChanged();
            notifyObservers("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
            setChanged();
            notifyObservers("\nAmbos ejercitos murieron en batalla\n");
            setChanged();
            notifyObservers("\n Contador:\n");
            setChanged();
            notifyObservers("\n Ejercito "+ejer1.getEjercito()+ " \n Muertes: "+acumuladorMuertes1 +" \n Vivos: "+acumuladorVivos1+" \n" );
            setChanged();
            notifyObservers("\n Ejercito "+ejer2.getEjercito()+ " \n Muertes: "+acumuladorMuertes2+" \n Vivos: "+acumuladorVivos2+"\n" );
            this.termino=true;
        } else if (acumuladorVivos1 == 0) {
            setChanged();
            notifyObservers("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
            setChanged();
            notifyObservers("\n Gano el ejercito "+ ejer2.getEjercito());
            setChanged();
            notifyObservers("\n Contador:\n");
            setChanged();
            notifyObservers("\n Ejercito "+ejer1.getEjercito()+ " \n Muertes: "+acumuladorMuertes1 +" \n Vivos: "+acumuladorVivos1+" \n" );
            setChanged();
            notifyObservers("\n Ejercito "+ejer2.getEjercito()+ " \n Muertes: "+acumuladorMuertes2+" \n Vivos: "+acumuladorVivos2+"\n" );
            this.termino=true;
            setChanged();
            notifyObservers(ejer2);

        } else if (acumuladorVivos2 == 0) {
            setChanged();
            notifyObservers("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
            setChanged();
            notifyObservers("\n Gano el ejercito "+ ejer1.getEjercito());
            setChanged();
            notifyObservers("\n Contador:\n");
            setChanged();
            notifyObservers("\n Ejercito "+ejer1.getEjercito()+ " \n Muertes: "+acumuladorMuertes1 +" \n Vivos: "+acumuladorVivos1+" \n" );
            setChanged();
            notifyObservers("\n Ejercito "+ejer2.getEjercito()+ " \n Muertes: "+acumuladorMuertes2+" \n Vivos: "+acumuladorVivos2+"\n" );
            this.termino = true;
            setChanged();
            notifyObservers(ejer1);

        } else {
            setChanged();
            notifyObservers("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
            setChanged();
            notifyObservers("\n La batalla continua");
            setChanged();
            notifyObservers("\n Contador:\n");
            setChanged();
            notifyObservers("\n Ejercito "+ejer1.getEjercito()+ " \n Muertes: "+acumuladorMuertes1 +" \n Vivos: "+acumuladorVivos1+" \n" );
            setChanged();
            notifyObservers("\n Ejercito "+ejer2.getEjercito()+ " \n Muertes: "+acumuladorMuertes2+" \n Vivos: "+acumuladorVivos2+"\n" );


        }
    }

    //Funcion que crea espacios en la consola, meramente visual
    private void saltoLinea() {
        setChanged();
        notifyObservers("\n");
        setChanged();
        notifyObservers("\n");
        setChanged();
        notifyObservers("\n");
        setChanged();
        notifyObservers("\n");
    }

    //Recibe como parametro un ejercito y el daño que recibio, crea una lista auxiliar, utiliza acumulador de muertes
    //y de heridos, recorre en la lista, si el dañoTotal sigue siendo superior a 0 realiza una accion correspondiente
    //en caso de ser mayor de 10 significa que nuestro soldado murio, se remueve de la lista y se le resta 10 al acumulador
    //para acercar la condicion de corte y suma 1 el acumuladorMuertes, en caso de que el daño sea superior a 0 e inferior a 10 quiere decir
    //que nuestro soldado resulto herido, ahora ese soldado tiene menos vida y se descuenta del dañoTotal, ademas
    //suma 1 en acumuladorDañados

    //Luego muestra por pantalla en caso de que exista

    private void resultadoBatallaSinRemover(Ejercito e, int dañoTotal) {
        int acumuladorMuertes = 0;
        int acumuladorDañados = 0;
        for (Soldado s : e.getLista()) {
            if (dañoTotal > 0) {
                if (dañoTotal >= 10) {
                    dañoTotal -= s.getVida();
                    s.setEstado(0);
                    acumuladorMuertes++;
                } else {
                    s.setVida((s.getVida() - dañoTotal));
                    if (s.getVida() <= 0) {
                        s.setEstado(0);
                        acumuladorMuertes++;
                    } else {
                        acumuladorDañados++;
                    }
                    dañoTotal -= s.getVida();

                }
            }
        }
        if (acumuladorMuertes != 0) {
            setChanged();
            notifyObservers("\n Muertes ejercito "+e.getEjercito()+": "+ acumuladorMuertes+ " \n" );
        }
        if (acumuladorDañados != 0) {
            setChanged();
            notifyObservers("\n Heridos ejercito "+e.getEjercito()+ " : "+acumuladorDañados+ "\n" );
        }
    }

}


