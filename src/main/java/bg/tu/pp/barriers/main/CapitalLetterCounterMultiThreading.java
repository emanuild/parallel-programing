package bg.tu.pp.barriers.main;

import bg.tu.pp.barriers.threads.FileLineProcessingThread;
import bg.tu.pp.barriers.threads.ResultConsolidationThread;
import bg.tu.pp.barriers.util.file.CsvFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CapitalLetterCounterMultiThreading implements CapitalLetterCounterConfig {

    // set this to the number of threads you wish your program to spawn
    private static final int NUM_THREADS = 3;
    private static CyclicBarrier barrier;

    // the main reference for each thread to post its statistics result to
    private static List<Float> results = new ArrayList<>(NUM_THREADS);

    public static void main(String[] args) {
        try {
            barrier = new CyclicBarrier(NUM_THREADS, new ResultConsolidationThread(results));

            long start = new Date().getTime();

            CsvFileReader csvFileReader = new CsvFileReader(FILE_PATH);
            processPostsByLineMultiThreading(csvFileReader);

            long end = new Date().getTime();

            System.out.println("Reading took: "+(end - start));
            System.out.println("Memory used: "+Runtime.getRuntime().totalMemory());
        } catch (FileNotFoundException e) {
            System.out.println(FILE_PATH+" does not exist!");
        }
    }

    private static void processPostsByLineMultiThreading(CsvFileReader csvFileReader) {
        for (int i=0; i<NUM_THREADS; ++i) {
            FileLineProcessingThread thread = new FileLineProcessingThread("Thread #"+i, barrier, csvFileReader, results);
            thread.start();
        }
    }

}
