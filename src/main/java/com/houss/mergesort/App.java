package com.houss.mergesort;

import java.util.Random;

public class App {
    public static void main(String[] args) {

        //PARALLEL MERGE SORT
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        int [] numbers1 = createArray(100000000);
        int [] numbers2 = new int[numbers1.length];

        System.arraycopy(numbers1, 0, numbers2, 0, numbers1.length);

        long starTime = System.currentTimeMillis();
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(numbers1);
        parallelMergeSort.parallelMergeSort(0, numbers1.length-1, numberOfThreads);
        long endtime = System.currentTimeMillis();

        System.out.printf("Time taken with parallel: %3d ms\n", endtime - starTime);

        //SEQUENTIAL MERGE SORT

        starTime = System.currentTimeMillis();
        MergeSort mergeSort = new MergeSort(numbers2);
        mergeSort.sort();
        endtime = System.currentTimeMillis();
        System.out.printf("Time taken with sequential: %3d ms\n", endtime - starTime);


    }

    private static int[] createArray(int n) {
        Random random = new Random();
        int [] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(n);
        }
        return a;
    }
}
