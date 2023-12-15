package com.tuling.bingfa1617.jucdemo.sync;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

/**
 * @author Fox
 * <p>
 * 根据QMode的不同，会执行不同的唤醒策略
 */
@Slf4j
public class SyncQModeDemo {

    public static void main(String[] args) throws InterruptedException {

        // todo-rsw :    一共有三个 结构cxq 竞争的话都会进入cxq 后进先出  entrylist waitset
        //  调用wait  会进入waitset   notify 进入 entrylist  优先唤醒entrylist
        SyncQModeDemo demo = new SyncQModeDemo();

        demo.startThreadA();
        //控制线程执行时间
        Thread.sleep(100);
        demo.startThreadB();
        Thread.sleep(100);
        demo.startThreadC();

    }

    final Object lock = new Object();

    public void startThreadA() {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("A get lock");
                try {
//                    Thread.sleep(500);
//                    lock.wait(300);
                    lock.wait();
                    // todo-rsw :    wait 释放锁 park sleep 不释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("A release lock");
            }
        }, "thread-A").start();
    }

    public void startThreadB() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    log.debug("B get lock");
                    Thread.sleep(300);
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("B release lock");
            }
        }, "thread-B").start();
    }

    public void startThreadC() {
        new Thread(() -> {
            synchronized (lock) {

                log.debug("C get lock");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.debug("C 休息完准备释放lock");
            }
        }, "thread-C").start();
    }

      public void startThreadD() {
        new Thread(() -> {
            synchronized (lock) {

                log.debug("D get lock");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.debug("D 休息完准备释放lock");
            }
        }, "thread-D").start();
    }


    public void startThreadE() {
        new Thread(() -> {
            synchronized (lock) {

                log.debug("E get lock");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.debug("E 休息完准备释放lock");
            }
        }, "thread-E").start();
    }


}
