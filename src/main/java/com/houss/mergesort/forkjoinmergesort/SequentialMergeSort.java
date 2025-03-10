package com.houss.mergesort.forkjoinmergesort;

public class SequentialMergeSort {

    private int [] nums;
    //merge sort is not an in-place algorithm
    private int [] tempArray;

    public SequentialMergeSort(int [] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void sort() {
        mergeSort(0, nums.length-1);
    }
    //DIVIDE ANS CONQUER APPROACH
    private void mergeSort(int lowIndex, int highIndex) {
        //DIVIDE PHASE
        //base-case
        if (lowIndex>= highIndex)
            return;
        //middle item
        int middleIndex = (lowIndex+highIndex)/2;

        //recursively on both sub-arrays, we keep splitting the problem into smaller and smaller
        // sub-problems until a given array contains just 1 item
        mergeSort(lowIndex, middleIndex); // here we generate a left sub-array
        mergeSort(middleIndex+1, highIndex); // here we generate a right sub-array

        // CONQUER PHASE
        //we have to combine the sub-solutions
        merge(lowIndex, middleIndex, highIndex);
    }

    private void merge(int lowIndex, int middleIndex, int highIndex) {
        //COPY THE ITEMS INTO THE TEMPORARY ARRAY
        for (int i = lowIndex; i <= highIndex ; i++) {
            tempArray[i] = nums[i];
        }

        int i = lowIndex;
        int j = middleIndex+1;
        int k = lowIndex;

        //We consider the temp array and copy the items into the nums array

        while (i<= middleIndex && j<= highIndex) {
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            ++k;
        }

        // we have to copy the items from the left sub-array ( if there are any)
        while (i<= middleIndex) {
            nums[k] = tempArray[i];
            ++k;
            i++;
        }

        // we have to copy the items from the right sub-array ( if there are any)
        while (j<= highIndex) {
            nums[k] = tempArray[j];
            ++k;
            j++;
        }


    }

    public void  showArray() {
        for (int num : nums) {
            System.out.println(num + " ");
        }
        //Arrays.stream(nums).forEach(System.out::println);
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
