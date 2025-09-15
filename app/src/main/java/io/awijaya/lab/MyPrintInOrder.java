package io.awijaya.lab;

import java.util.concurrent.Semaphore;

public class MyPrintInOrder {
    private final Semaphore secondSemaphore = new Semaphore(0);
    private final Semaphore thirdSemaphore = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondSemaphore.release(); // Signal that first() is done
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondSemaphore.acquire(); // Wait for first() to complete
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond.run();
        thirdSemaphore.release(); // Signal that second() is done
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdSemaphore.acquire(); // Wait for second() to complete
        // printThird() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
