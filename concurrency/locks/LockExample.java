package io.abhishek.concurrency.locks;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class PrintingJob implements Runnable {
  private final PrinterQueue printerQueue;

  public PrintingJob(PrinterQueue printerQueue) {
    this.printerQueue = printerQueue;
  }
  public void run() {
    try {
      System.out.println("Going to Print job for thread " + Thread.currentThread().getName());
      printerQueue.printJob(new Object());
      Thread.sleep(100);
    } catch(InterruptedException exp) {
      exp.printStackTrace();
    }
  }
}
class PrinterQueue {
  private final Lock queueLock = new ReentrantLock();
  public void printJob(Object document) {
   queueLock.lock();
   try {
     Long duration = (long) (Math.random() * 10000);
     System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds :: Time - " + new Date());
     Thread.sleep(duration);
   } catch(InterruptedException exp) {
     exp.printStackTrace();
   } finally {
     System.out.println("The document has been printed" + Thread.currentThread().getName());
     queueLock.unlock();
    }
  }
}
public class LockExample {
  public static void main(String[] args) {
    PrinterQueue printerQueue = new PrinterQueue();
    Thread[] threads = new Thread[10];
    for(int i = 0;i < 10;i++) {
      threads[i] = new Thread(new PrintingJob(printerQueue), "Thread " + i);
    }
    for (int i = 0; i < 10; i++)
    {
      threads[i].start();
    }
  }
}
