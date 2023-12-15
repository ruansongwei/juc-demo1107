package com.tuling.bingfa1617.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


    CompletableFuture<Integer> future1 = CompletableFuture
            .supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    int number = new Random().nextInt(10);
                    System.out.println("第一阶段：" + number);
                    return number;
                }
            });
    CompletableFuture<Integer> future2 = CompletableFuture
            .supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    int number = new Random().nextInt(10);
                    System.out.println("第二阶段：" + number);
                    return number;
                }
            });
    CompletableFuture<Integer> result = future1
            .thenCombine(future2, new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer x, Integer y) {
                    return x + y;
                }
            });
 System.out.println("最终结果：" + result.get());
    }
}
