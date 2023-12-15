package com.tuling.bingfa1617.designpattern.twophasetermination;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * @author Fox
 */
public class MonitorProxy2 {
    boolean started = false;
    //采集线程
    Thread rptThread;

    //线程终止标志位
    volatile boolean terminated = false;

    //启动采集功能
    synchronized void start() {
        //不允许同时启动多个采集线程
        if (started) {
            return;
        }
        started = true;
        rptThread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()&&!terminated) {

                //省略采集、回传实现
                report();
                //每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    //重新设置线程中断状态
//                    Thread.currentThread().interrupt();
                }
            }
            //执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }

    private void report() {
        System.out.println("采集数据");
    }

    //终止采集功能
    synchronized void stop() {
        //设置中断标志位
        terminated = true;
        rptThread.interrupt();
    }


    public static void main(String[] args) {
        MonitorProxy2 monitor = new MonitorProxy2();
        monitor.start();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程名称-%s").build();
        try {
            Thread.sleep(8000);

        } catch (Exception e) {
            e.printStackTrace();
//            Thread.currentThread().interrupt();
        }
        monitor.stop();
    }

}
