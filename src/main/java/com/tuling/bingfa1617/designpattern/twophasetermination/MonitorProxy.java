package com.tuling.bingfa1617.designpattern.twophasetermination;

/**
 * @author Fox
 */
public class MonitorProxy {
    boolean started = false;
    //采集线程
    Thread rptThread;

    //启动采集功能
    synchronized void start() {
        //不允许同时启动多个采集线程
        if (started) {
            return;
        }
        started = true;
        rptThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                //省略采集、回传实现
                report();
                //每隔两秒钟采集、回传一次数据


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    //重新设置线程中断状态

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
        rptThread.interrupt();

    }


    public static void main(String[] args) {
        MonitorProxy monitor = new MonitorProxy();
        monitor.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.stop();
    }

}
