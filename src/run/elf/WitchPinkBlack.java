package run.elf;

import run.InputClass.Child;

import java.util.HashMap;

public class WitchPinkBlack {
    public BlackOrPink witch(Child child, HashMap<Integer, String> elf) {
        if(elf.get(child.getId()) == null) {
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
