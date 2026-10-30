package com.vorozco.modernjava;

public class JEP513 {

  public static void main(String[] args) {
    System.out.println(oldValidatedAccount(100));
    System.out.println(newValidatedAccount(100));

    try {
      oldValidatedAccount(-5);
    } catch (IllegalArgumentException e) {
      System.out.println("(old) rejected: " + e.getMessage());
    }
    try {
      newValidatedAccount(-5);
    } catch (IllegalArgumentException e) {
      System.out.println("(new) rejected: " + e.getMessage());
    }
  }

  static String oldValidatedAccount(int balance) {
    return new OldSavingsAccount(balance).toString();
  }

  static String newValidatedAccount(int balance) {
    return new NewSavingsAccount(balance).toString();
  }

  private static class Account {
    final int balance;

    Account(int balance) {
      this.balance = balance;
    }

    @Override
    public String toString() {
      return "Account[balance=" + balance + "]";
    }
  }

  private static class OldSavingsAccount extends Account {
    OldSavingsAccount(int balance) {
      super(validate(balance));
    }

    private static int validate(int balance) {
      if (balance < 0) {
        throw new IllegalArgumentException("balance cannot be negative");
      }
      return balance;
    }
  }

  private static class NewSavingsAccount extends Account {
    NewSavingsAccount(int balance) {
      if (balance < 0) {
        throw new IllegalArgumentException("balance cannot be negative");
      }
      super(balance);
    }
  }
}
