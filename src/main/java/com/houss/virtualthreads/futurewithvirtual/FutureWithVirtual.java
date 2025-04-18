package com.houss.virtualthreads.futurewithvirtual;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureWithVirtual {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<String> future = service.submit(new FutureTask());

        while (!future.isDone()) {
            System.out.println("Main thread is waiting for the result...");
        }

        // blocks the main thread
        String res = future.get();
        System.out.println(res);
    }
}
