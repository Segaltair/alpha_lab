package com.example.demo.task3;

import org.junit.jupiter.api.Test;

/**
 * Необходимо написать программу, запускающую 2 одинаковых потока (или задания). Потоки работают параллельно (одновременно).
 * Каждый поток должен дописывать в один и тот же файл на диске (Result.txt) разделённые пробелами текстовые записи простых чисел, упорядоченных по возрастанию и ограниченных сверху числом 1000000 (миллион).
 * Числа в файле не должны дублироваться или быть пропущенными.
 * В отдельном собственном файле (Thread1.txt или Thread2.txt) каждый поток должен сохранить те числа (так же через пробел), которые записал в общий файл (Result.txt) именно он.
 * Простое число — это натуральное число, имеющее ровно два различных натуральных делителя единицу и самого себя.
 * Пример, как могли бы выглядеть файлы по окончании работы программы, если вместо 1000000 использовать ограничение 20:
 * Result.txt
 * 2 3 5 7 11 13 17 19
 * Thread1.txt
 * 2 3 5 13 19
 * Thread2.txt
 * 7 11 17
 */
public class Task3Test {
    /**
     * Результат вычислений будет в target/test-classes/task3
     */
    @Test
    public void test() throws InterruptedException {
        String resultFilename = "Result.txt";
        FileUtils.createFile(resultFilename);
        SerialNumberGenerator generator = new SerialNumberGenerator(1000000);

        Thread t1 = new Thread(new NumberFileWriter("Thread1.txt", resultFilename, generator));
        Thread t2 = new Thread(new NumberFileWriter("Thread2.txt", resultFilename, generator));

        long start = java.lang.System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long executedTime = java.lang.System.currentTimeMillis() - start;
        System.out.printf("Executed time: %d milliseconds%n", executedTime);
    }
}
