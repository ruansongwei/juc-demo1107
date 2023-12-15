package com.tuling.qianliangjieke.jmm;

public class VolatileTest2 {

    private static volatile boolean flag = true;
//    private static    boolean flag = true;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (flag){
                        System.out.println("trun on");
                        flag = false;
                        System.out.println("~~~~111");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (!flag){
                        System.out.println("trun off");
                        flag = true;
                        System.out.println("~~~~222");
                    }
                }
            }
        }).start();
    }
}