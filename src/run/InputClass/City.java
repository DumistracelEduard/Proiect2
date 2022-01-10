package run.InputClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class City {
    private double score;
    private String name;
    private ArrayList<Double> niceScores;
    private ArrayList<Child> children;

    public City(final String name) {
        this.name = name;
        this.score = 0;
        this.niceScores = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * adauga in lista de Score-uri ale orasului
     * @param niceScore
     */
    public void addScore(final double niceScore) {
        this.niceScores.add(niceScore);
    }

    /**
     * adauga copilul in lista si sorteaza dupa ID si calcueaza
     * de fiecare data cand se adauga un copil Score-ul orasului
     * @param child
     */
    public void addChild(final Child child) {
        this.children.add(child);
        Collections.sort(children, new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
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

    public final ArrayList<Double> getNiceScores() {
        return niceScores;
    }

    public final ArrayList<Child> getChildren() {
        return children;
    }
}
