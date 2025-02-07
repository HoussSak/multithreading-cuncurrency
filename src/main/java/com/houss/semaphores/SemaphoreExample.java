package com.houss.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;
    private Semaphore semaphore = new Semaphore(5, true);

    public void download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private void downloadData() {
        try {
            Thread.sleep(2000);
            System.out.println("Downloading data...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SemaphoreExample {

    // It is used to control access to a shared resource that uses a counter variable

    // semaphore maintains a set of permits


    public static void main(String[] args) {
        //Create Multiple threads - executors
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) {
            service.execute(Downloader.INSTANCE::download);
        }
    }
}
