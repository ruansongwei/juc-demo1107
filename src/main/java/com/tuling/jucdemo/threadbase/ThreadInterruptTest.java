package com.tuling.jucdemo.threadbase;

import java.util.concurrent.locks.LockSupport;

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
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                      // 清除中断标志位
//                    Thread.interrupted();
                    //不会清除中断标志位
//                    Thread.currentThread().isInterrupted();
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
