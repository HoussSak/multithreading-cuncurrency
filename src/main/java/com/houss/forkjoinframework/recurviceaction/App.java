package com.houss.forkjoinframework.recurviceaction;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class App {
        /**
         * fork() - asynchronously executes the given tasks in the pool
         *          we can call it when using RecursiveTask<> or RecursiveAction
         * <p>
         * join() - returns the result of the computation when it is finished
         * <p>
         * invoke() - executes the given task + wait + return the result upon completion
         */
        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();
           // SimpleRecursiveAction action = new SimpleRecursiveAction(800);
           // action.invoke();

            List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
            SimpleRecursiveActionExercise action2 = new SimpleRecursiveActionExercise(integers);
            action2.invoke();



        }
}
