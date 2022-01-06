package run.Average;

import java.util.List;

public interface AverageScoreCalculator {
    /**
     * apeleaza tipul de medie
     * @param niceScoreHistory
     * @return
     */
    double doAverage(List<Double> niceScoreHistory);
}
