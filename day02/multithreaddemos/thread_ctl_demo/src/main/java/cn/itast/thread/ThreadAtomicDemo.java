package cn.itast.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAtomicDemo {
    //private static   int n = 0 ;//执行n++操作的变量
    static AtomicInteger atomicInteger;
    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        while(j<100) {
            atomicInteger=new AtomicInteger(0);//指定创建原子整数初始值是0
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                //        n++;
                        atomicInteger.getAndIncrement();//对应n++操作
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                      //  n++;
                        atomicInteger.getAndIncrement();//对应n++操作
                    }
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("n的最终值是：" + atomicInteger.get());
            j++;
        }
    }
}
