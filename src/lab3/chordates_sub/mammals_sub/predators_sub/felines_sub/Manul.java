package lab3.chordates_sub.mammals_sub.predators_sub.felines_sub;

import lab3.StringConstants;
import lab3.chordates_sub.mammals_sub.predators_sub.Felines;

public final class Manul extends Felines {
    public Manul(String name) {
        this.name = name;
    }

    @Override
    public String getAnimalSpecies() {
        return StringConstants.SPECIES_MANUL;
    }
}
