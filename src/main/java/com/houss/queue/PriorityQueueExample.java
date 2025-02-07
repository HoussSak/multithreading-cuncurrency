package com.houss.queue;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Houssem implements Comparable<Houssem> {

    private Integer id;
    private Integer amount;
    public Houssem(Integer id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Houssem{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Houssem o) {
        return this.id.compareTo(o.id);
    }
}


class PriorityWorker1 implements Runnable {

    private BlockingQueue<Houssem> queue;

    public PriorityWorker1(BlockingQueue<Houssem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            queue.put(new Houssem(10, 12));
            queue.put(new Houssem(1, 13));
            queue.put(new Houssem(4, 14));
            Thread.sleep(2000);
            queue.put(new Houssem(22, 22));
            Thread.sleep(1000);
            queue.put(new Houssem(17, 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PriorityWorker2 implements Runnable {

    private BlockingQueue<Houssem> queue;

    public PriorityWorker2(BlockingQueue<Houssem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class PriorityQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Houssem> queue = new PriorityBlockingQueue<>();

        PriorityWorker1 first = new PriorityWorker1(queue);
        PriorityWorker2 second = new PriorityWorker2(queue);

        new Thread(second).start();
        new Thread(first).start();
    }
}
