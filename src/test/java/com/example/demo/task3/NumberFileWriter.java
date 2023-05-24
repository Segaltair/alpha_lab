package com.example.demo.task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NumberFileWriter implements Runnable {
    private final String filename;
    private final String resultFilename;
    private final SerialNumberGenerator generator;

    public NumberFileWriter(String filename, String resultFilename, SerialNumberGenerator generator) {
        this.filename = filename;
        this.resultFilename = resultFilename;
        this.generator = generator;
    }

    @Override
    public void run() {
        File file = FileUtils.createFile(filename);

        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            while (generator.hasNext()) {
                int nextInt = generator.generateNext();
                boolean isPrime = PrimeNumberChecker.isPrime(nextInt);
                while (!isPrime && generator.hasNext()) {
                    nextInt = generator.generateNext();
                    isPrime = PrimeNumberChecker.isPrime(nextInt);
                }
                if (isPrime) {
                    bw.append(Integer.toString(nextInt)).append(", ");
                    bw.flush();
                    writeToResultFile(nextInt);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private synchronized void writeToResultFile(Integer value) {
        try (FileWriter fw = new FileWriter(FileUtils.getResultFile(resultFilename), true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.append(value.toString()).append(", ");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
