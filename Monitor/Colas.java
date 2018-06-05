package Monitor;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.math3.linear.ArrayRealVector;

public class Colas {
    private int cantidadColas;
    ArrayList<Lock> colas = new ArrayList<>();

    public Colas(int cantidad) {
        this.cantidadColas = cantidad;

        for (int i = 0; i < cantidad; i++) {
            Lock queueLock = new ReentrantLock(true);
            colas.add(queueLock);
        }
    }

    public void acquire(int index) {
        colas.get(index).lock();
    }

    public void release(int index) {
        colas.get(index).unlock();
    }

    public ArrayRealVector quienesEstan() {
        ArrayRealVector lockeados = new ArrayRealVector();

        for (int i = 0; i < cantidadColas; i++) {
            Lock lock = colas.get(i);
            //Si hay no hay hilos bloqueados con tryLock se bloquea, sino sigue
            if (lock.tryLock()) {
                lock.unlock();
                lockeados.append(0);
            } else {
                lockeados.append(1);
            }
        }
        return lockeados;
    }
}
