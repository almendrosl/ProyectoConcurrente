package Monitor;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Colas {
    private int cantidad;
    ArrayList<Lock> colas = new ArrayList<>();

    public Colas(int cantidad) {
        this.cantidad = cantidad;

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

    public ArrayList<Integer> quienesEstan() {
        ArrayList<Integer> lockeados = new ArrayList<Integer>();

        for (int i = 0; i < cantidad; i++) {
            Lock lock = colas.get(i);
            if (lock.tryLock()) {
                lock.unlock();
            } else {
                lockeados.add(i);
            }
        }

        return lockeados;
    }
}
