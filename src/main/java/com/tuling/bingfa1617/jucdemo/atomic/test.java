package com.tuling.bingfa1617.jucdemo.atomic;



import java.util.ArrayList;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        ArrayList arrayList =new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(8);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);

        }

    }
}
