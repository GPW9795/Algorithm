package com.mj;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int[] faces = {25, 10, 5, 1};
        int money = 41;
        int coins = 0;
        Arrays.sort(faces);
        int index = faces.length - 1;
        while (index >= 0 && money > 0) {
            while (money >= faces[index]) {
                money -= faces[index];
                coins++;
            }
            index--;
        }
        System.out.println(coins);
    }

}
