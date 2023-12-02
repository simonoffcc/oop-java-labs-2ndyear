package lab3;

import lab3.chordates_sub.mammals_sub.insectivores_sub.hedgehogs_sub.CommonHedgehog;
import lab3.chordates_sub.mammals_sub.predators_sub.felines_sub.Lynx;
import lab3.chordates_sub.mammals_sub.predators_sub.felines_sub.Manul;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        List<Chordates> animalCollection = new ArrayList<>();
        List<CommonHedgehog> commonHedgehogCollection = new ArrayList<>();
        List<Manul> manulCollection = new ArrayList<>();
        List<Lynx> lynxCollection = new ArrayList<>();

        animalCollection.add(new CommonHedgehog("1 имя"));
        animalCollection.add(new CommonHedgehog("2 имя"));
        animalCollection.add(new Manul("3 имя"));
        animalCollection.add(new Lynx("4 имя"));
        animalCollection.add(new Lynx("5 имя"));
        animalCollection.add(new Lynx("6 имя"));
        animalCollection.add(new CommonHedgehog("7 имя"));

        //segregate(Млекопитающие, Ежовые,        Кошачьи, Хищные)
        //segregate(Хищные,        Хордовые,      Манулы,  Кошачьи)
        //segregate(Ежовые,        Насекомоядные, Хищные,  Хищные)
        segregate(animalCollection, commonHedgehogCollection, manulCollection, lynxCollection);

        System.out.println("Ежи: " + commonHedgehogCollection);
        System.out.println("Манулы: " + manulCollection);
        System.out.println("Рыси: " + lynxCollection);


    }

    public static void segregate(Collection<? extends Chordates> srcCollection,
                                 Collection<? super CommonHedgehog> collection1,
                                 Collection<? super Manul> collection2,
                                 Collection<? super Lynx> collection3) {
        for (Chordates animal: srcCollection) {
            if (animal instanceof CommonHedgehog) {
                collection1.add((CommonHedgehog) animal);
            } else if (animal instanceof Manul) {
                collection2.add((Manul) animal);
            } else if (animal instanceof Lynx) {
                collection3.add((Lynx) animal);
            }
        }
    }
}
