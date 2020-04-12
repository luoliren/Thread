package cn.itast.thread;

//1.创建一个银行对象：钱，存款，取款
class Bank{
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
      protected Integer initialValue(){
          return 0;
      }
    };

    public Integer get(){
        return threadLocal.get();
    }
    public void set(Integer money){
        Integer integer = threadLocal.get();
        threadLocal.set(integer+money);
    }
}

//2.创建转账对象：从银行中取钱，转账，保存到账户
class Transfer implements Runnable{
    private  Bank bank;
    public Transfer(Bank bank){
        this.bank = bank;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bank.set(10);
            System.out.println(Thread.currentThread().getName()+"账户余额："+bank.get());

        }
    }
}
public class ThreadLocalDemo {

    //3.在main方法中使用两个对象就行模拟转账
    public static void main(String[] args) {
        Bank bank = new Bank();
        Transfer transfer = new Transfer(bank);
        Thread thread1 = new Thread(transfer,"客户1");
        Thread thread2 = new Thread(transfer,"客户2");


        thread1.start();
        thread2.start();
    }
}
