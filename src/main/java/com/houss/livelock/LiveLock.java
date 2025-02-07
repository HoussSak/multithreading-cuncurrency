package com.houss.livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);


    public static void main(String[] args) {
        LiveLock liveLock = new LiveLock();

        new Thread(liveLock::Worker1,"worker1").start();
        new Thread(liveLock::Worker2,"worker2").start();
    }

    private void Worker1() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker1 acquires the lock1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (lock2.tryLock()) {
                System.out.println("Worker1 acquires the lock2");
                lock2.unlock();
            } else {
                System.out.println("Worker1 can not acquire lock2");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }
    private void Worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker2 acquires the lock2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (lock1.tryLock()) {
                System.out.println("Worker2 acquires the lock1");
                lock1.unlock();
            } else {
                System.out.println("Worker2 can not acquire lock1");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }
}
