package com.tuling.threadPool.threadpool;



import com.tuling.threadPool.MonkeyRejectedExecutionHandler;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService1 = Executors.newCachedThreadPool();//快
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);//慢
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();//最慢
        long start = System.currentTimeMillis();

        RejectedExecutionHandler rejectedExecutionHandler=  new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 写数据库的代码
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1),new MonkeyRejectedExecutionHandler());//自定义线程

        for (int i = 1; i <= 100; i++) {
            threadPoolExecutor.execute(new MyTask(i));


        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("时间："+(System.currentTimeMillis() - start));
    }
}

/***
 * 项目
 */
class MyTask implements Runnable {
    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "程序员做第" + i + "个项目");
        try {

            Thread.sleep(3000L);//业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
