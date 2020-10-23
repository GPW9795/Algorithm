package com.mj.ks;

/**
 * 背包
 */
public class Article {
    public int weight;
    public int value;
    public double ValueDensity;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        ValueDensity = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "Article{" +
                "weight=" + weight +
                ", value=" + value +
                ", ValueDensity=" + ValueDensity +
                '}';
    }
}
