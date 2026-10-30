package com.vorozco.modernjava.jep444;

import java.time.Duration;
import java.time.Instant;

public class VirtualThreadsDemo {

  private static final int THREAD_COUNT = 800;
  private static final Duration SLEEP_DURATION = Duration.ofSeconds(3000);

  public static Duration run() throws InterruptedException {
    Instant start = Instant.now();

    Thread[] threads = new Thread[THREAD_COUNT];
    for (int i = 0; i < THREAD_COUNT; i++) {
      int taskId = i;

      threads[i] = Thread.ofPlatform()
          .name("virtual-worker-" + taskId)
          .unstarted(() -> sleepTask(taskId));
    }
    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    return Duration.between(start, Instant.now());
  }

  private static void sleepTask(int taskId) {
    try {
      Thread.sleep(SLEEP_DURATION);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public static int threadCount() {
    return THREAD_COUNT;
  }
}
