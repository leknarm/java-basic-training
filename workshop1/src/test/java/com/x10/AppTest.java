package com.x10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class AppTest {

    @Test void testCase01_emptyList() {
        assertEquals(0, App.solve(Collections.emptyList()));
    }

    @Test void testCase02_noDuplicates() {
        assertEquals(0, App.solve(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test void testCase03_allSame() {
        assertEquals(5, App.solve(Arrays.asList(1, 1, 1, 1, 1)));
    }

    @Test void testCase04_twoNumbersSame() {
        assertEquals(2, App.solve(Arrays.asList(1, 2, 2)));
    }

    @Test void testCase05_threeSameAmongOthers() {
        assertEquals(3, App.solve(Arrays.asList(1, 2, 2, 2, 3, 4)));
    }

    @Test void testCase06_multipleDuplicates() {
        assertEquals(4, App.solve(Arrays.asList(1, 2, 2, 3, 3, 3, 3, 4)));
    }

    @Test void testCase07_negativeNumbers() {
        assertEquals(3, App.solve(Arrays.asList(-1, -1, -1, 2, 3)));
    }

    @Test void testCase08_mixPositiveAndNegative() {
        assertEquals(2, App.solve(Arrays.asList(-1, 1, -1, 1)));
    }

    @Test void testCase09_duplicatesAtEnd() {
        assertEquals(2, App.solve(Arrays.asList(1, 2, 3, 4, 5, 5)));
    }

    @Test void testCase10_duplicatesAtStart() {
        assertEquals(3, App.solve(Arrays.asList(3, 3, 3, 4, 5)));
    }

    @Test void testCase11_largeNumbers() {
        assertEquals(2, App.solve(Arrays.asList(1000000, 1000000, 999999)));
    }

    @Test void testCase12_zeroRepeated() {
        assertEquals(4, App.solve(Arrays.asList(0, 0, 0, 0, 1)));
    }

    @Test void testCase13_zeroAndOne() {
        assertEquals(2, App.solve(Arrays.asList(0, 1, 0, 1)));
    }

    @Test void testCase14_onlyOneElement() {
        assertEquals(0, App.solve(List.of(1)));
    }

    @Test void testCase15_allUniqueLargeList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(0, App.solve(list));
    }

    @Test void testCase16_multipleSameMax() {
        assertEquals(3, App.solve(Arrays.asList(1, 1, 1, 2, 2, 2, 3)));
    }

    @Test void testCase17_allZeros() {
        assertEquals(5, App.solve(Arrays.asList(0, 0, 0, 0, 0)));
    }

    @Test void testCase18_alternatingNumbers() {
        assertEquals(3, App.solve(Arrays.asList(1, 2, 1, 2, 1)));
    }

    @Test void testCase19_listWithNullsIgnored() {
        // Optional: Null handling – assume no nulls allowed
        assertEquals(2, App.solve(Arrays.asList(1, 2, 2, 3)));
    }

    @Test void testCase20_evenNumbers() {
        assertEquals(2, App.solve(Arrays.asList(2, 4, 6, 2, 8, 10)));
    }

    @Test void testCase21_oddNumbers() {
        assertEquals(3, App.solve(Arrays.asList(1, 3, 5, 3, 3, 7, 9)));
    }

    @Test void testCase22_primeNumbersRepeated() {
        assertEquals(2, App.solve(Arrays.asList(2, 3, 5, 7, 11, 2)));
    }

    @Test void testCase23_countOfTwoDifferentIsMax() {
        assertEquals(4, App.solve(Arrays.asList(3, 3, 3, 3, 5, 5, 5)));
    }

    @Test void testCase24_largeListSameElement() {
        List<Integer> list = Collections.nCopies(1000, 42);
        assertEquals(1000, App.solve(list));
    }

    @Test void testCase25_largeListUnique() {
        List<Integer> list = IntStream.range(0, 1000).boxed().toList();
        assertEquals(0, App.solve(list));
    }

    @Test void testCase26_twoMaxSameCount() {
        assertEquals(3, App.solve(Arrays.asList(5, 5, 5, 7, 7, 7)));
    }

    @Test void testCase27_duplicateAtMiddleOnly() {
        assertEquals(2, App.solve(Arrays.asList(1, 2, 3, 3, 4, 5)));
    }

    @Test void testCase28_duplicateNonConsecutive() {
        assertEquals(2, App.solve(Arrays.asList(9, 1, 9)));
    }

    @Test void testCase29_onlyTwoOfSame() {
        assertEquals(2, App.solve(Arrays.asList(6, 6)));
    }

    @Test void testCase30_multipleTies() {
        assertEquals(2, App.solve(Arrays.asList(1, 1, 2, 2, 3, 3)));
    }

}
