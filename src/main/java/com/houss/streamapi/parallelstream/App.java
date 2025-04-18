package com.houss.streamapi.parallelstream;

import java.util.stream.LongStream;

public class App {

    public static void main(String[] args) {
        // parallel() - because we have to make sure that the given steam can be parallelized
        // under the hood the fork-join framework is used
        long start = System.currentTimeMillis();
        System.out.println(sum(150));
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(parallelSum(150));
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));
    }


    private static Long sum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    private static Long parallelSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }


}
