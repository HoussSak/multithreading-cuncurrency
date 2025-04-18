package com.houss.virtualthreads.structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrecyOne {

    public static void main(String[] args) throws InterruptedException {

        // we do not pool virtual threads: we create new ones for every task
        // and we dispose them after they finished
        try(var scope = new StructuredTaskScope<String>()) {
            var process1 = new LongProcess(3, "result1");
            var process2 = new LongProcess(7, "result2");

            Subtask<String> res1 = scope.fork(process1);
            Subtask<String> res2 = scope.fork(process2);

            scope.join();
            // combine the results

            if (res1.state() == Subtask.State.SUCCESS) {
                System.out.println(res1.get());
            }

            if (res2.state() == Subtask.State.SUCCESS) {
                System.out.println(res2.get());
            }
            System.out.println(res1.get() + " - "+res2.get());


            // it will shut down the scope after all child threads terminate
        }
    }
}
