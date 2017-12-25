package Java.Executor;

/**
 * Ejemplo sobre ejecución de instrucciones de manera secuencial
 *
 * @author Antony García González, de Proyecto Panama Hitek. 
 * Visita http://panamahitek.com
 */
public class SecuentialExample1 {

    public static void main(String[] args) {
        //Ejecución de los procesos de manera secuencial
        task1();
        task2();
        task3();
    }

    public static void task1() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Task 1: " + i);
        }
    }

    public static void task2() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Task 2: " + i);
        }
    }

    public static void task3() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Task 3: " + i);
        }
    }
}
