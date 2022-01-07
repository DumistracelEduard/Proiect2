package run;

import run.InputClass.AnnualChanges;
import run.InputClass.Children;
import run.InputClass.Gift;

import java.util.ArrayList;
import java.util.HashMap;

public class GiftDistribution {
    public void witchStrategy(final Children children, HashMap<String, ArrayList<Gift>> gifts,
                              AnnualChanges annualChanges){
        if (annualChanges.getStrategy().equals("id")) {

        } else if (annualChanges.getStrategy().equals("niceScore")) {

        } else if (annualChanges.getStrategy().equals("niceScoreCity")) {

        }
    }
}
