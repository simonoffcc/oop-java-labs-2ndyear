package lab3.chordates_sub.mammals_sub.insectivores_sub.hedgehogs_sub;

import lab3.StringConstants;
import lab3.chordates_sub.mammals_sub.insectivores_sub.Hedgehogs;

public final class CommonHedgehog extends Hedgehogs {

    public CommonHedgehog(String name) {
        this.name = name;
    }

    @Override
    public String getAnimalSpecies() {
        return StringConstants.SPECIES_COMMON_HEDGEHOG;
    }
}
