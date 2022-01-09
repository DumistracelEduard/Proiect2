package run.DataStore;

import run.InputClass.AnnualChanges;
import run.InputClass.Child;
import run.InputClass.Gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputData {
    private final Integer numberOfYears;
    private double santaBudget;
    private List<Object> dataStore = new ArrayList<>();
    private final List<AnnualChanges> annualChanges;
    private final HashMap<Integer, Double> listScoreBonus;
    private final HashMap<Integer, String> listElf;
    private final HashMap<String, Integer> quantity;

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
                     final List<String> cityList, final List<AnnualChanges> annualChanges,
                     final HashMap<Integer, Double> listScoreBonus,
                     final HashMap<Integer, String> listElf,
                     final HashMap<String, Integer> quantity) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.dataStore.add(childList);
        this.dataStore.add(giftList);
        this.dataStore.add(cityList);
        this.annualChanges = annualChanges;
        this.listScoreBonus = listScoreBonus;
        this.listElf = listElf;
        this.quantity = quantity;
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

    public final HashMap<Integer, Double> getListScoreBonus() {
        return listScoreBonus;
    }

    public final HashMap<Integer, String> getListElf() {
        return listElf;
    }

    public HashMap<String, Integer> getQuantity() {
        return quantity;
    }
}
