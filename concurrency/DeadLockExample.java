package io.abhishek.concurrency;

public class DeadLockExample {
  class Resource1 {
    int i = 10;
  }
  class Resource2 {
    int i = 20;
  }

  public static void main(String[] args) {
    DeadLockExample test = new DeadLockExample();
    final Resource1 resource1 = test.new Resource1();
    final Resource2 resource2 = test.new Resource2();
    Runnable block1 = new Runnable() {
      @Override public void run() {
        synchronized (resource1) {   // can cause deadLock
          try {
            System.out.println("In block1");
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (resource2) {
            System.out.println("In block1");
          }
        }
      }
    };

    Runnable block2 = new Runnable() {
      @Override public void run() {
        synchronized (resource1) {
          synchronized (resource2) {
            System.out.println("In block2");
          }
        }
      }
    };

    new Thread(block1).start();
    new Thread(block2).start();
  }

}
