package com.houss.forkjoinframework.recurvitask;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        SimpleRecursiveTask task = new SimpleRecursiveTask(200);
        System.out.println(pool.invoke(task));

    }
}
