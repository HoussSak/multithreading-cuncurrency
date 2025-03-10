package com.houss.maximumfindingapproach;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        long [] nums = createNumbers(300000000);

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();

        long start = System.currentTimeMillis();
        System.out.println("Max: "+sequentialMaxFinding.max(nums));
        System.out.println("Time: " +(System.currentTimeMillis() - start));

        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums,0,nums.length);
        start = System.currentTimeMillis();
        System.out.println("Max: "+forkJoinPool.invoke(parallelMaxFinding));
        System.out.println("Time: " +(System.currentTimeMillis() - start));


    }

    private static long[] createNumbers(int n) {
        Random rand = new Random();
        long[] numbers = new long[n];
        for (int i =0;i<numbers.length; ++i) {
            numbers[i] = rand.nextInt();

        }
        return numbers;
    }
}
