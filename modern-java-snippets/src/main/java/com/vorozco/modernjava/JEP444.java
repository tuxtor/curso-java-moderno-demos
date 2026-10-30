package com.vorozco.modernjava;

import com.vorozco.modernjava.jep444.RegularThreadsDemo;
import com.vorozco.modernjava.jep444.VirtualThreadsDemo;

import java.time.Duration;

public class JEP444 {

  public static void main(String[] args) throws InterruptedException {
    // System.out.println("(old) starting " + RegularThreadsDemo.threadCount()
    // + " platform threads, each sleeping 30s...");
    // Duration platformElapsed = RegularThreadsDemo.run();
    // System.out.println("(old) done in " + platformElapsed.toSeconds() + "s");

    System.out.println("(new) starting " + VirtualThreadsDemo.threadCount()
        + " platform threads, each sleeping 30s...");
    Duration virtualPlatformElapsed = VirtualThreadsDemo.run();
    System.out.println("(new) done in " + virtualPlatformElapsed.toSeconds() + "s");
  }
}
