package run;

import run.InputClass.Child;
import run.InputClass.ChildUpdate;
import run.InputClass.Children;
import run.InputClass.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public final class Utils {
    private Utils() { }

    /**
     * distribuirea cadourilor pentru fiecare buget
     * @param budgetUnit
     * @param children
     * @param listGift
     */
    public static void distributionGift(final double budgetUnit, final Children children,
                                        final HashMap<String, ArrayList<Gift>> listGift) {
        for (Child child : children.getChildren()) {
            double buget = child.calculateBudget(budgetUnit);
            for (String giftPreference : child.getGiftsPreferences()) {
                if (listGift.get(giftPreference) != null
                        && buget - listGift.get(giftPreference).get(0).getPrice() > 0.0) {
                    buget -= listGift.get(giftPreference).get(0).getPrice();
                    child.getReceivedGifts().add(listGift.get(giftPreference).get(0));
                }
            }
        }
    }

    /**
     * convert lista de object pentru cadouri in hashmap de liste
     * fiecare lista fiin in ordine desc a preturilor
     * @param array
     * @return
     */
    public static HashMap<String, ArrayList<Gift>> convertObjectGift(final List<Object> array) {
        HashMap<String, ArrayList<Gift>> listGift = new HashMap<>();
        for (Object gift : array) {
            Gift giftSearch = (Gift) gift;
            if (listGift.containsKey(giftSearch.getCategory())) {
                listGift.get(giftSearch.getCategory()).add(giftSearch);
                Collections.sort(listGift.get(giftSearch.getCategory()), new Comparator<Gift>() {
                    @Override
                    public int compare(final Gift o1, final Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
            } else {
                ArrayList<Gift> newCategory = new ArrayList<>();
                newCategory.add(giftSearch);
                listGift.put(giftSearch.getCategory(), newCategory);
            }
        }
        return listGift;
    }

    /**
     * convert de la lista de Object la lista de copii
     * @param array
     * @return
     */
    public static ArrayList<Child> convertObject(final List<Object> array) {
        final int number = 18;
        ArrayList<Child> children = new ArrayList<>();
        for (Object object : array) {
            if (((Child) object).getAge() <= number) {
                children.add((Child) object);
            }
        }
        return children;
    }

    /**
     * convert de la lista de JSONArray in lista de String
     * @param array
     * @return
     */
    public static ArrayList<String> convertJSONArrayString(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * convert de la JSONArray la lista de cadouri
     * @param array
     * @return
     */
    public static ArrayList<Gift> convertJSONArrayGift(final JSONArray array) {
        if (array != null) {
            ArrayList<Gift> finalArrayGift = new ArrayList<>();
            for (Object object : array) {
                finalArrayGift.add(new Gift((String) ((JSONObject) object)
                        .get(Constants.PRODUCTNAME),
                        Double.parseDouble(((JSONObject) object)
                                .get(Constants.PRICE).toString()),
                        (String) ((JSONObject) object)
                                .get(Constants.CATEGORY)));
            }
            return finalArrayGift;
        } else {
            return null;
        }
    }

    /**
     * convert de la JSON Array la lista de copii
     * @param array
     * @return
     */
    public static ArrayList<Child> convertJSONArrayChildren(final JSONArray array) {
        if (array != null) {
            ArrayList<Child> newChildrenList = new ArrayList<>();
            for (Object object : array) {
                newChildrenList.add(new Child(Integer.parseInt(((JSONObject) object)
                        .get(Constants.ID).toString()),
                        (String) ((JSONObject) object).get(Constants.LASTNAME),
                        (String) ((JSONObject) object).get(Constants.FIRSTNAME),
                        Integer.parseInt(((JSONObject) object).get(Constants.AGE)
                                .toString()),
                        (String) ((JSONObject) object).get(Constants.CITY),
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                .get(Constants.GIFTSPREFERENCES)),
                        Double.parseDouble(((JSONObject) object).get(Constants.NICESCORE)
                                .toString())));
            }
            return newChildrenList;
        } else {
            return null;
        }
    }

    /**
     * convert lista de object in lista de ChildUpdate
     * @param array
     * @return
     */
    public static ArrayList<ChildUpdate> convertJSONArrayChildUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();
            for (Object object : array) {
                if (((JSONObject) object).get(Constants.NICESCORE) != null) {
                    childrenUpdates.add(new ChildUpdate(Integer.parseInt(((JSONObject) object)
                            .get(Constants.ID).toString()),
                            Double.parseDouble(((JSONObject) object).get(Constants.NICESCORE)
                                    .toString()),
                            Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFTSPREFERENCES))));
                } else {
                    childrenUpdates.add(new ChildUpdate(Integer.parseInt(((JSONObject) object)
                            .get(Constants.ID).toString()),
                            -1,
                            Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFTSPREFERENCES))));
                }
            }
            return childrenUpdates;
        } else {
            return null;
        }
    }
}