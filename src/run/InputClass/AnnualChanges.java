package run.InputClass;

import java.util.List;

public class AnnualChanges {
    private final double newSantaBudget;
    private final List<Gift> giftList;
    private final List<Child> newChildren;
    private final List<ChildUpdate> childrenUpdates;

    /**
     * constructor
     * @param newSantaBudget
     * @param giftList
     * @param newChildren
     * @param childrenUpdates
     */
    public AnnualChanges(final double newSantaBudget, final List<Gift> giftList,
                         final List<Child> newChildren, final List<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.giftList = giftList;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public final double getNewSantaBudget() {
        return newSantaBudget;
    }

    public final List<Gift> getGiftList() {
        return giftList;
    }

    public final List<Child> getNewChildren() {
        return newChildren;
    }

    public final List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    @Override
    public final String toString() {
        return "AnnualChanges{"
                + "NewSantaBudget=" + newSantaBudget
                + ", giftList=" + giftList
                + ", newChildren=" + newChildren
                + ", childrenUpdates=" + childrenUpdates
                + '}';
    }
}