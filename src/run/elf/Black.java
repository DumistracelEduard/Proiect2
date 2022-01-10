package run.elf;

import run.InputClass.Child;

public class Black implements BlackOrPink {
    /**
     * scade 30% din buget daca are elf black
     * @param child
     */
    @Override
    public void run(final Child child) {
        final int number1 = 30;
        final int number2 = 100;
        double buget = child.getAssignedBudget();
        buget -= (buget * number1) / number2;
        child.setAssignedBudget(buget);
    }
}
