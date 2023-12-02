package lab3.chordates_sub;

import lab3.Chordates;
import lab3.StringConstants;

public abstract class Mammals extends Chordates {
    @Override
    public String getAnimalClass() {
        return StringConstants.CLASS_MAMMALS;
    }
}
