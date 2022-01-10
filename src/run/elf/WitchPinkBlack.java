package run.elf;

import run.InputClass.Child;

import java.util.HashMap;

public class WitchPinkBlack {
    /**
     * verifica ce fel de tip e de elf
     * @param child copil
     * @param elf lista de elfi
     * @return
     */
    public BlackOrPink witch(final Child child, final HashMap<Integer, String> elf) {
        if (elf.get(child.getId()) == null) {
            return null;
        }
        if (elf.get(child.getId()).equals("yellow")) {
            return null;
        }
        if (elf.get(child.getId()).equals("black")) {
            return new Black();
        } else if (elf.get(child.getId()).equals("pink")) {
            return new Pink();
        }
        return null;
    }
}
