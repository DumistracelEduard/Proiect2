package run.Average;

import java.util.List;

public class AverageKid implements AverageScoreCalculator {
    /**
     * in cazul in care este copil se calculeaza media aritmetica
     * @param niceScoreHistory
     * @return
     */
    @Override
    public double doAverage(final List<Double> niceScoreHistory) {
        if (niceScoreHistory.size() != 1) {
            double sum = 0;
            for (double number : niceScoreHistory) {
                sum += number;
            }
            return sum / niceScoreHistory.size();
        }
        return niceScoreHistory.get(0);
    }
}
