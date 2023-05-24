package com.example.demo.task3;

public class PrimeNumberChecker {
    public static boolean isPrime(Integer number) {
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i < number; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
