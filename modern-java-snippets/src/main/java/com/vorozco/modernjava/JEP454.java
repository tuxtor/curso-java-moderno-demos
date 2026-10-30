package com.vorozco.modernjava;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

public class JEP454 {

  public static void main(String[] args) throws Throwable {
    System.out.println("PID from native getpid(): " + nativeProcessId());
    System.out.println("PID from ProcessHandle:   " + ProcessHandle.current().pid());
  }

  private static int nativeProcessId() throws Throwable {
    Linker linker = Linker.nativeLinker();
    SymbolLookup stdlib = linker.defaultLookup();

    MethodHandle getpid = linker.downcallHandle(
        stdlib.find("getpid").orElseThrow(),
        FunctionDescriptor.of(JAVA_INT));

    return (int) getpid.invoke();
  }
}
