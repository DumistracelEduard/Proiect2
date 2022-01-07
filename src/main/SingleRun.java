package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import run.Constants;
import run.DataStore.InputData;
import run.DataStore.InputLoader;
import run.InputClass.*;
import run.NiceScore;
import run.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class SingleRun {
    private static SingleRun instance = null;

    private SingleRun() {

    }

    /**
     * instanta pt Singleton
     * @return
     */
    public static SingleRun getInstance() {
        if (instance == null) {
            instance = new SingleRun();
        }
        return instance;
    }

    /**
     * creaza input si output-ul
     * @throws IOException
     */
    public void runProgram() throws IOException {
        final int number = 31;
        Path path = Paths.get(Constants.OUTDIRECTORY);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        for (int i = 1; i < number; ++i) {
            String output = Constants.OUTDIRECTORY + "/"
                    + Constants.OUTPATH + i + ".json";
            String input = Constants.PATHINPUT + "/test"
                    + i + ".json";
            run(input, output);
        }
    }

    /**
     * calculeaza data pt fiecare copil
     * @param children
     * @param santaBudget
     * @param listGift
     */
    public static void calculateData(final Children children, final double santaBudget,
                                     final HashMap<String, ArrayList<Gift>> listGift,
                                     final HashMap<Integer, Double> listScoreBonus) {
        double sum = 0;
        double budgetUnit, scoreBonus;
        for (Child child : children.getChildren()) {
            if (listScoreBonus.get(child.getId()) != null) {
                scoreBonus = listScoreBonus.get(child.getId());
                NiceScore niceScore = new NiceScore.Builder(child.getAge(),
                        child.getNiceScoreHistory())
                        .niceScoreBonus(scoreBonus)
                        .build();
                niceScore.calculateAverage();
                child.setAverageScore(niceScore.getNiceScore());
            }
            sum += child.getAverageScore();
        }
        budgetUnit = santaBudget / sum;
        Utils.distributionGift(budgetUnit, children, listGift);
    }

    /**
     * ruleaza programul si se realizeaza citirea si scrierea
     * @param input
     * @param output
     * @throws IOException
     */
    public static void run(final String input, final String output) throws IOException {
        InputLoader inputLoader = new InputLoader(input);
        InputData inputData = inputLoader.readData();

        Path path = Paths.get(output);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        System.out.println(input);
        HashMap<Integer, String> listElf = inputData.getListElf();
        List<Object> listGift = (List<Object>) inputData.getDataStore().get(1);
        HashMap<String, ArrayList<Gift>> gifts = Utils.convertObjectGift(listGift);
        Children children = new Children(inputData.getDataStore());
        calculateData(children, inputData.getSantaBudget(), gifts, inputData.getListScoreBonus());
        AnnualChildren annualChildren = new AnnualChildren(inputData.getNumberOfYears(), children);
        annualChildren.annualUpdate(inputData.getAnnualChanges(), annualChildren,
                gifts, inputData.getNumberOfYears(), inputData.getListScoreBonus());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(output), annualChildren);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
