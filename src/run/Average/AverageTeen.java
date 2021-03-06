package run.Average;

import java.util.List;

public class AverageTeen implements AverageScoreCalculator {
    /**
     * in caz ca copilul este Teen face media ponderata
     * @param niceScoreHistory lista de scoruri
     * @return returneaza media
     */
    @Override
    public double doAverage(final List<Double> niceScoreHistory) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < niceScoreHistory.size(); ++i) {
            sum += ((i + 1) * niceScoreHistory.get(i));
            count += (i + 1);
        }
        return sum / count;
    }
}
