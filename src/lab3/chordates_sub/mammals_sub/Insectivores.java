package lab3.chordates_sub.mammals_sub;

import lab3.StringConstants;
import lab3.chordates_sub.Mammals;

public abstract class Insectivores extends Mammals {
    @Override
    public String getAnimalOrder() {
        return StringConstants.ORDER_INSECTIVORES;
    }
}
