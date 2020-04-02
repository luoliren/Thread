package cn.itcast.thread;

import java.util.concurrent.CountDownLatch;

public class CoachRacerDemo {

    private CountDownLatch countDownLatch = new CountDownLatch(3);//设置要等待的运动员是3个
    /**
     * 运动员方法，由运动员线程调用
     */
    private void racer() throws InterruptedException {
        //1.获取运动员线程的名称
        String name = Thread.currentThread().getName();
        //2.获取运动员开始准备，打印准备信息
        System.out.println(name+"正在准备。。。");
        //3.线程睡眠1000ms，表示运动员在准备
        Thread.sleep(1000);
        //4.运动员准备完毕,打印准备完毕的信息，同时进行减一
        System.out.println(name+"准备完毕！");
        countDownLatch.countDown();//一个运动准备完成进行减一
    }

    /**
     * 教练方法，由教练线程调用
     */
    private void coach() throws InterruptedException {
        //1.获取教练线程的名称
        String name = Thread.currentThread().getName();
        //2.教练等待所有的运动员准备完毕，打印等待信息
        System.out.println(name+"等待运动员准备。。。");
        //3.调用countDownLatch的await方法等待其他线程执行完毕
        countDownLatch.await();
        //4.所有的运动员已就绪，教练开始训练；打印训练信息
        System.out.println("所有的运动员已就绪!"+name+"开始训练");
    }

    public static void main(String[] args) {
        //1.创建CoachRacerDemo实例，一边调用方法
        CoachRacerDemo racerDemo = new CoachRacerDemo();
        //2.创建3个线程对象，调用CoachRacerDemo的racer方法
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    racerDemo.racer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"运动员1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    racerDemo.racer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"运动员2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    racerDemo.racer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"运动员3");
        //3.创建1个线程对象，调用CoachRacerDemo的coach方法
      Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  racerDemo.coach();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      },"教练");
      thread.start();
      thread1.start();
      thread2.start();
      thread3.start();

    }
}
