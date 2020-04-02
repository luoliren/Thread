package cn.itcast.thread;

import java.util.Date;

/**
 * 自定义实现Runnable接口
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            /*Thread.currentThread()返回对当前正在执行的线程对象的引用。*/
            System.out.println(Thread.currentThread().getName()+"执行时间是："+new Date().getTime()+";执行次数是"+i);
        }
    }
}
