package com.vorozco.modernjava;

public class JEP358 {

  public static void main(String[] args) {
    Person person = new Person(null);
    int length = person.getAddress().getCity().length();
    System.out.println("City length " + length);
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
