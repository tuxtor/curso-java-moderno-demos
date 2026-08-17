package com.vorozco.modernjava;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JEP 491: Synchronize Virtual Threads without Pinning Platform Threads
 * (finalized in JDK 24).
 * <p>
 * There is no meaningful "old way" to show as separate code: this is the
 * exact same source running on two different JVM versions. Before JDK 24, a
 * virtual thread blocking (e.g. on {@code Thread.sleep}) inside a
 * {@code synchronized} block stayed <em>pinned</em> to its carrier platform
 * thread, so the carrier couldn't run any other virtual thread meanwhile.
 * JEP 491 lets the virtual thread unmount instead, freeing the carrier.
 * <p>
 * This sample makes the difference measurable, not just described: it starts
 * many virtual threads that each sleep inside a {@code synchronized} block,
 * pinned to a single carrier thread. On JDK 24+ they all run concurrently
 * and finish in roughly one sleep's worth of time; on pre-JEP-491 JDKs the
 * same code would serialize and take {@code taskCount * sleep} time instead.
 */
public class JEP491 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(demonstrateNonPinningSynchronization());
    }

    private static String demonstrateNonPinningSynchronization() throws InterruptedException {
        int taskCount = 200;
        int sleepMillis = 50;

        Instant start = Instant.now();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            // Each task uses its own lock, so real contention is never the
            // bottleneck - only pinning behavior is being measured here.
            Object lock = new Object();
            threads.add(Thread.ofVirtual().start(() -> {
                synchronized (lock) {
                    try {
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException ignored) {
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.join();
        }
        Duration elapsed = Duration.between(start, Instant.now());

        return "Ran " + taskCount + " virtual threads (each sleeping " + sleepMillis
                + "ms inside a synchronized block) in " + elapsed.toMillis()
                + "ms. If pinning still happened, this would take roughly "
                + (taskCount * sleepMillis) + "ms instead - run with"
                + " -Djdk.virtualThreadScheduler.parallelism=1 to force a single carrier"
                + " thread and make the effect unmistakable.";
    }
}
