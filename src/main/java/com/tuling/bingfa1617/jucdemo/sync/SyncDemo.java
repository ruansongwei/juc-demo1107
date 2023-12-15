package com.tuling.bingfa1617.jucdemo.sync;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 */
@Slf4j
public class SyncDemo {
    // todo-rsw :   counter++ -- 分三步 不保证整体的一致性
 /**
  * 1. getstatic i // 获取静态变量i的值
2 iconst_1 // 将int常量1压入操作数栈
3 iadd // 自增
4 putstatic i // 将修改后的值存入静态变量i

  getstatic i // 获取静态变量i的值
  2 iconst_1 // 将int常量1压入操作数栈
  3 isub // 自减
  4 putstatic i // 将修改后的值存入静态变量i

  */

    private static volatile int counter = 0;

    public static void increment() {
        counter++;
    }

    public static void decrement() {
        counter--;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                decrement();
            }
        }, "t2");
//如果交替就不行
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // todo-rsw :    // t1执行完 t2执行没问题 如果交替就不行
//        t1.start();
//        t1.join();
//        t2.start();
//        t2.join();


        //思考： counter=？
        log.info("counter={}", counter);


    }
}
