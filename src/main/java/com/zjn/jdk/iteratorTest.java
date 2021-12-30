package com.zjn.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class iteratorTest {



    public static void main(String[] args) {

        // 1.删除1没问题
        // 2.删除2有问题
        // 3.111打印几次
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String s : list) {
            System.out.println("111");
            if ("2".equals(s)) {
                list.remove(s);
            }
        }

        // 1.删除1没问题
        // 2.删除2有问题
        // 3.111打印几次  和 上面代码的差异在于 上面代码编译后 print 在 next 方法后面，所以上面打印2次，这里可以打印3次

//        Iterator<String> iterator = list.iterator();
//        while(iterator.hasNext()) {
//            System.out.println("333");
//            String next = iterator.next();
//            System.out.println(next);
//
//            if ("2".equals(next)) {
//                list.remove(next);
//            }
//        }


    }


}
