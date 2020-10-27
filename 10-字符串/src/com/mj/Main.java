package com.mj;

import com.mj.tools.Asserts;

public class Main {

    public static void main(String[] args) {
        Asserts.test(BruteForce01.indexOf("Hello World", "or") == 7);
        Asserts.test(BruteForce01.indexOf("Hello World", "abc") == -1);
    }

}
