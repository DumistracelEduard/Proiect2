package run.elf;

import run.InputClass.Child;

public class Pink implements BlackOrPink {
    @Override
    public void run(Child child) {
        double buget = child.getAssignedBudget();
        buget += (buget * 30) / 100;
        child.setAssignedBudget(buget);
    }
}
