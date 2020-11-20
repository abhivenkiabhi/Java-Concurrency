package io.abhishek.concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {
  public static void main(String[] args) {
    Integer threadCounter = 0;
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(20);
    CustomThreadPoolExecutor executor =
        new CustomThreadPoolExecutor(10,15, 5000, TimeUnit.MILLISECONDS, blockingQueue);
    executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
      @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected :" + ((Task)r).getName());
        System.out.println("wait for sometime");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }
        System.out.println("add task another time : " + ((Task)r).getName());
        executor.execute(r);
      }
    });
    executor.prestartAllCoreThreads();
    while(true) {
      threadCounter++;
      System.out.println("Adding demo Task " + threadCounter);
      executor.execute(new Task(threadCounter.toString()));
      if (threadCounter == 100)
        break;
    }
  }
}
