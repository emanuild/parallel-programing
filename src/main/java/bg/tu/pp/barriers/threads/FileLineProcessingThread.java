package bg.tu.pp.barriers.threads;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import bg.tu.pp.barriers.util.file.CsvFileReader;

public class FileLineProcessingThread extends Thread {

    private String threadName;
    private CsvFileReader csvFileReader;
    private CyclicBarrier barrier;
    private List<Float> results;

    public FileLineProcessingThread(String threadName, CyclicBarrier barrier, CsvFileReader csvFileReader, List<Float> results) {
        this.threadName = threadName;
        this.csvFileReader = csvFileReader;
        this.barrier = barrier;
        this.results = results;
    }

    @Override
    public void run() {
        long start = new Date().getTime();
        int fileLinesSize = 0;
        float postsCapitalLettersPercentage = 0.0f;
        List<String> fileLine;

        try {
            fileLine = csvFileReader.getCsvLine();

            while (fileLine != null) {
                ++fileLinesSize;

                if (fileLine.size() >= 10) {
                    String textColumn = fileLine.get(9);

                    float linePercentage = processLine(textColumn);
                    postsCapitalLettersPercentage += linePercentage;
                } else {
                    //System.out.println("Warning: inconsistent line: "+fileLinesSize+"! Content:"+fileLine);
                }

                fileLine = csvFileReader.getCsvLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        results.add((Float) (postsCapitalLettersPercentage/fileLinesSize));

        long end = new Date().getTime();
        System.out.println("Execution time of "+threadName+": "+(end-start));
        System.out.println("Common percentage of "+threadName+": "+(postsCapitalLettersPercentage/fileLinesSize));

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private static float processLine(String textColumn) {
        int capitalLetters = 0;
        int textLength = textColumn.length();

        if (textLength == 0) {
            return 0.0f;
        }

        for (int i=0; i<textLength; ++i) {
            char c = textColumn.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                ++capitalLetters;
            }
        }

        return (float) ((capitalLetters*100) / textLength);
    }

}
