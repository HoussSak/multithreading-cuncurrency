package com.houss.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // Approach #1
        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("Virtual-", 0);

        Thread t1 = builder.start(new VirtualTask());
        Thread t2 = builder.start(new VirtualTask());
        t1.join();
        t2.join();

        // Approach #2

        ThreadFactory factory = Thread.ofVirtual().name("Virtual-", 0).factory();

        Thread t12 = factory.newThread(new VirtualTask());
        Thread t22 = factory.newThread(new VirtualTask());
        t12.start();
        t22.start();
        t1.join();
        t2.join();

        // All virtual threads are daemon threads !!!

        // if we don't have anything to return we don't have to implement the runnable interface
        Thread.Builder.OfVirtual builderVoid = Thread.ofVirtual().name("Virtual-", 0);

        Thread t1v = builder.start(VirtualVoidTask::run);
        Thread t2v = builder.start(VirtualVoidTask::run);
        t1v.join();
        t2v.join();

        // The best approach
        // Try with resource approach
        try(var service = Executors.newVirtualThreadPerTaskExecutor()) {
            service.submit(VirtualVoidTask::run);
            service.submit(VirtualVoidTask::run);
            service.submit(VirtualVoidTask::run);
        }




    }
}
