package com.houss.streamapi.parallelstream;

import java.util.stream.IntStream;

public class App02 {

    public static void main(String[] args) {

        // sequential stream
        long start = System.currentTimeMillis();
        long numOfPrimesSeq = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                .filter(App02::isPrime).count();
        System.out.println(numOfPrimesSeq);
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));


        // parallel stream
         start = System.currentTimeMillis();
        long numOfPrimesParallel = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                .filter(App02::isPrime).parallel().count();
        System.out.println(numOfPrimesParallel);
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));

    }

    public static boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        // we can check the numbers in the range [0, square(N)]

        long maxDivisor = (long) Math.sqrt(num);
        for (int i = 3; i <= maxDivisor; i+=2) { // i+=2 ==> 3,5,7...
            if (num % i == 0) {
                return false;
            }

        }
        return true;
    }
}
