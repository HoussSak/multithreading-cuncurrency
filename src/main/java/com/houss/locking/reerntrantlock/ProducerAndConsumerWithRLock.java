package com.houss.locking.reerntrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Wotker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " produce");
        System.out.println("Produce method...");
        condition.await();
        System.out.println("Again the produce method...");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        //we want to make sure that we start with the producer()
        Thread.sleep(2000);
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " consume");
        System.out.println("Consume method...");
        Thread.sleep(3000);
        //Notify
        condition.signal();
        lock.unlock();
    }
}

public class ProducerAndConsumerWithRLock {

    public static void main(String[] args) {
        Wotker w = new Wotker();


    Thread t1 = new Thread(() ->  {
        try {
            w.produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    });

    Thread t2 = new Thread(() ->  {
        try {
            w.consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    });

    t1.start();
    t2.start();

    try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    }


}
