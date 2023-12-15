package com.tuling.jucdemo.lock1;

import com.tuling.threadPool.array.Array;

import java.util.Arrays;
import java.util.Comparator;

public class test {

    public static Comparator<Integer> getCompare() {
//        return (o1, o2) -> o2.length() - o1.length();
//        return (String o1, String o2) -> {
//            return o2.length() - o1.length();
//        };
        return ( o1,  o2) -> o2.intValue() - o1.intValue();



//          return new Comparator<String>() {
//              @Override
//              public int compare(String o1, String o2) {
//                  return 0;
//              }
//          };

    }






    public static void main(String[] args) {
        Integer[] arr = {2, 6, 4};

        System.out.println(Arrays.toString(arr));
                Arrays.sort(arr,getCompare());
        System.out.println(Arrays.toString(arr));
    }
}
