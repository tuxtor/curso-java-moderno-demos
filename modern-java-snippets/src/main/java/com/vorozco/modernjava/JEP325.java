package com.vorozco.modernjava;

public class JEP325 {

    public static void main(String[] args) {
        System.out.println(oldSwitchStatement("WEDNESDAY"));
        System.out.println(oldSwitchStatement("SATURDAY"));
        System.out.println(oldSwitchWithBlockBody("TUESDAY"));
    }

    private static String oldSwitchStatement(String day) {
        int numLetters;
        switch (day) {
            case "MONDAY":
            case "FRIDAY":
            case "SUNDAY":
                numLetters = 6;
                break;
            case "TUESDAY":
                numLetters = 7;
                break;
            case "THURSDAY":
            case "SATURDAY":
                numLetters = 8;
                break;
            case "WEDNESDAY":
                numLetters = 9;
                break;
            default:
                numLetters = 0;
        }
        return day + " (old) -> " + numLetters;
    }

    private static String oldSwitchWithBlockBody(String day) {
        String result;
        switch (day) {
            case "TUESDAY": {
                StringBuilder sb = new StringBuilder();
                sb.append(day).append(" is day #2");
                result = sb.toString();
                break;
            }
            default:
                result = day + " is not Tuesday";
        }
        return "(old) " + result;
    }
}
