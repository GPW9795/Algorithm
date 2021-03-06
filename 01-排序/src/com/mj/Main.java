package com.mj;

import com.mj.sort.*;
import com.mj.sort.cmp.*;
import com.mj.tools.Asserts;
import com.mj.tools.Integers;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] array1 = Integers.random(10000, 1, 20000);
        testSort(array1,
                new SelectionSort(),
                new HeapSort(),
                new InsertionSort3(),
                new MergeSort(),
                new BubbleSort3(),
                new QuickSort(),
                new ShellSort(),
                new CountingSort(),
                new RadixSort()
        );
//        int[] array = {2, 4, 6, 8, 10};
//        Asserts.test(BinarySearch.search(array, 5) == 2);
//        Asserts.test(BinarySearch.search(array, 1) == 0);
//        Asserts.test(BinarySearch.search(array, 15) == 5);
    }

    static void testSort(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}