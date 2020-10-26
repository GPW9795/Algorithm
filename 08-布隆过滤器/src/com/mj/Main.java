package com.mj;

public class Main {

    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(10000000, 0.01);
    }

}
