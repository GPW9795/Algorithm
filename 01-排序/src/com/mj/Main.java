package com.mj;

import com.mj.sort.BubbleSort3;
import com.mj.sort.HeapSort;
import com.mj.sort.SelectionSort;
import com.mj.sort.Sort;
import com.mj.tools.Asserts;
import com.mj.tools.Integers;
import com.mj.tools.Times;

public class Main {

    public static void main(String[] args) {
        Integer[] array1 = Integers.random(10000, 1, 20000);
        testSort(array1, new HeapSort(), new SelectionSort(), new BubbleSort3());
//        Asserts.test(Integers.isAscOrder(array1));
//        Asserts.test(Integers.isAscOrder(array2));
//        Asserts.test(Integers.isAscOrder(array3));
    }

    static void testSort(Integer[] array, Sort... sorts) {
        for (Sort sort:sorts){
            sort.sort(Integers.copy(array));
            System.out.println(sort);
        }
    }
}