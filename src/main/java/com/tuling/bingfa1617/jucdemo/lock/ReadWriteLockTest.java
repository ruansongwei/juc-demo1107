package com.tuling.bingfa1617.jucdemo.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Fox
 * ReadWriteLock测试
 */

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                queue.put(new Random().nextInt(10000));
            }).start();
        }

        Thread.sleep(10);

        for (int i = 0;i < 3; i++) {
            new Thread(() -> {
                    queue.get();
                }).start();
            }
        }
    }

    class Queue {
        //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
        private Object data = null;
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = readWriteLock.readLock();
        private Lock writeLock = readWriteLock.writeLock();

        public void get() {
            System.out.println(Thread.currentThread().getName()
                    + " be ready to read data!");
            //上读锁，其他线程只能读不能写
            readLock.lock();
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()
                        + " have read data :" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放读锁
                readLock.unlock();
            }
        }

        public void put(Object data) {
            System.out.println(Thread.currentThread().getName()
                    + " be ready to write data!");
            //上写锁，不允许其他线程读也不允许写
            writeLock.lock();
            try {
                Thread.sleep(5000);
                this.data = data;
                System.out.println(Thread.currentThread().getName()
                        + " have write data: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放写锁
                writeLock.unlock();
            }
        }
    }