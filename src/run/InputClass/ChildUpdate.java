package run.InputClass;

import java.util.ArrayList;

public class ChildUpdate {
    private final Integer id;
    private final double niceScore;
    private final ArrayList<String> giftsPreference;
    private final String elf;

    public ChildUpdate(final Integer id, final double niceScore,
                       final ArrayList<String> giftsPreference,
                       final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
        this.elf = elf;
    }

    public final Integer getId() {
        return id;
    }

    public final double getNiceScore() {
        return niceScore;
    }

    public final ArrayList<String> getGiftsPreference() {
        return giftsPreference;
    }

    public final String getElf() {
        return elf;
    }
}
