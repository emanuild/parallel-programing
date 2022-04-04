package bg.tu.pp.countdownlatch.threads;

import bg.tu.pp.countdownlatch.logic.BogusServiceStartupBusinessLogic;

import java.util.concurrent.CountDownLatch;

public class ServiceStarterThread extends Thread {
    private String serviceName;
    private CountDownLatch latch;
    private BogusServiceStartupBusinessLogic businessLogic;

    public ServiceStarterThread(String serviceName, CountDownLatch latch) {
        this.serviceName = serviceName;
        this.latch = latch;
        this.businessLogic = new BogusServiceStartupBusinessLogic();
    }

    @Override
    public void run() {
        businessLogic.startService(serviceName);
        latch.countDown();
    }
}
