package com.vorozco.modernjava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import java.util.concurrent.StructuredTaskScope;

public class JEP437 {

  public static void main(String[] args) throws Exception {
    // System.out.println("(old) " + oldConcurrentFetch());
    System.out.println("(new) " + newConcurrentFetch());
  }

  private static String oldConcurrentFetch() throws InterruptedException, ExecutionException {
    Callable<String> fetchUser = () -> {
      return "user-42";
    };
    Callable<String> fetchOrder = () -> {
      if (true) {
        throw new RuntimeException("Big fail");
      }
      return "order-99";
    };

    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    try {
      Future<String> userFuture = executor.submit(fetchUser);
      Future<String> orderFuture = executor.submit(fetchOrder);
      return userFuture.get() + " / " + orderFuture.get();
    } finally {
      executor.shutdown();
    }
  }

  private static String newConcurrentFetch() throws InterruptedException, ExecutionException {
    Callable<String> fetchUser = () -> {
      return "user-42";
    };
    Callable<String> fetchOrder = () -> {
      if (true) {
        throw new RuntimeException("Big fail");
      }
      return "order-99";
    };

    /*try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAll())) {
      var userTask = scope.fork(fetchUser);
      var orderTask = scope.fork(fetchOrder);

      scope.join();

      var user = userTask.state() == StructuredTaskScope.Subtask.State.SUCCESS ? userTask.get() : "Bad user";
      var order = orderTask.state() == StructuredTaskScope.Subtask.State.SUCCESS ? orderTask.get() : "Bad order";

      return user + "/" + order;
    }*/
    return null;

  }

}
