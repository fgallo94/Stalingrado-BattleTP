package Modelo;


public class CampoBatalla {
    private Ejercito ejer1;
    private Ejercito ejer2;
    private Ejercito atacanteG;
    private Ejercito defensorG;
    private boolean termino;
    private boolean disponible;

    public CampoBatalla() {
        ejer1 = null;
        ejer2 = null;
        termino = false;
        disponible = true;
    }

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

    synchronized void enfrentar(String nombre) throws InterruptedException {
        while (!disponible) {
            wait();
        }
        disponible = false;
        if (nombre.equals(ejer1.getEjercito())) {
            System.out.printf("entro aleman");
            atacanteG = ejer1;
            defensorG = ejer2;
        } else {
            System.out.printf("entro ruso");
            atacanteG = ejer2;
            defensorG = ejer1;

        }
        atacar();
        disponible = true;
        notifyAll();
    }

    private synchronized void atacar() {

        int ataque = traerAtaque();
        int defensa = traerDefensa();
        saltoLinea();
        System.out.printf("\n@@@@@@@@@@ RESULTADOS @@@@@@@@@@@ ");
        mostrarAtaqueTurno(ataque, defensa);
        int dañoCausado = ataque - defensa;
        resultadoBatallaSinRemover(defensorG, dañoCausado);
        mostrarContadorFinal();

    }

    private synchronized int traerAtaque() {
        int acumuladorAtaque = 0;
        System.out.printf("\n %s \n", atacanteG.getEjercito());
        for (Soldado s : atacanteG.getLista()) {
            if (s.getEstado() != 0) {
                acumuladorAtaque += s.getiAtaque();
                System.out.printf("\n |||||||||||\n");
            }
        }
        return acumuladorAtaque;
    }

    private synchronized int traerDefensa() {
        int acumuladorDefensa = 0;
        System.out.printf("\n %s \n", defensorG.getEjercito());
        for (Soldado s : defensorG.getLista()) {
            if (s.getEstado() != 0) {
                acumuladorDefensa += s.getiDefensa();
                System.out.printf("\n |||||||||||\n");
            }
        }
        return acumuladorDefensa;
    }


    private synchronized void mostrarAtaqueTurno(int acumA, int acumD) {

        //Salida por pantalla de los ataques y defensas

        System.out.printf("\n ########     %s       #########", atacanteG.getEjercito());
        System.out.printf("\n El ataque del ejercito %s fue de %d", atacanteG.getEjercito(), acumA);
        System.out.printf("\nLa defensa del otro ejercito fue de %d", acumD);

    }

    //Funcion que decide como sigue la batalla, en caso de que ambos mueran, o alguno de ellos muera, se muestra
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
            System.out.printf("\nAmbos ejercitos murieron en batalla\n");
            System.out.printf("\n Contador:\n");
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer1.getEjercito(), acumuladorMuertes1, acumuladorVivos1);
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer2.getEjercito(), acumuladorMuertes2, acumuladorVivos2);
            this.termino=true;
        } else if (acumuladorVivos1 == 0) {
            System.out.printf("\n Gano el ejercito %s", ejer2.getEjercito());
            System.out.printf("\n Contador:\n");
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer1.getEjercito(), acumuladorMuertes1, acumuladorVivos1);
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer2.getEjercito(), acumuladorMuertes2, acumuladorVivos2);
            this.termino=true;
        } else if (acumuladorVivos2 == 0) {
            System.out.printf("\n Gano el ejercito %s", ejer1.getEjercito());
            System.out.printf("\n Contador:\n");
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer1.getEjercito(), acumuladorMuertes1, acumuladorVivos1);
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer2.getEjercito(), acumuladorMuertes2, acumuladorVivos2);
            this.termino = true;
        } else {
            System.out.printf("\n La batalla continua");
            System.out.printf("\n Contador:\n");
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer1.getEjercito(), acumuladorMuertes1, acumuladorVivos1);
            System.out.printf("\n Ejercito %s \n Muertes: %d \n Vivos: %d \n", ejer2.getEjercito(), acumuladorMuertes2, acumuladorVivos2);


        }
    }


    //Funcion que crea espacios en la consola, meramente visual
    private void saltoLinea() {
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
        System.out.printf("\n");
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
            System.out.printf("\n Muertes ejercito %s: %d  \n", e.getEjercito(), acumuladorMuertes);
        }
        if (acumuladorDañados != 0) {
            System.out.printf("\n Heridos ejercito %s: %d \n", e.getEjercito(), acumuladorDañados);
        }
    }

}


