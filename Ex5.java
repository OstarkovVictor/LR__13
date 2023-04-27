import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Ex5{
    private static final Object lock = new Object();
    private static int maxValue = 0;

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int arraySize = scanner.nextInt();
        int arrStep = arraySize / 4;

        int[] arr = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        System.out.println(Arrays.toString(arr));

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < arrStep; i++) {
                    if (maxValue < arr[i]) {
                        maxValue= arr[i];
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = arrStep; i < arrStep * 2; i++) {
                    if (maxValue < arr[i]) {
                        maxValue= arr[i];
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock) {
                for (int i = arrStep * 2; i < arrStep * 3; i++) {
                    if (maxValue < arr[i]) {
                        maxValue= arr[i];
                    }
                }
            }
        });
        Thread thread4 = new Thread(() -> {
            synchronized (lock) {
                for (int i = arrStep * 3; i < arr.length; i++) {
                    if (maxValue < arr[i]) {
                        maxValue= arr[i];
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        System.out.println("Максимальное значение массива: ");
        System.out.println(maxValue);
    }}