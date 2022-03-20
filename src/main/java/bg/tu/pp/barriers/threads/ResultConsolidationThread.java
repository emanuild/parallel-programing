package bg.tu.pp.barriers.threads;

import java.util.List;

public class ResultConsolidationThread extends Thread {

    private List<Float> results;

    public ResultConsolidationThread(List<Float> results) {
        this.results = results;
    }

    @Override
    public void run() {
        System.out.println("Result Consolidation Thread started!");
        Float sum = 0.0f;

        for (Float result : results) {
            sum += result;
        }
        float compoundResult = sum/results.size();

        System.out.println("Compound result: "+compoundResult);
    }
}
