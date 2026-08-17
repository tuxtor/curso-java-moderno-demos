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

    private static String oldValidatedAccount(int balance) {
        return new OldSavingsAccount(balance).toString();
    }

    private static String newValidatedAccount(int balance) {
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

    /**
     * Before JEP 513, validation logic could not run before the mandatory
     * {@code super(...)} call, so it had to be smuggled into a private
     * static helper method just to reject bad input early.
     */
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

    /**
     * JEP 513 allows statements - like validation, or simple logging -
     * before the {@code super(...)}/{@code this(...)} call, as long as they
     * don't touch the instance being constructed. No helper method needed.
     */
    private static class NewSavingsAccount extends Account {
        NewSavingsAccount(int balance) {
            if (balance < 0) {
                throw new IllegalArgumentException("balance cannot be negative");
            }
            super(balance);
        }
    }
}
