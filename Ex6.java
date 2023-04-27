import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class Ex6 {
        private static final Object lock = new Object();
        private static int arrSum = 0;

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
                    int sum1 = 0;
                    for (int i = 0; i < arrStep; i++) {
                        sum1 = sum1 + arr[i];
                    }
                    arrSum = arrSum + sum1;
                }
            });

            Thread thread2 = new Thread(() -> {
                synchronized (lock) {
                    int sum2 = 0;
                    for (int i = arrStep; i < arrStep * 2; i++) {
                        sum2 = sum2 + arr[i];
                    }
                    arrSum = arrSum + sum2;
                }
            });

            Thread thread3 = new Thread(() -> {
                synchronized (lock) {
                    int sum3 = 0;
                    for (int i = arrStep * 2; i < arrStep * 3; i++) {
                        sum3 = sum3 + arr[i];
                    }
                    arrSum = arrSum + sum3;
                }
            });
            Thread thread4 = new Thread(() -> {
                synchronized (lock) {
                    int sum4 = 0;
                    for (int i = arrStep * 3; i < arr.length; i++) {
                        sum4 = sum4 + arr[i];
                    }
                    arrSum = arrSum + sum4;
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

            System.out.println("Сумма элементов массива найденное четырьмя потоками через цикл for: ");
            System.out.println(arrSum);
        }
    }
}