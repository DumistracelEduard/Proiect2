package run.elf;

import run.InputClass.Child;

import java.util.HashMap;

public final class RunBlackOrPink {

    private RunBlackOrPink() { }
    /**
     * apeleaza functia de a verifica ce elf e si apeleaza metoda de calculare
     * specifica
     * @param child
     * @param elf
     */
    public static void run(final Child child, final HashMap<Integer, String> elf) {
        WitchPinkBlack witchPinkBlack = new WitchPinkBlack();
        BlackOrPink blackOrPink = witchPinkBlack.witch(child, elf);
        if (blackOrPink != null) {
            blackOrPink.run(child);
        }
    }
}
