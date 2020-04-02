package cn.itcast.thread;

public class OddEvenDemo {
    private int i = 0 ;//要打印的数
    private Object obj = new Object();
    /**
     * 奇数打印方法，由奇数线程调用
     */

    /*obj.notify();obj.wait();的使用必须要依赖synchronized同步代码块  否则将不能使用*/
    public void odd() throws InterruptedException {
        //1.判断i是否是小于10，<10打印奇数
        while (i < 10) {
            synchronized (obj){
                //2.<10打印奇数
                if (i%2 == 1){
                    System.out.println("奇数:"+i);
                    i++;
                    obj.notify();//唤醒偶数线程打印
                } else {
                    obj.wait();//等待偶数线程打印
                }
            }
            }

    }

    /**
     * 偶数打印方法，由偶数线程调用
     */
    public  void  even() throws InterruptedException {
        //1.判断i是否是小于10，<10打印偶数
        while (i < 10) {
            synchronized (obj){
                //2.<10打印奇数
                if (i%2 == 0){
                    System.out.println("偶数数:"+i);
                    i++;
                    obj.notify();//唤醒奇数线程打印
                } else {
                    obj.wait();//等待奇数线程打印
                }
            }
            }

    }

    public static void main(String[] args) {
        final OddEvenDemo oddEvenDemo = new OddEvenDemo();
        //1.开启奇数线程打印
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oddEvenDemo.odd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //2.开启偶数线程打印
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oddEvenDemo.even();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
