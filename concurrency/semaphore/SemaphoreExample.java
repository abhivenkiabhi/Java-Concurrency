package io.abhishek.concurrency.semaphore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintingJob implements Runnable {
  private PrinterQueue printerQueue;

  public PrintingJob(PrinterQueue printerQueue) {
    this.printerQueue = printerQueue;
  }
  public void run() {
    System.out.println("Document Printing started : " + Thread.currentThread().getName());
    printerQueue.printJob(new Object());
  }
}
class PrinterQueue {
  // in binary semaphore semaphore size is 1. semaphore = new semaphore(1)
  private static int MAX_LIMIT = 3;
  private final Semaphore semaphore;
  private final Lock printerLock;
  private boolean[] freePrinters;

  public PrinterQueue() {
    semaphore = new Semaphore(MAX_LIMIT);
    printerLock = new ReentrantLock();
    freePrinters = new boolean[MAX_LIMIT];
    Arrays.fill(freePrinters, true);
  }

  public void printJob(Object document) {
    try {
      semaphore.acquire();
      int assignedPrinter = getPrinter();
      System.out.println("Printing document for " + Thread.currentThread().getName() + " at " + LocalDateTime.now().toString());
      Thread.sleep(2000);
      releasePrinter(assignedPrinter);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    } finally {
      System.out.println("Printing finished for " + Thread.currentThread().getName());
      semaphore.release();
    }
  }

  public int getPrinter() {
    int foundPrinter = -1;
    try {
      printerLock.lock();
      for (int i = 0; i < MAX_LIMIT; i++) {
        if (freePrinters[i] == true) {
          foundPrinter = i;
          freePrinters[i] = false;
          break;
        }
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    } finally {
      printerLock.unlock();
    }
    return foundPrinter;
  }

  public void releasePrinter(int i) {
    printerLock.lock();
    freePrinters[i] = true;
    printerLock.unlock();
  }

}
public class SemaphoreExample {
  public static void main(String[] args) {
    PrinterQueue printerQueue = new PrinterQueue();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for(int i = 0;i < 10;i++) {
      PrintingJob job = new PrintingJob(printerQueue);
      executorService.execute(job);
    }
    executorService.shutdown();
  }
}
