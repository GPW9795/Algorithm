package com.mj;

public class Main {

    public static void main(String[] args) {
//        BloomFilter<Integer> bf = new BloomFilter<>(1000000, 0.01);
//        for (int i = 0; i < 1000000; i++) {
//            bf.put(i);
//        }
//        // 误判个数
//        int index = 0;
//        for (int i = 1000001; i < 2000000; i++) {
//            if (bf.contains(i)) {
//                index++;
//            }
//        }
//        System.out.println(index);

        BloomFilter<String> bf = new BloomFilter<>(10_0000_0000, 0.01);
        String[] urls = {};

        for (String url : urls) {
            if (!bf.put(url)) continue;
            // 爬这个URL
            // ......
        }

        for (String url : urls) {
            if (bf.contains(url)) continue;
            // 爬这个URL
            // ......

            // 放进Bloom Filter
            bf.put(url);
        }
    }

}
