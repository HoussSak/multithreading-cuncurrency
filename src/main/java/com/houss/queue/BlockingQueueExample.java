package com.houss.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> queue;

    public FirstWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;

        while(true) {
            try {
                queue.put(counter);
                System.out.println("Putting item " + counter);
                counter++;
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> queue;

    public SecondWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while(true) {
            try {
                Integer counter = queue.take();

                System.out.println("Taking item " + counter);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

public class BlockingQueueExample {

    /**
     * Queue has a FIFO structure (first in first out) but it is not
     *      a synchronized data structure !! ----> NOT A THREAD SAFE !!
     * <p>
     * BlockingQueue -> an interface that represents a queue that is thread safe
     *      put items or take items from it ... ----> IT IS A THREAD SAFE !!!
     * <p>
     *      for example: one thread putting items into the queue and another
     *      thread taking items from it at the same time !!!
     * <p>
     *              We can fo the producer-consumer pattern !!!
     * <p>
     *      put() putting items to the queue
     *      take() taking items from the queue
     */

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(new FirstWorker(queue)).start();
        new Thread(new SecondWorker(queue)).start();

    }
}
