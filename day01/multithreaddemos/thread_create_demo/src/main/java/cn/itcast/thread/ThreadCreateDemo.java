package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadCreateDemo {
    public static void main(String[] args) {
        /*
        //1.继承thread类创建线程
        //1.创建自定义线程的实例
        MyThread myThread = new MyThread();
        //2.启动线程
        myThread.start();
        //3.在main主线程打印信息
        for (int i = 0; i <10 ; i++) {
            System.out.println("main主线程执行了"+new Date().getTime());
        }*/

        //2.实现runnable接口
        //2.1在main主线程打印信息
  /*      for (int i = 0; i <10 ; i++) {
            *//*Thread.currentThread()返回对当前正在执行的线程对象的引用。*//*
            System.out.println(Thread.currentThread().getName()+"执行时间是："+new Date().getTime()+";执行次数是"+i);
        }
        //2.2通过thread类执行MyRunnable类
        Thread thread = new Thread(new MyRunnable(),".myRunnable");
        thread.start();
*/
        //3.实现Callable接口
        //3.1创建FutureTask实例，常见myCallable实例
       /* FutureTask<String> task = new FutureTask<String>(new MyCallable());
        //3.2创建thread实例，执行FutureTask
        Thread thread = new Thread(task,"myCallable");
        thread.start();
        //3.3在主线程打印信息
        for (int i = 0; i < 10; i++) {
            System.out.println("MyCallable执行结果是："+i);
        }
        //3.4获取myCallable执行结果
        try {
            String result = task.get();
            System.out.println("myCallable执行结果是："+result);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //4.使用线程池创建线程
        //4.1使用Executors获取线程池对象
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //4.2通过线程池对象获取线程执行并执行MyRunnable实例
        executorService.execute(new MyRunnable());
        //4.3打印主线程的信息
        for (int i = 0; i <10 ; i++) {
           //*Thread.currentThread()返回对当前正在执行的线程对象的引用。*//*
            System.out.println(Thread.currentThread().getName()+"执行时间是："+new Date().getTime()+";执行次数是"+i);
        }
    }
}
