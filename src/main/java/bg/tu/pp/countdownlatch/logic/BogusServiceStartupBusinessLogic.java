package bg.tu.pp.countdownlatch.logic;

import java.util.Random;

public class BogusServiceStartupBusinessLogic {

    private static final Random RANDOM = new Random();
    private static final int MAX_EXEC_TIME = 10000;// 10 seconds

    public void startService(String serviceName) {
        Integer executionTime = RANDOM.nextInt(MAX_EXEC_TIME);
        System.out.println(serviceName+" is starting!");

        try {
            Thread.sleep(executionTime.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(serviceName+" has started in "+executionTime+"ms!");
    }
}
