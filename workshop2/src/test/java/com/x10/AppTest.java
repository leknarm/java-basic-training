package com.x10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.*;


public class AppTest {

    @Test void testCase01_singleList() {
        assertEquals(0, App.solve(List.of(List.of(10, 5))));
    }

    @Test void testCase02_twoLists_increase() {
        assertEquals(1, App.solve(List.of(
                List.of(10, 3),
                List.of(9, 5)
        )));
    }

    @Test void testCase03_twoLists_decrease() {
        assertEquals(0, App.solve(List.of(
                List.of(10, 9),
                List.of(9, 4)
        )));
    }

    @Test void testCase04_threeLists_oneIncrease() {
        assertEquals(2, App.solve(List.of(
                List.of(10, 3),
                List.of(9, 2),
                List.of(8, 4)
        )));
    }

    @Test void testCase05_allIncreasing() {
        List<List<Integer>> input = List.of(
                List.of(10, 1),
                List.of(9, 2),
                List.of(8, 3),
                List.of(7, 4)
        );
        assertEquals(3, App.solve(input));
    }

    @Test void testCase06_allDecreasing() {
        List<List<Integer>> input = List.of(
                List.of(10, 9),
                List.of(9, 7),
                List.of(8, 4),
                List.of(7, 1)
        );
        assertEquals(0, App.solve(input));
    }

    @Test void testCase07_alternating() {
        List<List<Integer>> input = List.of(
                List.of(10, 1),
                List.of(9, 3),
                List.of(8, 2),
                List.of(7, 4)
        );
        assertEquals(3, App.solve(input));
    }

    @Test void testCase08_duplicateNotAllowed() {
        List<List<Integer>> input = List.of(
                List.of(10, 1),
                List.of(9, 3),
                List.of(8, 2)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase09_highValues() {
        List<List<Integer>> input = List.of(
                List.of(1_000_000, 1000),
                List.of(999_999, 2000)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase10_negativeLastValues() {
        List<List<Integer>> input = List.of(
                List.of(-1, -100),
                List.of(-2, -50)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase11_largeStrictIncrease() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            input.add(List.of(1000 + i, i));
        }
        assertEquals(999, App.solve(input));
    }

    @Test void testCase12_largeStrictDecrease() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 1000; i > 0; i--) {
            input.add(List.of(2000, i));
        }
        assertEquals(0, App.solve(input));
    }

    @Test void testCase13_middleSpike() {
        List<List<Integer>> input = List.of(
                List.of(10, 1),
                List.of(9, 10),
                List.of(8, 2)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase14_lastValueIsMax() {
        List<List<Integer>> input = List.of(
                List.of(10, 2),
                List.of(9, 3),
                List.of(8, 1000)
        );
        assertEquals(2, App.solve(input));
    }

    @Test void testCase15_reverseIndexInInnerList() {
        List<List<Integer>> input = List.of(
                List.of(200, 100),
                List.of(150, 120)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase16_zeroEdge() {
        List<List<Integer>> input = List.of(
                List.of(100, 0),
                List.of(90, 1)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase17_sameFirstDifferentLast() {
        List<List<Integer>> input = List.of(
                List.of(100, 10),
                List.of(100, 20)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase18_randomOrder() {
        List<List<Integer>> input = List.of(
                List.of(100, 5),
                List.of(90, 9),
                List.of(80, 1),
                List.of(70, 12),
                List.of(60, 3)
        );
        assertEquals(3, App.solve(input));
    }

    @Test void testCase19_large_input_half_increasing() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            input.add(List.of(200_000 - i, i * 2));
        }
        assertEquals(99_999, App.solve(input));
    }

    @Test void testCase20_large_input_all_same_last_illegal() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add(List.of(100 - i, 80 - i));
        }
        assertEquals(0, App.solve(input));
    }

    @Test void testCase21_zigzag() {
        List<List<Integer>> input = List.of(
                List.of(10, 1),
                List.of(9, 3),
                List.of(8, 2),
                List.of(7, 4),
                List.of(6, 3),
                List.of(5, 5)
        );
        assertEquals(5, App.solve(input));
    }

    @Test void testCase22_oneElement() {
        List<List<Integer>> input = List.of(
                List.of(100, 50)
        );
        assertEquals(0, App.solve(input));
    }

    @Test void testCase23_max_values() {
        List<List<Integer>> input = List.of(
                List.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
                List.of(Integer.MAX_VALUE - 1, -1)
        );
        assertEquals(1, App.solve(input));
    }

    @Test void testCase24_all_even_last() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            input.add(List.of(1000 + i, i * 2));
        }
        assertEquals(19, App.solve(input));
    }

    @Test void testCase25_all_odd_last() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            input.add(List.of(1000 + i, i * 2 + 1));
        }
        assertEquals(19, App.solve(input));
    }

    @Test void testCase26_jump_in_middle() {
        List<List<Integer>> input = List.of(
                List.of(100, 1),
                List.of(99, 2),
                List.of(98, 100),
                List.of(97, 101)
        );
        assertEquals(3, App.solve(input));
    }

    @Test void testCase27_sortedInputDescendingLasts() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 1000; i > 0; i--) {
            input.add(List.of(i + 1000, i));
        }
        assertEquals(0, App.solve(input));
    }

    @Test void testCase28_sortedInputAscendingLasts() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            input.add(List.of(i + 1000, i));
        }
        assertEquals(999, App.solve(input));
    }

    @Test void testCase29_jump_in_middle() {
        List<List<Integer>> input = List.of(
                List.of(100, 1),
                List.of(99, 102),
                List.of(98, 100),
                List.of(97, 101)
        );
        assertEquals(2, App.solve(input));
    }

    @Test void testCase30_max_size_unique_last_values() {
        List<List<Integer>> input = new ArrayList<>();
        for (int i = 0; i < 200_000; i++) {
            input.add(List.of(300_000 - i, i));  // last elements are 0..199999
        }
        assertEquals(199_999, App.solve(input));
    }

}
