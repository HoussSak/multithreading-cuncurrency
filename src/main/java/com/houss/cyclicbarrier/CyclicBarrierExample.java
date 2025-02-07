package com.houss.cyclicbarrier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.*;

class Worker implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker(int id,CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.random = new Random();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID: " + id + " starts the work... " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS")));


        try {
            TimeUnit.SECONDS.sleep(random.nextInt(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException  e) {
            e.printStackTrace();
        }

        System.out.println("After the await()");
    }
}
public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()-> {
            System.out.println("All Tasks have been finished...");
        });

        for (int i = 0; i < 5; ++i) {
            service.execute(new Worker(i+1,cyclicBarrier));
        }

        service.shutdown();

    }
}
