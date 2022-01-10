package run;

import run.InputClass.AnnualChanges;
import run.InputClass.Child;
import run.InputClass.Children;
import run.InputClass.City;
import run.InputClass.Gift;
import run.InputClass.WitchCity;
import run.elf.RunBlackOrPink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public final class GiftDistribution {
    private GiftDistribution() { }

    /**
     * face update in toata lista de quantity
     * @param quantityYear
     * @param name
     */
    public static void updateQuantity(final List<HashMap<String, Integer>> quantityYear,
                                      final String name) {
        for (int i = 0; i < quantityYear.size(); ++i) {
            if (quantityYear.get(i).containsKey(name)) {
                Integer total = quantityYear.get(i).get(name);
                total--;
                quantityYear.get(i).put(name, total);
            }
        }
    }

    /**
     * distribuie cadourile dupa id
     * @param children
     * @param gifts
     * @param quantity
     * @param quantityYear
     */
    public static void idSort(final Children children,
                              final HashMap<String, ArrayList<Gift>> gifts,
                              final HashMap<String, Integer> quantity,
                              final List<HashMap<String, Integer>> quantityYear) {
        Collections.sort(children.getChildren(), new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        double buget;
        for (Child child: children.getChildren()) {
            buget = child.getAssignedBudget();
            for (String favorite: child.getGiftsPreferences()) {
                if (gifts.get(favorite) == null) {
                    continue;
                }
                Collections.sort(gifts.get(favorite), new Comparator<Gift>() {
                    @Override
                    public int compare(final Gift o1, final Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
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
     * @param children
     * @param gifts
     * @param quantity
     * @param quantityYear
     */
    public static void niceScore(final Children children,
                                 final HashMap<String, ArrayList<Gift>> gifts,
                                 final HashMap<String, Integer> quantity,
                                 final List<HashMap<String, Integer>> quantityYear) {
        Collections.sort(children.getChildren(), new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });
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
     * @param children
     * @param gifts
     * @param annualChanges
     * @param quantity
     * @param elf
     * @param budgetUnit
     * @param year
     * @param quantityYear
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
            Collections.sort(children.getChildren(), new Comparator<Child>() {
                @Override
                public int compare(final Child o1, final Child o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });
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
