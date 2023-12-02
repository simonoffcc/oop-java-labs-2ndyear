package lab3;

public abstract class Chordates {

    protected String name = StringConstants.NO_NAME;

    public String getName() {
        return name;
    }

    public String getAnimalType() {
        return StringConstants.PHYLUM_CHORDATA;
    }
    public abstract String getAnimalClass();
    public abstract String getAnimalOrder();
    public abstract String getAnimalFamily();
    public abstract String getAnimalSpecies();

    @Override
    public String toString() {
        return getName() + ": " +
                getAnimalSpecies();
    }

    public String getHierarchy() {
        return getName() +
                ": " +
                getAnimalType() +
                " -> " +
                getAnimalClass() +
                " -> " +
                getAnimalOrder() +
                " -> " +
                getAnimalFamily() +
                " -> " +
                getAnimalSpecies();
    }
}
