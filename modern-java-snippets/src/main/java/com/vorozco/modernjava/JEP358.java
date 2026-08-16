package com.vorozco.modernjava;

public class JEP358 {

    public static void main(String[] args) {
        System.out.println(helpfulNpeMessage());
    }

    /**
     * Since JDK 15, helpful NPE messages are enabled by default (JEP 358),
     * describing exactly which call/field access returned null. This method
     * triggers a real chained null dereference and reports the JVM-generated
     * message.
     */
    private static String helpfulNpeMessage() {
        Person person = new Person(null);
        try {
            int length = person.getAddress().getCity().length();
            return "unexpectedly got length " + length;
        } catch (NullPointerException e) {
            return e.getMessage();
        }
    }

    private static class Person {
        private final Address address;

        Person(Address address) {
            this.address = address;
        }

        Address getAddress() {
            return address;
        }
    }

    private static class Address {
        private final String city;

        Address(String city) {
            this.city = city;
        }

        String getCity() {
            return city;
        }
    }
}
