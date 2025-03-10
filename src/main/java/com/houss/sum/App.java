package com.houss.sum;

import java.util.Random;

public class App {

    public static void main(String[] args) {

        int [] numbers1 = createArray(100000000);
        SequentialSum s = new SequentialSum();
        long startTime = System.currentTimeMillis();
        System.out.println("SUM: "+s.sum(numbers1));
        System.out.println("Time: "+ (System.currentTimeMillis() - startTime));


       int numOfThreads = Runtime.getRuntime().availableProcessors();
        ParallelSum parallelSum = new ParallelSum(numOfThreads);
        startTime = System.currentTimeMillis();
        System.out.println("Parallel sum: "+ parallelSum.sum(numbers1));
        System.out.println("Time " + (System.currentTimeMillis() - startTime));

    }

    private  static int [] createArray(int n) {
        Random r = new Random();
        int [] array = new int [n];
        for (int i = 0; i < n ; i++) {
            array[i] = r.nextInt(n);
        }
        return array;
    }
}
