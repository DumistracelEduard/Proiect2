package run.DataStore;

import run.InputClass.AnnualChanges;
import run.InputClass.Child;
import run.InputClass.Gift;

import java.util.ArrayList;
import java.util.List;

public class InputData {
    private final Integer numberOfYears;
    private double santaBudget;
    private List<Object> dataStore = new ArrayList<>();
    private final List<AnnualChanges> annualChanges;

    /**
     * constructor
     * @param numberOfYears
     * @param santaBudget
     * @param childList
     * @param giftList
     * @param cityList
     * @param annualChanges
     */
    public InputData(final Integer numberOfYears, final double santaBudget,
                     final List<Child> childList, final List<Gift> giftList,
                     final List<String> cityList, final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.dataStore.add(childList);
        this.dataStore.add(giftList);
        this.dataStore.add(cityList);
        this.annualChanges = annualChanges;
    }

    public final Integer getNumberOfYears() {
        return numberOfYears;
    }

    public final double getSantaBudget() {
        return santaBudget;
    }

    public final List<Object> getDataStore() {
        return dataStore;
    }

    public final List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }
}
