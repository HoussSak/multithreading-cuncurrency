package com.houss.sum;

public class SequentialSum {
    //Linear running time algorithm O(N)
    public int sum(int [] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
