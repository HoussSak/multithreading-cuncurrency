package com.houss.virtualthreads.futurewithvirtual;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FutureTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("FutureTask started");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("FutureTask finished");
        return "FutureTask finished";
    }
}
