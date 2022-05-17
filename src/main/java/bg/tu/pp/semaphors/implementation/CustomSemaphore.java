package bg.tu.pp.semaphors.implementation;

public class CustomSemaphore {

    private int limit = 0;
    private int registeredThreadsCount = 0;

    public CustomSemaphore(int limit) {
        this.limit = limit;
    }

    public synchronized void acquire() throws InterruptedException {
        registeredThreadsCount = registeredThreadsCount+1;

        if (limit <= registeredThreadsCount) {
            this.wait();
        }
    }

    public synchronized void release() {
        registeredThreadsCount = registeredThreadsCount-1;

        try {
            this.notify();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
