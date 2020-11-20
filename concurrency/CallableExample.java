package io.abhishek.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Factorial implements Callable<Integer> {
  private Integer number;
  public Factorial(Integer num) {
    this.number = num;
  }
  public Integer call() throws InterruptedException{
    Integer res = 1;
     if (number == 0 || number == 1)
       return 1;
     else {
       for (int i = 2; i <= number; i++) {
         res = res * i;
         TimeUnit.MILLISECONDS.sleep(500);
       }
     }
     return res;
  }
}
public class CallableExample {
  public static void main(String[] args) {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    List<Future<Integer>> resultList = new ArrayList<>();
    Random random = new Random();
    for(int i = 0;i < 4;i++) {
      Integer num = random.nextInt(10);
      Factorial cal = new Factorial(num);
      Future<Integer> res = executor.submit(cal);
      resultList.add(res);
    }
    for(Future<Integer> future : resultList) {
      try {
        System.out.println("Future result is " + future.get() + " task done is " + future.isDone());
      } catch (InterruptedException | ExecutionException exception) {
        exception.printStackTrace();
      }
    }
  }
}
