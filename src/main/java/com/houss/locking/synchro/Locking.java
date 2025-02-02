package com.houss.locking.synchro;

public class Locking {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static int counter1 = 0;
    public static int counter2 = 0;

    public static void incrementCounter1() {
        // at the same time != parallel - CPU time slicing
        // there is a single intrinsic lock associated with every object or class in Java
        //
        //a given  thread that needs exclusive and consistent access to an object's fields
        //
        //has to acquire the object's intrinsic lock before accessing them,
        //
        //and then the thread releases the intrinsic lock when it's done with them
        //
        //with Locks: the acquired lock can be released any thread
        //
        //RLocks can be released by the thread that acquired it exclusively
        //
        //Ok so a thread cannot acquire a lock owned by another thread. But a given thread can acquire a lock that it already owns. Allowing a thread to acquire the same lock more than once is called re-entrant synchronization. And this is exactly what is happening in Python when using RLocks- the same thread may acquire the lock more than once.
        //
        //For example: let's consider recursive method calls. If a given thread calls a recursive and synchronized method several times then it is fine (note that in this case the same thread "enters" the synchronized block several times). There will be no deadlock because of re-entrant synchronization.
        synchronized(lock1) {
            counter1++;
        }
    }

    public static synchronized void incrementCounter2() {
        synchronized(lock2) {
            counter2++;
        }
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
