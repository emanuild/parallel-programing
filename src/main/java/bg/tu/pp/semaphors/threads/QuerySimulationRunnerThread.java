package bg.tu.pp.semaphors.threads;

import bg.tu.pp.semaphors.implementation.CustomSemaphore;
import bg.tu.pp.semaphors.logic.BogusDbBusinessLogic;

import java.util.concurrent.Semaphore;

public class QuerySimulationRunnerThread extends Thread {

    private CustomSemaphore semaphore;
    private String query;
    private long startMilis;
    private BogusDbBusinessLogic businessLogic;

    public QuerySimulationRunnerThread(CustomSemaphore semaphore, String query, long startMilis) {
        this.semaphore = semaphore;
        this.query = query;
        this.startMilis = startMilis;

        businessLogic = new BogusDbBusinessLogic();
    }

    @Override
    public void run() {
        System.out.println(query+" queued for execution!");
        try {
            semaphore.acquire();
            businessLogic.processMeaningfulLogic(query, startMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
