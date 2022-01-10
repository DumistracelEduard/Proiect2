package run.InputClass;

import java.util.List;

public class AnnualChanges {
    private final double newSantaBudget;
    private final List<Gift> giftList;
    private final List<Child> newChildren;
    private final List<ChildUpdate> childrenUpdates;
    private final String strategy;

    /**
     * constructor
     * @param newSantaBudget noul buget
     * @param giftList lista de cadouri
     * @param newChildren lista noua de copii
     * @param childrenUpdates lista de update-uri
     */
    public AnnualChanges(final double newSantaBudget, final List<Gift> giftList,
                         final List<Child> newChildren, final List<ChildUpdate> childrenUpdates,
                         final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.giftList = giftList;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
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

    public final String getStrategy() {
        return strategy;
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
