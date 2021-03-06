package cn.itcast.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            lock.lock();
            System.out.println("加锁几次："+(i+1));
        }


        for (int i = 0; i < 10; i++) {
        try {
            System.out.println("解锁几次："+(i+1));
        }finally {
            lock.unlock();
        }

        }
    }
}
