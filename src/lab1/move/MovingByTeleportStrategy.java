package lab1.move;

public class MovingByTeleportStrategy implements MoveStrategy {

    @Override
    public void move() {
        System.out.println("I'm teleporting!\n");
    }
}
