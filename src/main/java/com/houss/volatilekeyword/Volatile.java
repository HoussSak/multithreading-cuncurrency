package com.houss.volatilekeyword;

class Worker implements Runnable {

    // it will be stored in the main memory (RAM) and not the cache of the current thread
    private volatile boolean terminated;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting for termination");
        while (!terminated) {
            System.out.println("Working class is running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public boolean isTerminated() {
        return terminated;
    }
}
public class Volatile {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        System.out.println(Thread.currentThread().getName() + " thread started");
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        worker.setTerminated(true);
        System.out.println("Algorithm is terminated");

    }
}
