package com.mj.ks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) {
        // 价值
        run("价值主导", (Article a1, Article a2) -> {
            return a2.value - a1.value;
        });
        // 重量
        run("重量主导", (Article a1, Article a2) -> {
            return a1.weight - a2.weight;
        });

        // 价值密度
        run("价值密度主导", (Article a1, Article a2) -> {
            return Double.compare(a2.ValueDensity, a1.ValueDensity);
        });

    }

    static void run(String title, Comparator<Article> comparator) {
        Article[] articles = {
                new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)
        };
        Arrays.sort(articles, comparator);
        int capacity = 150, weight = 0, value = 0;
        List<Article> selectedArticles = new ArrayList<>();
        for (int i = 0; i < articles.length; i++) {
            int newWeight = weight + articles[i].weight;
            if (newWeight <= capacity) {
                selectedArticles.add(articles[i]);
                weight = newWeight;
                value += articles[i].value;
            }
        }
        System.out.println("--------------------------" + title + "--------------------------");
        System.out.println("总价值：" + value);
        for (Article article : selectedArticles) {
            System.out.println(article);
        }
    }
}
