package com.example.demo.task3;

public class SerialNumberGenerator {
    private volatile int currentValue = 2;
    private final Integer maxValue;

    public SerialNumberGenerator(Integer maxValue) {
        if (maxValue < 2) {
            throw new RuntimeException("maxValue must be larger than 1!");
        }
        this.maxValue = maxValue;
    }

    public boolean hasNext() {
        return currentValue < maxValue;
    }

    public synchronized int generateNext() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
