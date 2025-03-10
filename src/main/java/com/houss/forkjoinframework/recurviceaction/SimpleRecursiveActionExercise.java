package com.houss.forkjoinframework.recurviceaction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveActionExercise extends RecursiveAction {

    List<Integer> nums;
    public SimpleRecursiveActionExercise(List<Integer> nums) {
        this.nums = nums;
    }
    @Override
    protected void compute() {
        if (nums.size() > 2) {
            System.out.println("The size of PARALLEL the task: "+nums.size());

            System.out.println("Parallel execution and split the tasks...");
            int mid = nums.size() / 2;
            SimpleRecursiveActionExercise task1 = new SimpleRecursiveActionExercise(nums.subList(0, mid));
            SimpleRecursiveActionExercise task2 = new SimpleRecursiveActionExercise(nums.subList(mid, nums.size()));
            invokeAll(task1, task2);

        }else {
            for(Integer num : nums)
                System.out.println(num);
        }

    }
}
