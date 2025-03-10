package com.houss.exercice01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    private int id;
    private  volatile boolean full;
    private ChopStick leftStick;
    private ChopStick rightStick;
    private Random rand;

    public int getEatingCounter() {
        return eatingCounter;
    }

    private int eatingCounter;

    public Philosopher(int id, ChopStick leftStick, ChopStick rightStick) {
        this.id = id;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            //after eating a lot (1000 times) then we will terminate the given thread
            while (!full) {
                think();
                if (leftStick.pickUp(this, State.LEFT)) {
                    //The philosopher is able to acquire the left chopstick
                    if (rightStick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightStick.putDown(this, State.RIGHT);
                    }
                    leftStick.putDown(this, State.LEFT);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void think() throws InterruptedException {
        System.out.println(this+ " is thinking...");
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this+ " is eating...");
        eatingCounter++;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
    }
    public  void setFull(boolean full) {
        this.full = full;
    }

    public boolean isFull() {
        return this.full;
    }

    @Override
    public String toString() {
        return "Philosopher "+id;
    }
}
