package run.elf;

import run.GiftDistribution;
import run.InputClass.Child;
import run.InputClass.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public final class Yellow {
    private Yellow() { }

    /**
     * in caz ca elful este yellow se ia primul cadou din prima prefererinta
     * doar daca copilul nu are cadou sau cadoul inca e pe stoc
     * @param child
     * @param elf
     * @param gifts
     * @param quantity
     * @param quantityYear
     */
    public static void yellowRun(final Child child, final String elf,
                                 final HashMap<String, ArrayList<Gift>> gifts,
                                 final HashMap<String, Integer> quantity,
                                 final List<HashMap<String, Integer>> quantityYear) {
        if (elf.equals("yellow")) {
            if (child.getReceivedGifts().size() != 0) {
                return;
            }
            if (gifts.containsKey(child.getGiftsPreferences().get(0))) {
                ArrayList<Gift> giftsList = gifts.get(child.getGiftsPreferences().get(0));
                Comparator comparator = new Comparator<Gift>() {
                    @Override
                    public int compare(final Gift o1, final Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                };
                giftsList.sort(comparator);

                if (quantity.containsKey(giftsList.get(0).getProductName())
                        && quantity.get(giftsList.get(0).getProductName()) > 0) {
                    child.getReceivedGifts().add(giftsList.get(0));
                    GiftDistribution.updateQuantity(quantityYear, giftsList.get(0)
                            .getProductName());
                }
            }
        }
    }
}
