package io.abhishek.concurrency.factorythread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
class Task implements Runnable {
  public void run() {
    try {
      System.out.println("Thread created " + Thread.currentThread().getName());
      Thread.sleep(100);
    } catch (InterruptedException exp) {
      exp.printStackTrace();
    }
  }
}
class CustomThreadFactory implements ThreadFactory {
  private int counter;
  private String name;
  List<String> stats;

  public CustomThreadFactory(String name) {
    this.counter = 0;
    this.name = name;
    stats = new ArrayList<>();
  }
  public Thread newThread(Runnable runnable) {
    Thread thread = new Thread(runnable, "Thread " + counter++);
    return thread;
  }

}
public class ThreadFactoryExample {
  public static void main(String[] args) {
    CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
    Task task = new Task();
    Thread[] thread = new Thread[5];
    for(int i = 0;i < 5;i++) {
      thread[i] = factory.newThread(task);
      thread[i].start();
    }
    System.out.println("All threads created");
  }

}
