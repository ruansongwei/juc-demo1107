package com.tuling.bingfa1617.jucdemo.threadbase;

/**
 * @author  Fox
 * 中断机制
 */
public class ThreadInterruptTest {

    static int i = 0;

    public static void main(String[] args)  {
        System.out.println("begin");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public  void run() {
                while (true) {
                    i++;
                    System.out.println(i);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();

                    }
                    // todo-rsw :    Thread.currentThread().isInterrupted() 每一个都有
//                    boolean interrupted = Thread.interrupted();//清除中断标志位
//                    Thread.interrupted();//清除中断标志位
//                    System.out.println(interrupted);
                    // todo-rsw :   Thread.interrupted()  只会出现一次 只有第一次不清除
//                    Thread.currentThread().isInterrupted(); //不会清除中断标志位
//                    boolean interrupted = Thread.currentThread().isInterrupted();//不会清除中断标志位
//                    System.out.println(interrupted);
                    if (Thread.currentThread().isInterrupted() ) {
                        System.out.println("=========");
                    }
                    if(i==10){
                        break;
                    }

                }
            }
        });

        t1.start();
        //不会停止线程t1,只会设置一个中断标志位 flag=true
        t1.interrupt();

    }
}
