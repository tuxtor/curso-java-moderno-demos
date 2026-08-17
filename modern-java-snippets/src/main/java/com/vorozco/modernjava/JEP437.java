package com.vorozco.modernjava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

public class JEP437 {

    public static void main(String[] args) throws Exception {
        System.out.println("(old) " + oldConcurrentFetch());
        System.out.println("(new) " + newConcurrentFetch());
    }

    /**
     * Before structured concurrency, running two tasks in parallel and
     * combining their results meant managing an ExecutorService and Futures
     * by hand - and if one task failed, the other kept running unless you
     * remembered to cancel it yourself.
     */
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

    /**
     * JEP 437's StructuredTaskScope ties the lifetime of forked subtasks to
     * a single try-with-resources block: join() waits for both, and if one
     * fails, allSuccessfulOrThrow() cancels the sibling automatically -
     * concurrent work is treated as a single structured unit.
     */
    private static String newConcurrentFetch() throws InterruptedException, ExecutionException {
        Callable<String> fetchUser = () -> "user-42";
        Callable<String> fetchOrder = () -> "order-99";

        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow())) {
            var userTask = scope.fork(fetchUser);
            var orderTask = scope.fork(fetchOrder);

            scope.join();

            return userTask.get() + " / " + orderTask.get();
        }
    }
}
