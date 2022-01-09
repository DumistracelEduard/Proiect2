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

    public void addScore(final double niceScore) {
        this.niceScores.add(niceScore);
    }

    public void addChild(final Child child) {
        this.children.add(child);
        Collections.sort(children, new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
    }

    public void calculateScore() {
        if (niceScores.size() == 0) {
            this.score = 0;
            return;
        }
        double sum = 0;
        for(Double score: niceScores) {
            sum += score;
        }
        this.score = sum / niceScores.size();
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getNiceScores() {
        return niceScores;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
}
