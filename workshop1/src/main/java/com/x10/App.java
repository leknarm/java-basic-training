package com.x10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println(solve(List.of(1, 5, 2, 7, 3, 2, 7, 3, 8, 4)));
    }

    public static Integer solve(List<Integer> sizes) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (Integer size : sizes) {
            countMap.put(size, countMap.getOrDefault(size, 0) + 1);
        }

        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1 && entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }

        return maxCount;
    }
}
