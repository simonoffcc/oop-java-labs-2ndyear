package lab;

import java.util.Random;

public class Process {

    private static State state = State.RUNNING;
    private static final Object mutex = new Object();
    private static final Thread abstractProgram = new Thread(new AbstractProgram());

    static class AbstractProgram implements Runnable {

        @Override
        public void run() {
            Thread daemon = new Thread(() -> {
                while (true) {
                    Utils.pause(500, 5500);
                    if (abstractProgram.isInterrupted()) {
                        break;
                    }
                    synchronized (mutex) {
                        //System.out.println("�����: ��������� ��������� ������ " + state.toString());
                        state = State.values()[new Random().nextInt(State.values().length)];
                        if (state.equals(State.RUNNING)) {
                            System.out.println("�����$~ ��������� ������� � ��������� ��������. ���������: RUNNING");
                        } else {
                            System.out.println("�����$~ � ������� ��������� ��������� ��: " + state.toString());
                        }
                        mutex.notify();
                    }
                }
            });
            daemon.setDaemon(true);
            daemon.start();
            System.out.println("����������� ���������$~ � ���������� � ��������� ������!");

            while (!Thread.currentThread().isInterrupted()) {
                someWork();
            }
        }

        private void someWork() {
            int a = 0;
            a++;
        }
    }

    static class Supervisor implements Runnable {

        @Override
        public void run() {
            System.out.println("����������$~ � �����!");
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
                        case UNKNOWN, STOPPING -> runProgram();
                        default -> System.out.println("����������$~ � ������ �� ������)");
                    }
                }
            }
        }

        private void runProgram() {
            state = State.RUNNING;
            System.out.println("����������$~ � ������������ ���������");
        }

        private void stopProgram() {
            abstractProgram.interrupt();
            System.out.println("����������$~ � ��������� ���������");
        }
    }

    public static void main(String[] args) {
        new Thread(new Supervisor()).start();
    }
}
