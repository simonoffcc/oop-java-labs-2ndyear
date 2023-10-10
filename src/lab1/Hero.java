package lab1;

import lab1.move.MoveStrategy;

public class Hero {
    private final String name;
    private MoveStrategy moveStrategy;

    public Hero(String name, MoveStrategy moveStrategy) {
        this.name = name;
        this.moveStrategy = moveStrategy;
    }

    String getName() {
        return this.name;
    }

    void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    void move() {
        System.out.print(name + "-> ");
        moveStrategy.move();
    }
}
