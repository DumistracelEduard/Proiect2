package run.elf;

import run.InputClass.Child;

import java.util.HashMap;

public class RunBlackOrPink {
    public static void run(Child child, HashMap<Integer, String> elf) {
        WitchPinkBlack witchPinkBlack = new WitchPinkBlack();
        BlackOrPink blackOrPink = witchPinkBlack.witch(child, elf);
        blackOrPink.run(child);
    }
}
