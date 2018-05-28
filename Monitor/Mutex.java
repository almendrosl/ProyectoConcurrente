package Monitor;

import java.util.concurrent.Semaphore;

public class Mutex {
    private Semaphore semaphore;

    //Inicializa semáforo en 1 porque es mutex y solo quiero exclusión mutua y además
    //Fairnes true para que la cola de espera sea FIFO
    public void Mutex() {
        semaphore = new Semaphore(1, true);
    }

    public void acquire() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void release() {
        semaphore.release();
    }

    public final int getQueueLength() {
        return semaphore.getQueueLength();
    }

    public final boolean hasQueuedThreads() {
        return semaphore.hasQueuedThreads();
    }

    public boolean tryAcquire() {
        return semaphore.tryAcquire();
    }
}
