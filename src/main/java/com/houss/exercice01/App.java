package com.houss.exercice01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    //The aim of the simulation is to avoid thread starvation
    //We are able to avoid deadlocks because we use trylock();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = null;
        Philosopher [] philosophers = null;
        ChopStick [] chopsticks;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new ChopStick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new ChopStick(i);
            }
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.submit(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }

        } finally {

            executorService.shutdown();
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher philosopher : philosophers) {
                System.out.println(philosopher+ " ate #"+philosopher.getEatingCounter()+ " times");
            }

        }

    }
}
