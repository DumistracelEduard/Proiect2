package run.InputClass;

import java.util.ArrayList;

public class ChildUpdate {
    private final Integer id;
    private final double niceScore;
    private final ArrayList<String> giftsPreference;


    public ChildUpdate(final Integer id, final double niceScore,
                       final ArrayList<String> giftsPreference) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
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
}
