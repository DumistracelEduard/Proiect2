package run.elf;

import run.InputClass.Child;

public class Pink implements BlackOrPink {
    /**
     * daca are elf pink se aduna 30%
     * @param child copil
     */
    @Override
    public void run(final Child child) {
        final int number1 = 30;
        final int number2 = 100;
        double buget = child.getAssignedBudget();
        buget += (buget * number1) / number2;
        child.setAssignedBudget(buget);
    }
}
