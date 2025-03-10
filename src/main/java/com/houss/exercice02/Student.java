package com.houss.exercice02;

import java.util.Random;

public class Student implements Runnable {

    private int id;
    private Book[] books;
    private Random random;

    private volatile boolean done;

    public Student(int id, Book [] books) {
        this.id =id;
        this.books = books;
        this.random = new Random();
    }

    @Override
    public void run() {

        while (!done) {
            System.out.println(done);
            int bookId = random.nextInt(Constants.NUMBER_Of_BOOKS);
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Student #"+ id;
    }
}
