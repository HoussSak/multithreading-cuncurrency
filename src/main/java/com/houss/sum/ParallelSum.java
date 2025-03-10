package com.houss.sum;

public class ParallelSum {
    private ParallelWorker [] workers;
    private int numOfThreads;

    public ParallelSum(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        workers = new ParallelWorker[numOfThreads];
    }

    public int sum(int [] nums) {
        int size = (int) Math.ceil(Math.log(nums.length) / Math.log(2));

        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new ParallelWorker(nums, i*size, (i+1)*size);
            workers[i].start();
        }

        try{
                for(var parWorker : workers) {
                    parWorker.join();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int total = 0;
        for (var worker : workers) {
            total += worker.getPartialSum();
        }
        return total;

    }
}
