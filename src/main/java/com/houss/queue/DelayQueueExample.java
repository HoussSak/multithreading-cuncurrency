package com.houss.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    public static void main(String[] args) {
        BlockingQueue<DelayWorker> queue = new DelayQueue<>();
        try {
            //These can be inserted by different threads
            queue.put(new DelayWorker(2000, "This is the first message..." ));
            queue.put(new DelayWorker(10000, "This is the second message..." ));
            queue.put(new DelayWorker(4500, "This is the third message..." ));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //We can get the messages
        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class DelayWorker implements Delayed {

    private long duration;
    private String message;

    public DelayWorker(long duration, String message) {
        this.duration = System.currentTimeMillis()  +  duration;
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // This is the method that can compare objects
        // -1, 1 or 0

        if (duration < ((DelayWorker) o).getDuration()) {
            return -1;
        }

        if (duration > ((DelayWorker) o).getDuration()) {
            return +1;
        }
        return 0;
    }
}
