package com.tuling.qianliangjieke.jmm;

import com.tuling.jucdemo.factory.UnsafeFactory;

/**
 * @author  Fox
 *
 */
public class ReOrderTest {

    private static  int x = 0, y = 0;
//    private volatile static  int a = 0, b = 0;
// todo-rsw :   不加volatile 会出现重排序
    private  static  int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            /**
             *  x,y:   00, 10, 01, 11
             */
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    shortWait(20000);
                    a = 1; // volatile写
                    //  StoreLoad
                    UnsafeFactory.getUnsafe().storeFence();
                    x = b; // volatile读
                    // todo-rsw :   重排序结果是 先  x = b;    后  a = 1;
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    UnsafeFactory.getUnsafe().storeFence();
                    y = a;
                }
            });
// todo-rsw :   1线程先执行 01 二线程先执行 10   线程1 a=1 线程2 b=1 会出现1 1
             thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("第" + i + "次（" + x + "," + y + ")");
            if (x==0&&y==0){
                break;
            }
        }
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }

}
