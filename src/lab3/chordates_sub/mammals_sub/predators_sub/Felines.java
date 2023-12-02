package lab3.chordates_sub.mammals_sub.predators_sub;

import lab3.StringConstants;
import lab3.chordates_sub.mammals_sub.Predators;

public abstract class Felines extends Predators {
    @Override
    public String getAnimalFamily() {
        return StringConstants.FAMILY_FELINES;
    }
}
