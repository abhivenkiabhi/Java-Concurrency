package io.abhishek.concurrency.exceptionhandling;

import java.lang.Thread.UncaughtExceptionHandler;

class Task implements Runnable {
  public void run() {
    Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
    System.out.println(Integer.parseInt("123"));
    System.out.println(Integer.parseInt("xyz"));
    System.out.println(Integer.parseInt("456"));
  }
}
class ExceptionHandler implements UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("Exception has been captured");
    e.printStackTrace(System.out);
    System.out.println("Thread Status : " + t.getState());
    new Thread(new Task()).start();
  }
}
public class UncaughtExceptionHandlerExample {
  public static void main(String[] args) {
    Task task = new Task();
    Thread thread = new Thread(task);
    thread.start();
  }

}
