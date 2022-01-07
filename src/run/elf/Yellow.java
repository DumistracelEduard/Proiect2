package run.elf;

import run.InputClass.Child;
import run.InputClass.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Yellow {
    public void YellowRun(Child child, String elf, HashMap<String, ArrayList<Gift>> gifts,
                          HashMap<String, Integer> quantity) {
        if (elf.equals("yellow")) {
            for (String preference: child.getGiftsPreferences()) {
                if (gifts.containsKey(preference)) {
                    ArrayList<Gift> giftsList = gifts.get(preference);
                    Comparator comparator = new Comparator<Gift>() {
                        @Override
                        public int compare(final Gift o1, final Gift o2) {
                            return Double.compare(o1.getPrice(), o2.getPrice());
                        }
                    };
                    giftsList.sort(comparator);
                    for (Gift gift: giftsList) {
                        if (quantity.containsKey(gift.getProductName()) && quantity.get(gift.getProductName()) > 0) {
                            child.getReceivedGifts().add(gift);
                            return;
                        }
                    }
                }
            }
        }
    }
}
