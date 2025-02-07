package com.houss.exchanger;


import java.util.concurrent.Exchanger;

class FirstExchangerThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;
    public FirstExchangerThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("FirstExchangerThread incremented the counter: " + counter);
            try {
               counter = exchanger.exchange(counter);
                System.out.println("FirstExchangerThread get the counter: " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class SecondExchangerThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;
    public SecondExchangerThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("SecondExchangerThread decrement the counter: " + counter);
            try {
                counter = exchanger.exchange(counter);
                System.out.println("SecondExchangerThread get the counter: " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
public class ExchangerExample {
    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new FirstExchangerThread(exchanger)).start();
        new Thread(new SecondExchangerThread(exchanger)).start();

    }
}
