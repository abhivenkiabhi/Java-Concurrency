package io.abhishek.concurrency.executerservice;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class Job implements Callable<Integer> {
  Integer val;
  public Job(int val) {
    this.val = val;
  }
  public Integer call() {
    System.out.println("Callable method called at currentTime " + LocalDateTime.now().toString());
    try {
      Thread.sleep(200);
    } catch(InterruptedException exp) {
      exp.printStackTrace();
    }
    return this.val;
  }
}
