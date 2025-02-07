package com.houss.colletions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker2 implements Runnable {

    private int id;
    private CountDownLatch latch;

    public Worker2(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
        } finally {
            latch.countDown();
            System.out.println("Worker " + id + " has finished work. Remaining count: " + latch.getCount());
        }
    }

    private void doWork() {
        try {
            System.out.println("Worker " + id + " starts working.");
            if (Math.random() > 0.5) {
                throw new RuntimeException("Simulated error");
            }
            Thread.sleep(1000); // Simulating work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class SimulationWithTryFinally {

    public static void main(String[] args) {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);
        ExecutorService service = Executors.newFixedThreadPool(numberOfWorkers);

        for (int i = 0; i < numberOfWorkers; i++) {
            service.execute(new Worker2(i, latch));
        }

        service.shutdown();
        System.out.println("Main thread continues immediately without waiting for workers to finish.");
    }
}
