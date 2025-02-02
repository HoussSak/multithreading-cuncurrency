package com.houss.threadpriority;

class Worker implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
       // System.out.println(Thread.currentThread().getName());
       // Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
       // System.out.println(Thread.currentThread().getPriority());

        //////

        Thread thread = new Thread(new Worker());
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        //Main thread with priority 5
        System.out.println("This is in the main thread");
    }
}
