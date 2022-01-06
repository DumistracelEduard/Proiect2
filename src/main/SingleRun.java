package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import run.Constants;
import run.DataStore.InputData;
import run.DataStore.InputLoader;
import run.InputClass.AnnualChildren;
import run.InputClass.Child;
import run.InputClass.Children;
import run.InputClass.Gift;
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
        final int number = 19;
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
                                     final HashMap<String, ArrayList<Gift>> listGift) {
        double sum = 0;
        double budgetUnit;
        for (Child child : children.getChildren()) {
            child.calculateAverage();
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
        List<Object> listGift = (List<Object>) inputData.getDataStore().get(1);
        HashMap<String, ArrayList<Gift>> gifts = Utils.convertObjectGift(listGift);
        Children children = new Children(inputData.getDataStore());
        calculateData(children, inputData.getSantaBudget(), gifts);
        AnnualChildren annualChildren = new AnnualChildren(inputData.getNumberOfYears(), children);
        annualChildren.annualUpdate(inputData.getAnnualChanges(), annualChildren,
                gifts, inputData.getNumberOfYears());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(output), annualChildren);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
