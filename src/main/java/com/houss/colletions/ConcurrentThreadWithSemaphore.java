package com.houss.colletions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;



public class ConcurrentThreadWithSemaphore {
    private static final int NUMBER_OF_TEAMS = 3;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // Semaphore avec un seul permis

        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool avec 2 threads

        // Soumission de deux tâches, une pour chaque thread, avec le semaphore pour la séquence
        executor.submit(() -> launchPreparation("Batch 1", semaphore));
        executor.submit(() -> launchPreparation("Batch 2", semaphore));

        executor.shutdown();
    }

    public static void launchPreparation(String batchName, Semaphore semaphore) {
        try {
            semaphore.acquire(); // Acquérir le permis pour bloquer le deuxième thread tant que le premier n'a pas fini
            CountDownLatch latch = new CountDownLatch(NUMBER_OF_TEAMS);
            System.out.println(batchName + " commence.");

            // Création et démarrage des threads pour chaque équipe
            Thread team1 = new Thread(new PreparationTask(latch, batchName + " - Équipe Logistique"));
            Thread team2 = new Thread(new PreparationTask(latch, batchName + " - Équipe Sécurité"));
            Thread team3 = new Thread(new PreparationTask(latch, batchName + " - Équipe Technique"));
            team1.start();
            team2.start();
            team3.start();

            // Attente que toutes les équipes aient fini
            latch.await();
            System.out.println(batchName + ": Toutes les équipes sont prêtes. L'événement peut commencer !");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Libération du permis pour permettre au suivant de commencer
        }
    }

    static class PreparationTask implements Runnable {
        private CountDownLatch latch;
        private String teamName;

        public PreparationTask(CountDownLatch latch, String teamName) {
            this.latch = latch;
            this.teamName = teamName;
        }

        @Override
        public void run() {
            try {
                prepare();
                latch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void prepare() throws InterruptedException {
            System.out.println(teamName + " commence la préparation.");
            Thread.sleep((long) (Math.random() * 3000));
            System.out.println(teamName + " a terminé la préparation.");
        }
    }
}

