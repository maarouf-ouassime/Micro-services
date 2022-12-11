package com.example.bowlinggame.fizzbuzz;

public class FizzBuzz {

    public static String of(int i) {
        if (i == 0) {
            return "0";
        }
        return _of(i);
    }

    private static String _of(int i) {
        String result = "";
        if (i % 3 == 0) {
            result += "Fizz";
        }
        if (i % 5 == 0) {
            result += "Buzz";
        }
        return !result.isEmpty() ? result : String.valueOf(i);
    }
}
