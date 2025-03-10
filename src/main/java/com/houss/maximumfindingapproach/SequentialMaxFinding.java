package com.houss.maximumfindingapproach;

public class SequentialMaxFinding {

    public long max(long[] nums) {
        long max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }
}
