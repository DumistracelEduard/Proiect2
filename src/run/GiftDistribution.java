package run;

import run.InputClass.AnnualChanges;
import run.InputClass.Child;
import run.InputClass.Children;
import run.InputClass.City;
import run.InputClass.Gift;
import run.InputClass.WitchCity;
import run.elf.RunBlackOrPink;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public final class GiftDistribution {
    private GiftDistribution() { }

    /**
     * face update in toata lista de quantity
     * @param quantityYear lista de cantitati pe an
     * @param name numele obiectului cautat
     */
    public static void updateQuantity(final List<HashMap<String, Integer>> quantityYear,
                                      final String name) {
        for (HashMap<String, Integer> stringIntegerHashMap : quantityYear) {
            if (stringIntegerHashMap.containsKey(name)) {
                Integer total = stringIntegerHashMap.get(name);
                total--;
                stringIntegerHashMap.put(name, total);
            }
        }
    }

    /**
     * distribuie cadourile dupa id
     * @param children lista de copii
     * @param gifts lista de cadouri
     * @param quantity lista de cantitati
     * @param quantityYear lista de cantitati pe toti anii
     */
    public static void idSort(final Children children,
                              final HashMap<String, ArrayList<Gift>> gifts,
                              final HashMap<String, Integer> quantity,
                              final List<HashMap<String, Integer>> quantityYear) {
        children.getChildren().sort(Comparator.comparingInt(Child::getId));
        double buget;
        for (Child child: children.getChildren()) {
            buget = child.getAssignedBudget();
            for (String favorite: child.getGiftsPreferences()) {
                if (gifts.get(favorite) == null) {
                    continue;
                }
                gifts.get(favorite).sort(Comparator.comparingDouble(Gift::getPrice));
                for (Gift gift: gifts.get(favorite)) {
                    if (buget - gift.getPrice() > 0) {
                        if (quantity.containsKey(gift.getProductName())
                                && quantity.get(gift.getProductName()) > 0) {
                            updateQuantity(quantityYear, gift.getProductName());
                            child.getReceivedGifts().add(gift);
                            buget -= gift.getPrice();
                            break;
                        }
                    }
                }

            }
        }
    }

    /**
     * realizeaza distribuirea dupa niceScore
     * @param children lista de copii
     * @param gifts lista de cadouri
     * @param quantity lista de cantitati
     * @param quantityYear lista de cantitati in fiecare an
     */
    public static void niceScore(final Children children,
                                 final HashMap<String, ArrayList<Gift>> gifts,
                                 final HashMap<String, Integer> quantity,
                                 final List<HashMap<String, Integer>> quantityYear) {
        children.getChildren().sort((o1, o2) ->
                Double.compare(o2.getAverageScore(), o1.getAverageScore()));
        double buget;
        for (Child child: children.getChildren()) {
            buget = child.getAssignedBudget();
            for (String favorite: child.getGiftsPreferences()) {
                for (Gift gift: gifts.get(favorite)) {
                    if (buget - gift.getPrice() > 0) {
                        if (quantity.containsKey(gift.getProductName())
                                && quantity.get(gift.getProductName()) > 0) {
                            updateQuantity(quantityYear, gift.getProductName());
                            child.getReceivedGifts().add(gift);
                            buget -= gift.getPrice();
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * calculeaza bugetul si vede ce fel de strategie sa ruleze
     * @param children lista de copii
     * @param gifts lista de cadouri
     * @param annualChanges lista de schimbari anuale
     * @param quantity lista de cantitati
     * @param elf lista de elfi
     * @param budgetUnit bugetul pe o unitate
     * @param year verificam daca e alt an decat anul 0
     * @param quantityYear lista de cantitati pe fiecare an
     */
    public static void witchStrategy(final Children children,
                                     final HashMap<String, ArrayList<Gift>> gifts,
                                     final AnnualChanges annualChanges,
                                     final HashMap<String, Integer> quantity,
                                     final HashMap<Integer, String> elf,
                                     final double budgetUnit,
                                     final Integer year,
                                     final List<HashMap<String, Integer>> quantityYear) {
        for (Child child : children.getChildren()) {
            child.calculateBudget(budgetUnit);
            RunBlackOrPink.run(child, elf);
        }
        if (annualChanges.getStrategy().equals("niceScore") && year != 0) {
            niceScore(children, gifts, quantity, quantityYear);
            children.getChildren().sort(Comparator.comparingInt(Child::getId));
        } else if (annualChanges.getStrategy().equals("niceScoreCity") && year != 0) {
            List<City> listCity =  WitchCity.addScoreCity(children);
            double buget;
            for (City city: listCity) {
                for (Child child: city.getChildren()) {
                    buget = child.getAssignedBudget();
                    for (String favorite: child.getGiftsPreferences()) {
                        for (Gift gift: gifts.get(favorite)) {
                            if (buget - gift.getPrice() > 0) {
                                if (quantity.containsKey(gift.getProductName())
                                        && quantity.get(gift.getProductName()) > 0) {
                                    updateQuantity(quantityYear, gift.getProductName());
                                    child.getReceivedGifts().add(gift);
                                    buget -= gift.getPrice();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            idSort(children, gifts, quantity, quantityYear);
        }
    }
}
