package lab;

import java.util.Random;

public class Process {

    private static State state = State.UNKNOWN;
    private static final Object mutex = new Object();
    private static final Thread abstractProgram = new Thread(new AbstractProgram());

    static class AbstractProgram implements Runnable {
        @Override
        public void run() {
            System.out.println("Демон$~ Состояние демона перед запуском программы: " + state + ".");
            Thread daemon = new Thread(() -> {
                while (true) {
                    Utils.pause(500, 5500);
                    if (abstractProgram.isInterrupted()) {
                        break;
                    }
                    synchronized (mutex) {
                        do {
                            state = State.values()[new Random().nextInt(State.values().length)];
                        } while (state == State.UNKNOWN);

                        if (state.equals(State.RUNNING)) {
                            System.out.println("Демон$~ Программе повезло и состояние осталось. Состояние: RUNNING.");
                        } else {
                            System.out.println("Демон$~ Я изменил состояние программы на: " + state + ".");
                        }
                        mutex.notify();
                    }
                }
            });
            daemon.setDaemon(true);
            daemon.start();
            System.out.println("Абстрактная программа$~ я заработала и запустила демона!");

            while (!Thread.currentThread().isInterrupted()) {
                someWork();
            }
        }

        private void someWork() {
            int amogus = 0;
            amogus++;
        }
    }

    static class Supervisor implements Runnable {
        @Override
        public void run() {
            System.out.println("Супервизор$~ я встал!");
            abstractProgram.start();
            while (!abstractProgram.isInterrupted()) {
                synchronized (mutex) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (state) {
                        case FATAL_ERROR -> stopProgram();
                        case STOPPING -> runProgram();
                        default -> System.out.println("Супервизор$~ Я ничего не сделал.");
                    }
                }
            }
        }

        private void runProgram() {
            state = State.RUNNING;
            System.out.println("Супервизор$~ Я перезапустил программу.");
        }

        private void stopProgram() {
            abstractProgram.interrupt();
            System.out.println("Супервизор$~ Я остановил программу.");
        }
    }

    public static void main(String[] args) {
        new Thread(new Supervisor()).start();
    }
}
