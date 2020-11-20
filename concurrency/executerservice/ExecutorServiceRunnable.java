package io.abhishek.concurrency.executerservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceRunnable {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      try {
        System.out.println("Current Time is " + LocalDateTime.now());
        Thread.sleep(100);
      } catch(InterruptedException exp) {
        exp.printStackTrace();
      }
    };

    ExecutorService exService = Executors.newFixedThreadPool(10);
    Task task = new Task();
    //1. execute task using execute() method
    exService.execute(task);

    //2. execute task using submit() method
    Future<String> future = exService.submit(task, "DONE");
    while(future.isDone() == false) {
      try {
        System.out.println("returned value is " + future.get());
      } catch (InterruptedException | ExecutionException exp) {
        exp.printStackTrace();
      }
    }
    exService.shutdown();
  }
}
