package run.InputClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Child {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private Integer age;
    private ArrayList<String> giftsPreferences;
    private double averageScore;
    private final List<Double> niceScoreHistory;
    private double assignedBudget;
    private final List<Gift> receivedGifts;

    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge() + 1;
        this.city = child.getCity();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.receivedGifts = new ArrayList<>();
        this.averageScore = 0;
        this.assignedBudget = 0;
    }

    public Child(final Integer id, final String lastName,
                 final String firstName, final Integer age,
                 final String city, final ArrayList<String> giftsPreferences,
                 final double averageScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.averageScore = averageScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(averageScore);
        this.receivedGifts = new ArrayList<>();
    }

    /**
     * verifica daca in lista de preferinte exista de 2 ori sau
     * mai mult una dintre preferinte si daca da o sterge
     */
    public void checkPreferences() {
        for (int i = 0; i < giftsPreferences.size(); ++i) {
            for (int j = i + 1; j < giftsPreferences.size() - 1; ++j) {
                if (giftsPreferences.get(i).equals(giftsPreferences.get(j))) {
                    giftsPreferences.remove(j);
                }
            }
        }
    }

    /**
     * updateaza lista de categori de preferinte
     * @param newPreferences lista de preferinte
     */
    public void updateGiftPreferences(final List<String> newPreferences) {
        int ok;
        for (String oldPreferences: giftsPreferences) {
            ok = 0;
            for (String newPreference: newPreferences) {
                if (oldPreferences.equals(newPreference)) {
                    ok = 1;
                }
            }
            if (ok == 0) {
                newPreferences.add(oldPreferences);
            }
        }

        HashMap<String, String> duplicate = new HashMap<>();
        ArrayList<String> finalPreferences = new ArrayList<>();
        for (String newPreference : newPreferences) {
            if (!duplicate.containsKey(newPreference)) {
                finalPreferences.add(newPreference);
                duplicate.put(newPreference, newPreference);
            }
        }
        this.giftsPreferences = finalPreferences;
    }

    /**
     * calculeaza bugetul
     * @param budgetUnit bugetul pe unitate
     */
    public void calculateBudget(final double budgetUnit) {
        this.assignedBudget = averageScore * budgetUnit;
    }

    /**
     * adauga scorul de cumintenie daca nu este null
     * @param newScore noul score adaugat
     */
    public void addNiceScore(final double newScore) {
        final int number = 18;
        if (newScore != -1 && age <= number) {
            this.niceScoreHistory.add(newScore);
        }
    }

    public final Integer getId() {
        return id;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getCity() {
        return city;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(final Integer age) {
        this.age = age;
    }

    public final ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final double getAverageScore() {
        return averageScore;
    }

    public final void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    public final List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final double getAssignedBudget() {
        return assignedBudget;
    }

    public final void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public final List<Gift> getReceivedGifts() {
        return receivedGifts;
    }
}
