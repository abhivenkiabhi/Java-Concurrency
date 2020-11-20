package io.abhishek.concurrency.runnabl;
class Task implements Runnable {
  int cnt;
  public void run() {
    print();
  }
  public Task(int count) {
    this.cnt = count;
  }
  public void print() {
    if (cnt == 1) {
      System.out.println("Info " + Thread.currentThread().getName());
    } else {
      System.out.println("var " + Thread.currentThread().getName());
    }
  }
}
public class ThreadVmWare {
  public static void main(String[] args) {
    Task task1 = new Task(1);
    Task task2 = new Task(2);
    Thread t1 = new Thread(task1, "Thread1");
    Thread t2 = new Thread(task2, "Thread2");
    t1.start();
    t2.start();
  }

}
