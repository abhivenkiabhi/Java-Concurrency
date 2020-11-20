package io.abhishek.concurrency.threadpoolexec;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecExample {
  public static void main(String[] args) {
    ScheduledThreadPoolExecutor executor =
        (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
    Task task = new Task("Scheduled Task");
    executor.scheduleWithFixedDelay(task, 100, 2000, TimeUnit.MILLISECONDS);
  }
}
