package com.houss.maximumfindingapproach;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxFinding(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        //if the array in small - then we use sequential approach
        if (highIndex - lowIndex < 1000) {
            return sequentialMaxFinding();
        } else {
            int mid = (lowIndex + highIndex) / 2;
            ParallelMaxFinding left = new ParallelMaxFinding(nums, lowIndex, mid);
            ParallelMaxFinding right = new ParallelMaxFinding(nums, mid + 1, highIndex);
            invokeAll(left, right);
            return Math.max(left.join(), right.join());
        }
    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIndex];
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }

        return max;
    }
}
