package com.vorozco.modernjava;

public class JEP358 {

  public static void main(String[] args) {
    Person person = new Person(null);
    try {
      int length = person.getAddress().getCity().length();
      IO.println("City length " + length);
    } catch (NullPointerException e) {
      IO.println(e.getMessage());
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
