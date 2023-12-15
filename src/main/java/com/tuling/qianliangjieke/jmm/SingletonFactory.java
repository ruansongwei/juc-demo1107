package com.tuling.qianliangjieke.jmm;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author  Fox
 * hsdis-amd64.dll
 * 查看汇编指令
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
 *  DCL为什么要使用volatile
 */
public class SingletonFactory {

    private static  SingletonFactory myInstance;

    public static SingletonFactory getMyInstance() {
        if (myInstance == null) {
            synchronized (SingletonFactory.class) {
                if (myInstance == null) {
                    // 1. 开辟一片内存空间
// todo-rsw :   如果指令重排 3先执行 因为有了 内存地址 但是实际上还没初始化就会出现问题 对象不完整 创建对象分三步
                    // 3. myInstance指向内存空间的地址
                    // 2. 对象初始化
                    myInstance = new SingletonFactory();
                }
            }
        }
        return myInstance;
    }

//    private   static ThreadFactory threadFactory =new ThreadFactoryBuilder()
//            .setNameFormat("demo-pool-%d").build();


    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        List<CompletableFuture<Singleton>> futures = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            CompletableFuture<Singleton> future = CompletableFuture.supplyAsync(() -> Singleton.getInstance(), executorService);
//            futures.add(future);
//        }
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//        Set<Singleton> instances = new HashSet<>();
//        for (CompletableFuture<Singleton> future : futures) {
//            try {
//                instances.add(future.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Number of instances created: " + instances.size());
//        executorService.shutdown();
//    }
// SingletonFactory singletonFactory =new SingletonFactory();
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread =new Thread(r,"thread"+new Random().nextInt(10));
                return thread;
            }
        };

        ExecutorService executorService1 = Executors.newFixedThreadPool(100,threadFactory);//快

        for (int i = 0; i < 1000000; i++) {
            executorService1.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+" is running");
                      System.out.println(getMyInstance());
                }
            });
//        }


        }
    }
}



