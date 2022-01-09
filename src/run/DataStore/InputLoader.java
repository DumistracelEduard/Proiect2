package run.DataStore;

import run.Constants;
import run.InputClass.AnnualChanges;
import run.InputClass.Child;
import run.InputClass.Gift;
import run.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class InputLoader {
    private final String inputPath;

    /**
     * salvam path-ul
     * @param inputPath
     */
    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * citesc input data utilizand JSONParser
     * @return
     */
    public InputData readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = 0;
        double santaBudget = 0;
        List<Child> childList = new ArrayList<>();
        List<Gift> giftList = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        List<AnnualChanges> annualChanges = new ArrayList<>();
        HashMap<Integer, Double> listScoreBonus = new HashMap<>();
        HashMap<Integer, String> listElf = new HashMap<>();
        HashMap<String, Integer> quantity = new HashMap<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            numberOfYears = Integer.parseInt(jsonObject.get(Constants.NUMBEROFYEARS).toString());
            santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTABUDGET).toString());
            JSONObject jsoninitialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);
            JSONArray jsonChangesAnnual = (JSONArray) jsonObject.get(Constants.ANNUALCHANGES);
            if (jsoninitialData != null) {
                JSONArray jsonChildren = (JSONArray) jsoninitialData.get(Constants.CHILDREN);
                if (jsonChildren != null) {
                    for (Object jsonChild : jsonChildren) {
                        childList.add(
                                new Child(Integer.parseInt(((JSONObject) jsonChild)
                                        .get(Constants.ID).toString()),
                                        (((String) ((JSONObject) jsonChild)
                                                .get(Constants.LASTNAME))),
                                        (((String) ((JSONObject) jsonChild)
                                                .get(Constants.FIRSTNAME))),
                                        Integer.parseInt(((JSONObject) jsonChild)
                                                .get(Constants.AGE)
                                        .toString()),
                                        (((String) ((JSONObject) jsonChild)
                                                .get(Constants.CITY))),
                                        Utils.convertJSONArrayString((JSONArray)
                                                ((JSONObject) jsonChild)
                                                        .get(Constants.GIFTSPREFERENCES)),
                                        Double.parseDouble(((JSONObject) jsonChild)
                                        .get(Constants.NICESCORE).toString())));
                        listScoreBonus.put(Integer.parseInt(((JSONObject) jsonChild)
                                .get(Constants.ID).toString()),
                                Double.parseDouble(((JSONObject) jsonChild)
                                .get(Constants.NICESCOREBONUS).toString()));
                        listElf.put(Integer.parseInt(((JSONObject) jsonChild)
                                .get(Constants.ID).toString()), (((String) ((JSONObject) jsonChild)
                                .get(Constants.ELF))));
                    }
                }
                JSONArray jsonGifts = (JSONArray) jsoninitialData.get(Constants.SANTAGIFTSLIST);
                if (jsonGifts != null) {
                    for (Object jsonGift : jsonGifts) {
                        giftList.add(new Gift((String) ((JSONObject) jsonGift)
                                .get(Constants.PRODUCTNAME),
                                Double.parseDouble(((JSONObject) jsonGift)
                                        .get(Constants.PRICE).toString()),
                                (String) ((JSONObject) jsonGift)
                                        .get(Constants.CATEGORY)
                                ));
                        quantity.put((String) ((JSONObject) jsonGift)
                                .get(Constants.PRODUCTNAME), Integer.parseInt(((JSONObject) jsonGift).get(Constants.QUANTITY).toString()));
                    }
                }
            } else {
                System.out.println("Nu exista date initiale");
            }
            if (jsonChangesAnnual != null) {
                for (Object jsonChange : jsonChangesAnnual) {
                    annualChanges.add(
                            new AnnualChanges(Double.parseDouble(((JSONObject) jsonChange)
                                    .get(Constants.NEWSANTABUDGET).toString()),
                            Utils.convertJSONArrayGift((JSONArray) ((JSONObject) jsonChange)
                                    .get(Constants.NEWGIFTS), quantity),
                            Utils.convertJSONArrayChildren((JSONArray) ((JSONObject) jsonChange)
                                    .get(Constants.NEWCHILDREN)),
                            Utils.convertJSONArrayChildUpdate((JSONArray) ((JSONObject) jsonChange)
                                    .get(Constants.CHILDRENUPDATES)),
                                    (String) ((JSONObject) jsonChange).get(Constants.STRATEGY)
                            ));
                }
            } else {
                System.out.println("Nu exista schimbari anuale");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new InputData(numberOfYears, santaBudget, childList,
                giftList, cityList, annualChanges, listScoreBonus, listElf, quantity);
    }
}
