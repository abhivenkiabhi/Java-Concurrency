package io.abhishek.concurrency.executerservice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCallable {
  public static void main(String[] args) {
    ExecutorService exService = Executors.newFixedThreadPool(10);
    Job job = new Job(1);
    List<Job> taskList = Arrays.asList(new Job(5), new Job(6), new Job(7));
    try {
      List<Future<Integer>> futureList = exService.invokeAll(taskList);
      for(Future<Integer> result : futureList) {
        System.out.println("fut result is " + result.get());
      }
    } catch(InterruptedException | ExecutionException exp) {
      exp.printStackTrace();
    }
    Future<Integer> res = exService.submit(job);
    while(res.isDone() == false) {
      try {
        System.out.println("Result is " + res.get());
      } catch (InterruptedException | ExecutionException exp) {
        exp.printStackTrace();
      }
    }
    exService.shutdown();

  }
}
