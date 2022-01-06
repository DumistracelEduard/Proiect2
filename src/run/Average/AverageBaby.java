package run.Average;

import java.util.List;

public class AverageBaby implements AverageScoreCalculator {
    /**
     * in cazul in care este bebelus media este 10
     * @param niceScoreHistory
     * @return
     */
    @Override
    public double doAverage(final List<Double> niceScoreHistory) {
        final int value = 10;
        return value;
    }
}
