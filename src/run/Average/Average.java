package run.Average;

import java.util.List;

public class Average {
    private final AverageScoreCalculator averageScoreCalculator;

    /**
     * constructor
     * @param averageScoreCalculator
     */
    public Average(final AverageScoreCalculator averageScoreCalculator) {
        this.averageScoreCalculator = averageScoreCalculator;
    }

    /**
     * calculeaza media
     * @param niceScoreHistory
     * @return
     */
    public double executeAverage(final List<Double> niceScoreHistory) {
        return averageScoreCalculator.doAverage(niceScoreHistory);
    }
}
