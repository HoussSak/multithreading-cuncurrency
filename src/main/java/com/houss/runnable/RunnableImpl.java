package com.houss.runnable;

class Runner1 implements Runnable {

    @Override
    public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runner1: "+i);
            }
        }

}

class Runner2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: "+i);
        }

    }
}

public class RunnableImpl {
    public static void main(String[] args) {
       //Multithreading
       Thread t1 = new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               System.out.println("Runner1: "+i);
           }
       });
       Thread t2 = new Thread(()-> {
           for (int i = 0; i < 10; i++) {
               System.out.println("Runner2: "+i);
           }
       });

      t1.start();
      t2.start();
    }
}