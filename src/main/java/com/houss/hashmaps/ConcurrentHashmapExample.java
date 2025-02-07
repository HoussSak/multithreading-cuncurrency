package com.houss.hashmaps;

import java.util.concurrent.ConcurrentHashMap;

class MapFirstWorker implements Runnable {

    private ConcurrentHashMap<String,Integer> map;
    public MapFirstWorker(ConcurrentHashMap<String,Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z", 5);
            map.put("A", 25);
            Thread.sleep(2000);
            map.put("D", 19);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MapSecondWorker implements Runnable {

    private ConcurrentHashMap<String,Integer> map;
    public MapSecondWorker(ConcurrentHashMap<String,Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(2000);
            System.out.println(map.get("Z"));
            System.out.println(map.get("B"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


public class ConcurrentHashmapExample {

    public static void main(String[] args) {

        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        new Thread(new MapFirstWorker(map)).start();
        new Thread(new MapSecondWorker(map)).start();

    }
}
