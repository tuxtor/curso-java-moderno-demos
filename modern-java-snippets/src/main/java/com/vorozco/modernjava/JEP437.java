package com.vorozco.modernjava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JEP437 {

    public static void main(String[] args) throws Exception {
        System.out.println("(old) " + oldConcurrentFetch());
    }

    private static String oldConcurrentFetch() throws InterruptedException, ExecutionException {
        Callable<String> fetchUser = () -> "user-42";
        Callable<String> fetchOrder = () -> "order-99";

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            Future<String> userFuture = executor.submit(fetchUser);
            Future<String> orderFuture = executor.submit(fetchOrder);
            return userFuture.get() + " / " + orderFuture.get();
        } finally {
            executor.shutdown();
        }
    }
}
