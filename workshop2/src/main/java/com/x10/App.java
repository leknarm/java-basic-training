package com.x10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println(solve(List.of(List.of(100, 30), List.of(50, 40))));
        System.out.println(solve(List.of(List.of(100, 45), List.of(50, 40))));
        System.out.println(solve(List.of(List.of(100, 200), List.of(90, 40), List.of(50, 41), List.of(10, 30))));
    }

    public static Integer solve(List<List<Integer>> cars) {
        int noOfUnsold = 0;

        List<List<Integer>> reverseCars = new ArrayList<>(cars);
        Collections.reverse(reverseCars);
        int maxPerformance = reverseCars.get(0).get(1);

        for (int i = 1; i < reverseCars.size(); i++) {
            Integer next = reverseCars.get(i).get(1);
            if (maxPerformance > next) {
                noOfUnsold++;
            } else {
                maxPerformance = next;
            }
        }

        return noOfUnsold;
    }
}
