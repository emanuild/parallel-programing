package bg.tu.pp.semaphors.logic;

import bg.tu.pp.phasers.dto.Player;

import java.util.Date;
import java.util.Random;

public class BogusDbBusinessLogic {

    private static final Random RANDOM = new Random();
    private static final int MAX_EXEC_TIME = 1000;// 1 second

    public void processMeaningfulLogic(String query, long startMilis) {
        Integer executionTime = 200;//RANDOM.nextInt(MAX_EXEC_TIME);
        System.out.println(query+" is executing!");

        try {
            Thread.sleep(executionTime.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeElapsedSinceStart = new Date().getTime()-startMilis;
        System.out.println(query+" executed in "+executionTime+"ms.\nTime passed from the start:"+timeElapsedSinceStart);
    }
}
