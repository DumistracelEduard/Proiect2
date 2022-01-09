package run;

import run.InputClass.*;
import run.elf.RunBlackOrPink;

import java.util.*;

public class GiftDistribution {
    public static void updateQuantity(List<HashMap<String, Integer>> quantityYear, String name) {
        for (int i = 0; i < quantityYear.size(); ++i) {
            if(quantityYear.get(i).containsKey(name)) {
                Integer total = quantityYear.get(i).get(name);
                total--;
                quantityYear.get(i).put(name, total);
            }
        }
    }
    public static void idSort(Children children, HashMap<String, ArrayList<Gift>> gifts, HashMap<String, Integer> quantity, List<HashMap<String, Integer>> quantityYear) {
        Collections.sort(children.getChildren(), new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        double buget;
        for (Child child: children.getChildren()) {
            buget = child.getAssignedBudget();
            for (String favorite: child.getGiftsPreferences()) {
                if(gifts.get(favorite) == null) {
                    continue;
                }
                Collections.sort(gifts.get(favorite), new Comparator<Gift>() {
                    @Override
                    public int compare(Gift o1, Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
                for (Gift gift: gifts.get(favorite)) {
                    if (buget - gift.getPrice() > 0) {
                        if (quantity.containsKey(gift.getProductName()) && quantity.get(gift.getProductName()) > 0) {
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
    public static void niceScore(Children children, HashMap<String, ArrayList<Gift>> gifts,
                          HashMap<String, Integer> quantity, List<HashMap<String, Integer>> quantityYear) {
        Collections.sort(children.getChildren(), new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });
        double buget;
        for (Child child: children.getChildren()) {
            buget = child.getAssignedBudget();
            for (String favorite: child.getGiftsPreferences()) {
                for(Gift gift: gifts.get(favorite)) {
                    if (buget - gift.getPrice() > 0) {
                        if (quantity.containsKey(gift.getProductName()) && quantity.get(gift.getProductName()) > 0) {
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

    public static void witchStrategy(final Children children, HashMap<String, ArrayList<Gift>> gifts,
                              AnnualChanges annualChanges, HashMap<String, Integer> quantity,
                              final HashMap<Integer, String> elf, final double budgetUnit, Integer year,
                                     List<HashMap<String, Integer>> quantityYear) {
        for (Child child : children.getChildren()) {
            child.calculateBudget(budgetUnit);
            RunBlackOrPink.run(child, elf);
        }
        if (annualChanges.getStrategy().equals("niceScore") && year != 0) {
            niceScore(children, gifts, quantity, quantityYear);
            Collections.sort(children.getChildren(), new Comparator<Child>() {
                @Override
                public int compare(Child o1, Child o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });
        } else if (annualChanges.getStrategy().equals("niceScoreCity") && year != 0) {
            List<City> listCity =  WitchCity.addScoreCity(children);
            double buget;
            for (City city: listCity) {
                for(Child child: city.getChildren()) {
                    buget = child.getAssignedBudget();
                    for (String favorite: child.getGiftsPreferences()) {
                        for(Gift gift: gifts.get(favorite)) {
                            if (buget - gift.getPrice() > 0) {
                                if (quantity.containsKey(gift.getProductName()) && quantity.get(gift.getProductName()) > 0) {
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
