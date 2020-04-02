package cn.itcast.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    private int ticketNum = 100;//电影票的数量，默认100张
    private Object obj = new Object();
    private Lock lock = new ReentrantLock(true);//参数是否公平锁：true公平锁，多个线程都拥有执行权
                                                    // false:非公平独占锁，默认值
    @Override
    public void run() {
        while(true){
            lock.lock();//加锁
            try {
                if (ticketNum > 0) {//判断是否有票，ticketNum > 0
                    //有票，让线程睡眠100毫秒
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //打印当前出的票的数字和线程名
                    String name = Thread.currentThread().getName();
                    System.out.println("线程"+name+"销售电影票："+ticketNum);

                    //票数减一
                    ticketNum--;
                }
            }finally {
                lock.unlock();
            }

        }
    }



/*    public void run() {
        while(true){
            //如果想要执行下面的代码，就必须要拥有Object的对象 synchronized的obj对象就像开启下面的代码的钥匙
            //同步代码块
            synchronized (obj){
                if (ticketNum > 0) {//判断是否有票，ticketNum > 0
                //有票，让线程睡眠100毫秒
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //打印当前出的票的数字和线程名
                String name = Thread.currentThread().getName();
                System.out.println("线程"+name+"销售电影票："+ticketNum);

                //票数减一
                ticketNum--;
            }}

        }
    }*/


/*
    public void run() {
        while (true) {
            saleTicket();
        }
    }
    //synchronized加到方法上，如果方法不是静态方法，就调用当前方法的实例，
    // 里面的方法就是创建当前类的实例就是synchronized的开启的钥匙
    //private synchronized(new Ticket()) void saleTicket()
    //如果是静态方法，那么synchronized的那把钥匙就是当前所在这个类的类对象，目前所在的类是Ticket
    //  private static synchronized(Ticket.class) void saleTicket()


    private synchronized void saleTicket() {
        if (ticketNum > 0) {//判断是否有票，ticketNum > 0
            //有票，让线程睡眠100毫秒
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //打印当前出的票的数字和线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程" + name + "销售电影票：" + ticketNum);

            //票数减一
            ticketNum--;
        }
    }*/
}
