package Monitor;

import RdP.RdP;
import org.apache.commons.math3.linear.ArrayRealVector;

public class GestorDeMonitor {
    private final Mutex mutex = new Mutex();
    private int cantidadTransiciones;
    private Colas colas;
    private Politicas politicas;
    private RdP petriNet;

    public GestorDeMonitor(int cantidadTransiciones, Politicas politicas, RdP rdp) {
        this.cantidadTransiciones = cantidadTransiciones;
        colas = new Colas(cantidadTransiciones);
        this.politicas = politicas;
        petriNet = rdp;
    }

    public void dispararTransicion(int transicionN) {
        mutex.acquire();

        boolean k = true;

        while (k) {
            k = true;
            k = petriNet.disparar();
            if (k) {
                ArrayRealVector sensibilizadas = colas.quienesEstan(); //rdp.sensibilizadas();
                ArrayRealVector quienesEstan = colas.quienesEstan();
                ArrayRealVector addVector = sensibilizadas.ebeMultiply(quienesEstan);
                int cantidadDeDisparables = cual(addVector);
                if (cantidadDeDisparables == 0) {
                    k = false;
                } else if (cantidadDeDisparables == 1) {
                    int hiloDespertable = 0;
                    for (int i = 0; i < cantidadTransiciones; i++) {
                        if (addVector.getEntry(i) != 0) {
                            hiloDespertable = i;
                        }
                    }
                    colas.release(hiloDespertable);
                    break;
                } else {
                    int thrreadDespertar = politicas.masPrioridad(addVector);
                    colas.release(thrreadDespertar);
                    break;
                }
            } else {
                mutex.release();
                colas.acquire(transicionN);
            }
        }
        mutex.release();
    }

    private int cual(ArrayRealVector addVector) {
        int cantidadDeDisparables = 0;
        for (int i = 0; i < cantidadTransiciones; i++) {
            if (addVector.getEntry(i) > 0) {
                cantidadDeDisparables++;
            }
        }
        return cantidadDeDisparables;
    }
}