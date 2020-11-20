package io.abhishek.concurrency.threadpoolexec;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecExample {
  public static void main(String[] args) {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    for(int i = 0;i < 5;i++) {
      Task task =  new Task("task " + i);
      System.out.println("Created Task with name " + task.getName());
      executor.execute(task);
    }
    executor.shutdown();

  }
}
