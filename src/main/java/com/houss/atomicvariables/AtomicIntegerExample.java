package com.houss.atomicvariables;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        AtomicIntegerExample atomicIntegerExample = new AtomicIntegerExample();

        //new Thread(atomicInteger::increment, "thread1").start();
        //new Thread(atomicInteger::increment, "thread2").start();

       Thread t1 =  new Thread(()-> {
            increment();
        });

        Thread t2 =  new Thread(()-> {
            increment();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(t1.getState());

        System.out.println("Counter: " + count);
    }

    public static void increment() {

        for (int i = 0; i < 10000; i++) {
            count.getAndIncrement();
        }
    }
}
