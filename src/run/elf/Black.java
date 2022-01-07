package run.elf;

import run.InputClass.Child;

public class Black implements BlackOrPink {
    @Override
    public void run(Child child) {
        double buget = child.getAverageScore();
        buget -= (buget * 30) / 100;
        child.setAverageScore(buget);
    }
}
