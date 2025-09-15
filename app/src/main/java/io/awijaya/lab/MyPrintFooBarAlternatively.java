package io.awijaya.lab;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode.com/problems/print-foobar-alternately/
 */
public class MyPrintFooBarAlternatively {
    private int n;

    public MyPrintFooBarAlternatively(int n) {
        this.n = n;
    }

    private final Semaphore fooSemaphore = new Semaphore(1);
    private final Semaphore barSemaphore = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSemaphore.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barSemaphore.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooSemaphore.release();
        }
    }
}
