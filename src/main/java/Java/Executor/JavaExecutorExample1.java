package Java.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaExecutorExample1 {

    public static void main(String[] args) {
        ExecutorService exec1 = Executors.newSingleThreadExecutor();
        exec1.submit(() -> {
            task1();
            exec1.shutdown();
        });
        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        exec2.submit(() -> {
            task2();
            exec2.shutdown();
        });
        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        exec3.submit(() -> {
            task3();
            exec3.shutdown();
        });

    }

    public static void task1() {
        for (int i = 0; i < 30; i++) {
            System.out.println("Task 1: " + i);
        }
    }

    public static void task2() {
        for (int i = 0; i < 30; i++) {
            System.out.println("Task 2: " + i);
        }
    }

    public static void task3() {
        for (int i = 0; i < 30; i++) {
            System.out.println("Task 3: " + i);
        }
    }

}
