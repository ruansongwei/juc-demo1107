package com.tuling.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fox
 */
public class AtomicIntegerTest {

    static AtomicInteger sum = new AtomicInteger(0);
    // todo-rsw :   无论加不加 volatile 都无法保证原子性 ++ -- 分三步
    static volatile int count=0;
    static  int count1=0;
    static AtomicInteger sum1 = new AtomicInteger(0);
    public static void main(String[] args) {
// todo-rsw :  getAndSet 返回旧值

//        int andSet = sum1.getAndSet(1);
//        System.out.println(andSet);
//        System.out.println(sum1);
        // todo-rsw :  addAndGet 返回加后的值
//        int andSet = sum1.addAndGet(6);
//        System.out.println(andSet);
//        System.out.println(sum1);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 原子自增  CAS
                    sum.incrementAndGet();


//                    count++;

                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum.get());
//        System.out.println(count);

    }

}
