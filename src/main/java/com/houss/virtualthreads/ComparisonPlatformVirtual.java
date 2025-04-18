package com.houss.virtualthreads;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparisonPlatformVirtual {

    public static void main(String[] args) {
      // for (int i = 0; i < 150000; i++) {
      //     Thread.ofPlatform().start(() -> {
      //         try {
      //             System.out.println("Started "+Thread.currentThread());
      //             Thread.sleep(Duration.ofSeconds(10));
      //         } catch (InterruptedException e) {
      //             throw new RuntimeException(e);
      //         }
      //     });
      // }
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10000000; i++) {

            executorService.submit(()-> {
                try {
                    System.out.println("Started "+Thread.currentThread());
                    Thread.sleep(Duration.ofSeconds(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
