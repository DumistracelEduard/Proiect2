package run;

import run.Average.Average;
import run.Average.AverageBaby;
import run.Average.AverageKid;
import run.Average.AverageTeen;

import java.util.List;

public final class NiceScore {
    private double niceScore;
    private final List<Double> niceScoreHistory;
    private final Integer age;
    private final double niceScoreBonus;

    public static final class Builder {
        private final Integer age;
        private final List<Double> niceScoreHistory;
        private double niceScoreBonus = 0;

        public Builder(final Integer age, final List<Double> niceScoreHistory) {
            this.age = age;
            this.niceScoreHistory = niceScoreHistory;
        }

        /**
         * niceScoreBonus nu e neaparat necesar
         * @param bonusNiceScore score-ul bonus
         * @return
         */
        public Builder niceScoreBonus(final Double bonusNiceScore) {
            this.niceScoreBonus = bonusNiceScore;
            return this;
        }

        /**
         * build
         * @return
         */
        public NiceScore build() {
            return new NiceScore(this);
        }
    }

    /**
     * selecteaza tipul de medie adecvata varstei
     */
    public void calculateAverage() {
        double niceScoreFinal;
        final int number0 = 100;
        final int number1 = 5;
        final int number2 = 12;
        final int number3 = 10;
        if (age < number1) {
            Average average = new Average(new AverageBaby());
            niceScoreFinal = average.executeAverage(niceScoreHistory);
            this.niceScore = niceScoreFinal;
        } else if (age < number2) {
            Average average = new Average(new AverageKid());
            niceScoreFinal = average.executeAverage(niceScoreHistory);
            niceScoreFinal = niceScoreFinal + ((niceScoreFinal * niceScoreBonus) / number0);
            if (niceScoreFinal > number3) {
                niceScoreFinal = number3;
            }
            this.niceScore = niceScoreFinal;
        } else {
            Average average = new Average(new AverageTeen());
            niceScoreFinal = average.executeAverage(niceScoreHistory);
            niceScoreFinal = niceScoreFinal + ((niceScoreFinal * niceScoreBonus) / number0);
            if (niceScoreFinal > number3) {
                niceScoreFinal = number3;
            }
            this.niceScore = niceScoreFinal;
        }
    }

    private NiceScore(final Builder builder) {
        this.niceScoreBonus = builder.niceScoreBonus;
        this.age = builder.age;
        this.niceScoreHistory = builder.niceScoreHistory;
    }

    public double getNiceScore() {
        return niceScore;
    }
}
