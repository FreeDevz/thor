package io.awijaya.lab;

public class MyFibonacciSequence {

    public static void printFibo(int times) {

        int firstFibo = 0;
        int secondFibo = 1;
        System.out.println(firstFibo);
        System.out.println(secondFibo);

        for (int i = 2; i < times; i++) {
            int sum = firstFibo + secondFibo;
            firstFibo = secondFibo;
            secondFibo = sum;
            System.out.println(sum);
        }
    }

    public static void printFiboRecursive(int times) {
        int firstFibo = 0;
        int secondFibo = 1;
        System.out.println(firstFibo);
        System.out.println(secondFibo);

        printFiboRecursive(firstFibo, secondFibo, times - 2);
    }

    public static void printFiboRecursive(int firstFibo, int secondFibo, int times) {
        if (times > 0) {
            int sum = firstFibo + secondFibo;
            System.out.println(sum);

            printFiboRecursive(secondFibo, sum, times - 1);
        }
    }


    public static void main(String[] args) {
//        printFibo(10); // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
        printFiboRecursive(10);
    }
}
