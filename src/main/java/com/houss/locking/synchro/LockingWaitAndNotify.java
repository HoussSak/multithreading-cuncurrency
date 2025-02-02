package com.houss.locking.synchro;


class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the Produce method...");
            wait();
            System.out.println("Again in the Produce method...");
        }
    }

    public void consume() throws InterruptedException {

        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume method is executed...");
            notify();
            // it is not going to handle the lock: we can make further operations
            Thread.sleep(3000);
        }

    }
}
public class LockingWaitAndNotify {

    public static void main(String[] args) {
        Process p = new Process();

        Thread t1 = new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
