package cn.itcast.thread;

import java.util.concurrent.Semaphore;

class Work implements Runnable{
    private int workerNum;//工人的工号
    private Semaphore semaphore;//机器数

    public Work(int workerNum, Semaphore semaphore) {
        this.workerNum = workerNum;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        //1。工人要获取机器
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2。打印工人获取机器，开始工作
        String name = Thread.currentThread().getName();
        System.out.println(name+"获取到机器，开始工作。。。。");
        //3.线程睡眠1000毫秒，模拟工人使用机器的过程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4.使用完毕，释放机器，打印工人使用完毕，释放机器
        semaphore.release();
        System.out.println(name+"使用完毕，释放机器！");
    }
}
public class WorkerMachineDemo {
    public static void main(String[] args) {
        int workers = 8 ;//代表工人数8个
        Semaphore semaphore = new Semaphore(3);//代表机器数3个
        for (int i = 0; i < workers; i++) {
            new Thread(new Work(i,semaphore)).start();
        }
    }

}
