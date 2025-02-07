package com.houss.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    // Le deadlock, ou interblocage, c’est quand deux trucs (ou plus) se bloquent mutuellement en attendant que l’autre lâche ce qu’il détient,
    // et personne ne peut avancer. Imagine une situation où deux potes, disons Alice et Bob, sont dans une cuisine.

    // Alice a besoin d'une cuillère et Bob d'une fourchette pour manger,
    // mais il n’y a qu’une cuillère et qu’une fourchette.

    //Alice prend la cuillère et attend que Bob lâche la fourchette.
    //Bob prend la fourchette et attend qu’Alice lâche la cuillère.
    //Ni Alice ni Bob ne vont lâcher ce qu’ils ont avant d’obtenir ce que l’autre détient,
    // donc ils sont bloqués à attendre indéfiniment. C'est ça un deadlock.

    //Dans ton code, voici ce qui se passe :

    //worker1 commence par prendre lock1 et ensuite tente de prendre lock2.
    //worker2 commence par prendre lock2 et ensuite tente de prendre lock1.
    //Si worker1 a le lock1 et attend le lock2, et en même temps worker2 a le lock2 et attend le lock1,
    // ils se retrouvent bloqués sans fin, chacun attendant que l’autre lâche son verrou.
    // C’est le problème classique de la cyclic dependency en programmation
    // où deux processus se retrouvent en attente circulaire d’une ressource détenue par l'autre.

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        Deadlock deadlock = new Deadlock();

        new Thread(deadlock::worker1,"worker1").start();
        new Thread(deadlock::worker2,"worker2").start();

    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker 1 acquires the lock 1...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock2.lock();
        System.out.println("Worker 2 acquires the lock 2...");
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock();
        System.out.println("Worker 2 acquires the lock 2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock1.lock();
        System.out.println("Worker 2 acquires the lock 1...");
        lock1.unlock();
        lock2.unlock();
    }
}
