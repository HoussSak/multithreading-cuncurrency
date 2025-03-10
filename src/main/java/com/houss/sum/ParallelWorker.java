package com.houss.sum;

public class ParallelWorker extends Thread {
    private int [] nums;
    private int lowIndex;
    private int highIndex;
    private int partialSum;

    public ParallelWorker(int [] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = Math.min(nums.length, highIndex);
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i = lowIndex; i < highIndex; i++) {
            partialSum += nums[i];
        }
    }

    public int getPartialSum() {
        return partialSum;
    }
}
