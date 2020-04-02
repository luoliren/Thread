package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行时间是"+new Date().getTime()+";循环次数"+i);
        }
        return "MyCallable接口执行完成！";
    }
}
