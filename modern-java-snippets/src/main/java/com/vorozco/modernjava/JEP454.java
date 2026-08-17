package com.vorozco.modernjava;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

/**
 * JEP 454: Foreign Function {@literal &} Memory API (finalized in JDK 22).
 * <p>
 * There is no meaningful "old way" to show here: before the FFM API the only
 * option to call native code from Java was JNI, which requires writing and
 * compiling a separate C glue library just to call one function. The whole
 * point of JEP 454 is that we no longer need that glue code, so this sample
 * only demonstrates the new approach: calling the C standard library's
 * {@code getpid()} function directly, with no parameters and no native
 * memory management at all &mdash; the simplest possible native call.
 */
public class JEP454 {

    public static void main(String[] args) throws Throwable {
        System.out.println("PID from native getpid(): " + nativeProcessId());
        System.out.println("PID from ProcessHandle:   " + ProcessHandle.current().pid());
    }

    /**
     * Looks up {@code getpid()} in the platform's C standard library and
     * calls it directly, without writing or compiling a single line of C.
     * {@code getpid} takes no arguments and returns a {@code pid_t}, which on
     * Linux is a plain 32-bit {@code int}.
     */
    private static int nativeProcessId() throws Throwable {
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();

        MethodHandle getpid = linker.downcallHandle(
                stdlib.find("getpid").orElseThrow(),
                FunctionDescriptor.of(JAVA_INT));

        return (int) getpid.invoke();
    }
}
