package io.abhishek.concurrency.sync;

public class MathClass {
  public void printNumber(int n) throws InterruptedException {
    synchronized (this) {
      for(int i = 1;i <= n;i++) {
        System.out.println(Thread.currentThread().getName() + " : " + i);
        Thread.sleep(500);
      }
    }
  }
}
