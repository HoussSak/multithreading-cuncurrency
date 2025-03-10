package com.houss.exercice02;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        Student [] students = null;
        Book [] books = null;
        ExecutorService service = null;


        try {
            books = new Book[Constants.NUMBER_Of_BOOKS];
            students = new Student[Constants.NUMBER_Of_STUDENTS];

            for (int i=0; i <Constants.NUMBER_Of_BOOKS; i++) {
                books[i] = new Book(i+1);
            }
            service = Executors.newFixedThreadPool(Constants.NUMBER_Of_STUDENTS);

            for (int i = 0; i < Constants.NUMBER_Of_STUDENTS; i++) {
                students[i] = new Student(i+1, books);
                service.submit(students[i]);
            }

            TimeUnit.MILLISECONDS.sleep(Constants.SIMULATION_RUNNING_TIME);
            Arrays.stream(students).forEach(s -> s.setDone(true));

         }  finally {
            service.shutdown();
            while (!service.isTerminated()) {
                Thread.sleep(1000);
            }
        }

    }
}
