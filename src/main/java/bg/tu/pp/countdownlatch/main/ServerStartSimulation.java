package bg.tu.pp.countdownlatch.main;

import bg.tu.pp.countdownlatch.threads.ServiceStarterThread;
import javafx.concurrent.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class ServerStartSimulation {

    private static final String[] SERVICES_TO_START = {
            "exif",
            "http",
            "php",
            "GD",
            "etc"
    };

    public static void main(String[] args) {
        final long startMilis = new Date().getTime();
        final int serviceCount = SERVICES_TO_START.length;
        CountDownLatch latch = new CountDownLatch(serviceCount);

        System.out.println("Server is starting . . .");

        for (String serviceName : SERVICES_TO_START) {
            ServiceStarterThread thread = new ServiceStarterThread(serviceName, latch);
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startupMilis = new Date().getTime() - startMilis;
        System.out.println("Server has started with "+serviceCount+" active services in "+startupMilis+"ms");
    }
}
