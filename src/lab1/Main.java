package lab1;

import lab1.move.MovingByFlightStrategy;
import lab1.move.MovingByFootStrategy;
import lab1.move.MovingByHorseRidingStrategy;
import lab1.move.MovingByTeleportStrategy;
import lab1.move.MovingByDrivingCarStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Simon", new MovingByFootStrategy());

        Scanner sc = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.println("Select moving strategy:\n"
                    + "> foot\n"
                    + "> riding\n"
                    + "> fly\n"
                    + "> drive\n"
                    + "> teleport\n"
                    + "For exit type 'exit'"
            );
            answer = sc.next();
            if ("exit".equals(answer)) {
                System.out.println("Exiting the program.\n");
                break;
            } else if ("foot".equals(answer)) {
                hero.setMoveStrategy(new MovingByFootStrategy());
                hero.move();
            } else if ("riding".equals(answer)) {
                hero.setMoveStrategy(new MovingByHorseRidingStrategy());
                hero.move();
            } else if ("fly".equals(answer)) {
                hero.setMoveStrategy(new MovingByFlightStrategy());
                hero.move();
            } else if ("drive".equals(answer)) {
                hero.setMoveStrategy(new MovingByDrivingCarStrategy());
                hero.move();
            } else if ("teleport".equals(answer)) {
                hero.setMoveStrategy(new MovingByTeleportStrategy());
                hero.move();
            } else {
                System.out.println("Hero can't move!\n");
            }
        }
        sc.close();
    }
}
