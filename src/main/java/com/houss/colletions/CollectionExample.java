package com.houss.colletions;

import java.util.*;

public class CollectionExample {

    public static void main(String[] args) {
        // add() and remove() methods are synchronized
        // intrinsic lock - not that efficient because threads have to wait for each other even when the want to execute
        // independent methods (operations)
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());

        // Example for SET
        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        //Example fot MAP
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                nums.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                nums.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Size of nums: " + nums.size());
    }
}
