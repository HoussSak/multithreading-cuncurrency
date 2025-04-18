package com.houss.virtualthreads.completablefeaturevirtual;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletablefeatureVirtual {

    public static void main(String[] args) {
        ExecutorService ioExecutor = Executors.newFixedThreadPool(5);
        ExecutorService cpuExecutor = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(()-> "Hello World!", cpuExecutor)
                .thenApplyAsync(String::toUpperCase, ioExecutor)
                .thenApply(s -> s+ " something")
                .thenAccept(System.out::println);

        // Combine the result from multiple completableFeatures
        CompletableFuture.supplyAsync(()-> "Hello ")
                .thenCombine(CompletableFuture.supplyAsync(()-> "World"), (s1,s2) -> s1+" - "+s2)
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);


    }
}
