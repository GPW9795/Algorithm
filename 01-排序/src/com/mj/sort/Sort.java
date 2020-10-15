package com.mj.sort;

import com.mj.tools.Integers;

public abstract class Sort {
    protected Integer[] array;
    public void sort(Integer[] array) {
        if (array == null || array.length < 2) return;

        this.array = array;
        sort();
    }

    protected abstract void sort();
}
