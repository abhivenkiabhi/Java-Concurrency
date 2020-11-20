package io.abhishek.concurrency.threadlocal;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

class Task implements Runnable {
  private final static AtomicInteger nextId = new AtomicInteger(0);
  private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
    public Integer initialValue() {
      return nextId.getAndIncrement();
    }
  };

  public int getThreadId() {
    return threadId.get();
  }
  private static final ThreadLocal<Date> startDate = new ThreadLocal<>() {
    public Date initialValue() {
      return new Date();
    }
  };
  public void run() {
    System.out.println("Starting Thread " + getThreadId() + " " + startDate.get());
    try {
      Thread.sleep(200);
    } catch(InterruptedException exp) {
      exp.printStackTrace();
    }
    System.out.println("finished Thread " + getThreadId() + " " + startDate.get());
  }
}

public class ThreadLocalExample {
  public static void main(String[] args) {
    Thread[] threads = new Thread[3];
    Task task = new Task();
    for(int i = 0;i < 3;i++) {
      threads[i] = new Thread(task, "thread " + i);
    }
    threads[0].start();
    threads[1].start();
    threads[2].start();
  }
}
