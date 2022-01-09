package run.InputClass;

import run.Utils;

import java.util.ArrayList;
import java.util.List;

public final class Children {
    private final List<Child> children;

    public Children(final Children children) {
        final int number = 18;
        this.children = new ArrayList<>();
        for (Child child : children.getChildren()) {
            if (child.getAge() < number) {
                this.children.add(new Child(child));
            }
        }
    }

    public Children(final List<Object> dataStore) {
        this.children = Utils.convertObject((List<Object>) dataStore.get(0));
        for (Child child: children) {
            child.checkPreferences();
        }
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Children{" +
                "children=" + children +
                '}';
    }
}
