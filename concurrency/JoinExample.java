package io.abhishek.concurrency;
class Task implements Runnable {

  @Override
  public void run() {
    for(int i = 0;i < 5;i++) {
      System.out.println(i + " " + Thread.currentThread().getName());
     // Thread.yield();
    }
  }
}
public class JoinExample {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Task(), "T1");
    Thread t2 = new Thread(new Task(), "T2");
    Thread t3 = new Thread(new Task(), "T3");
    t1.start();
    t1.join();
    t2.start();
    t2.join();
    t3.start();
  //  t3.join();
  }
}
