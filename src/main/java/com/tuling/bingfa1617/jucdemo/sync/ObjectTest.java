package com.tuling.bingfa1617.jucdemo.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author  Fox
 *
 * 关闭指针压缩（-XX:-UseCompressedOops）
 */
public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {
        //jvm延迟偏向
        Thread.sleep(5000);
        Object obj = new Test();
        //Object obj = new Integer[4];
        // todo-rsw :   如果调用hashCode 无锁状态  不使用偏向锁没地方存hashcode
//        obj.hashCode();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        new Thread(()->{
            // todo-rsw :   如果是锁对象的话 默认4s 后是偏向状态 如果是锁的类 加载期间是无锁状态！！！
            synchronized (obj){
                // todo-rsw :   如果是锁里面调直接升级重量级锁
//                obj.hashCode();
                // todo-rsw :   调用wait 也会变成重量级锁  notify 轻量级锁

                System.out.println(Thread.currentThread().getName()+"\n"+ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println(Thread.currentThread().getName()+"释放锁\n"+ClassLayout.parseInstance(obj).toPrintable());

            // jvm 优化
//            try {
//                Thread.sleep(100000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        },"Thread1").start();


        Thread.sleep(20);

//
//        new Thread(()->{
//            synchronized (obj){
//                System.out.println(Thread.currentThread().getName()+"\n"+ClassLayout.parseInstance(obj).toPrintable());
//            }
//        },"Thread2").start();

    }
}

class Test{
    private boolean flag;
    private long  p;
}
