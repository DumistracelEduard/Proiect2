package run.InputClass;

import run.elf.Yellow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static main.SingleRun.calculateData;

public final class AnnualChildren {
    private final List<Children> annualChildren;

    /**
     * constructor
     * @param numberOfYears numarul de ani
     * @param children lista de copii
     */
    public AnnualChildren(final Integer numberOfYears, final Children children) {
        this.annualChildren = new ArrayList<>(numberOfYears + 1);
        this.annualChildren.add(0, children);
    }

    /**
     * adaug fiecare copil care nu apare in lista si este sub 19 ani
     * @param children lista de copii
     * @param newChildren lista noua de copii
     */
    public void updateChild(final Children children, final List<Child> newChildren) {
        int ok;
        final int number = 18;
        for (Child newChild: newChildren) {
            ok = 0;
            for (Child child : children.getChildren()) {
                if (child.getLastName().equals(newChild.getLastName())
                        && child.getFirstName().equals(newChild.getFirstName())
                        && child.getAge().equals(newChild.getAge())) {
                    ok = 1;
                }
                if (newChild.getAge() > number) {
                    ok = 1;
                }
            }
            if (ok == 0) {
                children.getChildren().add(newChild);
            }
        }
        children.getChildren().sort(Comparator.comparingInt(Child::getId));

    }

    /**
     * adaug cadourile careapar in urm ani
     * @param listGift lista de cadouri
     * @param newGifts lista de cadouri noi
     */
    public void updateGift(final HashMap<String, ArrayList<Gift>> listGift,
                           final List<Gift> newGifts) {
        for (Gift newGift: newGifts) {
            if (listGift.containsKey(newGift.getCategory())) {
                ArrayList<Gift> list = listGift.get(newGift.getCategory());
                list.add(newGift);
                list.sort(Comparator.comparingDouble(Gift::getPrice));
            } else {
                ArrayList<Gift> newCategory = new ArrayList<>();
                newCategory.add(newGift);
                listGift.put(newGift.getCategory(), newCategory);
            }
        }
    }

    /**
     * updatam datele la fiecare copil
     * @param childUpdates lista de update copii
     * @param children lista de copii
     * @param elf lista de elfi
     */
    public void updateDataChild(final List<ChildUpdate> childUpdates,
                                final Children children,
                                final HashMap<Integer, String> elf) {
        for (ChildUpdate childUpdate: childUpdates) {
            for (Child child: children.getChildren()) {
                if (child.getId().equals(childUpdate.getId())) {
                    elf.put(childUpdate.getId(), childUpdate.getElf());
                    child.updateGiftPreferences(childUpdate.getGiftsPreference());
                    child.addNiceScore(childUpdate.getNiceScore());
                }
            }
        }
    }

    /**
     * realizez update-ul pentru fiecare an si salvez fiecare lista
     * de copii in children
     * @param annualChanges schimbari anuale
     * @param children lista de copii
     * @param listGift lista de cadouri
     * @param numberOfYears numarul de ani
     * @param elf lista de elfi
     * @param listScoreBonus lista de scoruri bonus
     * @param quantityYear cantitatea pe fiecare an
     */
    public void annualUpdate(final List<AnnualChanges> annualChanges,
                             final AnnualChildren children,
                             final HashMap<String, ArrayList<Gift>> listGift,
                             final int numberOfYears,
                             final HashMap<Integer, Double> listScoreBonus,
                             final HashMap<Integer, String> elf,
                             final List<HashMap<String, Integer>> quantityYear) {
        double santaBudget;
        for (int i = 0; i < numberOfYears; ++i) {
            santaBudget = annualChanges.get(i).getNewSantaBudget();
            Children children1 = new Children(children.getAnnualChildren().get(i));
            updateGift(listGift, annualChanges.get(i).getGiftList());
            updateChild(children1, annualChanges.get(i).getNewChildren());
            updateDataChild(annualChanges.get(i).getChildrenUpdates(), children1, elf);
            calculateData(children1, santaBudget, listGift, listScoreBonus, elf,
                    annualChanges.get(i), quantityYear.get(i + 1), 1, quantityYear);
            for (Child child: children1.getChildren()) {
                Yellow.yellowRun(child, elf.get(child.getId()), listGift,
                        quantityYear.get(i), quantityYear);
            }
            children.getAnnualChildren().add(children1);
        }
    }

    public List<Children> getAnnualChildren() {
        return annualChildren;
    }
}
