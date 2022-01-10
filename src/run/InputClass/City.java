package run.InputClass;

import java.util.ArrayList;
import java.util.Comparator;

public class City {
    private double score;
    private final String name;
    private final ArrayList<Double> niceScores;
    private final ArrayList<Child> children;

    public City(final String name) {
        this.name = name;
        this.score = 0;
        this.niceScores = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * adauga in lista de Score-uri ale orasului
     * @param niceScore noul score adaugat
     */
    public void addScore(final double niceScore) {
        this.niceScores.add(niceScore);
    }

    /**
     * adauga copilul in lista si sorteaza dupa ID si calcueaza
     * de fiecare data cand se adauga un copil Score-ul orasului
     * @param child noul copil adaugat
     */
    public void addChild(final Child child) {
        this.children.add(child);
        children.sort(Comparator.comparingInt(Child::getId));
        double sum = 0;
        for (Double scoreAdd: niceScores) {
            sum += scoreAdd;
        }
        this.score = sum / niceScores.size();
    }

    public final double getScore() {
        return score;
    }

    public final String getName() {
        return name;
    }

    public final ArrayList<Child> getChildren() {
        return children;
    }
}
