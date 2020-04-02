package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThressThreadStartDemo  {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);//参数是参与CyclicBarrier的线程数
    public void startThreeThread() throws BrokenBarrierException, InterruptedException {
        //1.打印线程准备启动
        String name = Thread.currentThread().getName();
        System.out.println(name+"正在准备。。。");
        //2.调用CyclicBarriar的await方法等待线程全部只能被完成
        cyclicBarrier.await();
        //3.打印线程启动完毕的信息
        System.out.println(name+"已经启动完毕："+new Date().getTime());
    }

    public static void main(String[] args) {
        ThressThreadStartDemo thressThreadStartDemo = new ThressThreadStartDemo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thressThreadStartDemo.startThreeThread();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thressThreadStartDemo.startThreeThread();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thressThreadStartDemo.startThreeThread();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
