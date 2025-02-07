package com.houss.copyonwritearrays;

import java.util.List;
import java.util.Random;

public class WriteTask implements Runnable {

    private List<Integer> list;
    private Random rand;
    public WriteTask(List<Integer> list) {
        this.list = list;
        this.rand = new Random();
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(rand.nextInt(list.size()), rand.nextInt(10));
        }

    }
}
