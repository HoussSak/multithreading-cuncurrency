package com.houss.synchronization;

public class Synchronization {
    public static int counter1 = 0;
    public static int counter2 = 0;

    // with the synchronized keyword, we make sure that the method is executed only by one single thread
    // at a given time
    public static void incrementCounter1() {

        //Class level locking
        // Rule of thumb: we syncho blocks that are 100% necessary
        synchronized(Synchronization.class) {
            counter1++;
        }
    }

    public static synchronized void incrementCounter2() {
        counter2++;
    }

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementCounter1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementCounter2();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter 1: " + counter1);
        System.out.println("Counter 2: " + counter2);

    }

    public static void main(String[] args) {
        process();

    }
}
