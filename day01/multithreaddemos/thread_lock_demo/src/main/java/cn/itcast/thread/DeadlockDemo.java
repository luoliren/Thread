package cn.itcast.thread;

import java.util.concurrent.ThreadLocalRandom;

public class DeadlockDemo  {
    public static void main(String[] args) {
        //1.创建两个线程DeadlockRunnable实例；flag=1;flag=2;
        DeadLockRunable runable1 = new DeadLockRunable(1);
        DeadLockRunable runable2 = new DeadLockRunable(2);

        //2.创建两个线程执行两个DeadLockRunnable实例
        Thread thread1 = new Thread(runable1,"runable1");
        Thread thread2 = new Thread(runable2,"runable2");

        thread1.start();
        thread2.start();

    }
}
