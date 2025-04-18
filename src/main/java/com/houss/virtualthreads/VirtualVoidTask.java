package com.houss.virtualthreads;

public class VirtualVoidTask {

    public static void run() {

        System.out.println("Virtual task started: "+ Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Virtual task finished: "+ Thread.currentThread().getName());

    }
}
