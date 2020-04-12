package cn.itcast.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private Map<String,String> map = new HashMap<String, String>();//操作map对象
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();//读操作
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();//写操作

    public String  get(String key){
        readLock.lock();//读操作加锁
        try {
            System.out.println(Thread.currentThread().getName()+"读操作已加锁，开始读操作。。。");
            Thread.sleep(3000);
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println(Thread.currentThread().getName()+"读操作已解锁，读操作结束。。。");
            readLock.unlock();
        }
    }

    public void put(String key,String value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写操作已加锁，正在执行写操作");
            Thread.sleep(3000);
            map.put(key,value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"写操作已解锁，正在执行写操作");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo =new ReadWriteLockDemo();
        readWriteLockDemo.put("key1","value1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(readWriteLockDemo.get("key1"));
            }
        },"读线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(readWriteLockDemo.get("key1"));
            }
        },"读线程2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(readWriteLockDemo.get("key1"));
            }
        },"读线程3").start();
    }
}
