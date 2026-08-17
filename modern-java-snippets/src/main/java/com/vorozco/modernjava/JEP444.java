package com.vorozco.modernjava;

import com.vorozco.modernjava.jep444.RegularThreadsDemo;

import java.time.Duration;

public class JEP444 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("(old) starting " + RegularThreadsDemo.threadCount()
                + " platform threads, each sleeping 30s...");
        Duration platformElapsed = RegularThreadsDemo.run();
        System.out.println("(old) done in " + platformElapsed.toSeconds() + "s");
    }
}
