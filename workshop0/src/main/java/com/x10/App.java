package com.x10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        long start = System.nanoTime();
        List<Integer> beam1 = List.of(2, 7, 8, 15, 20);
        List<Integer> beam2 = List.of(3, 4, 5, 7, 10, 16, 21);
        Integer result1 = solve(beam1, beam2);
        System.out.println("Result1: " + result1);

        List<Integer> beam3 = List.of(10, 20, 30);
        List<Integer> beam4 = List.of(1, 10, 20);
        Integer result2 = solve(beam3, beam4);
        System.out.println("Result2: " + result2);

        List<Integer> beam5 = List.of(10, 20);
        List<Integer> beam6 = List.of(5, 10, 15, 20);
        Integer result3 = solve(beam5, beam6);
        System.out.println("Result3: " + result3);
        long end = System.nanoTime();
        System.out.println("Processing time: " + (end - start) / 1_000_000.0 + " ms");
    }

    public static Integer solve(List<Integer> beam1, List<Integer> beam2) {
        List<Integer> oddBeam1 = new ArrayList<>();
        List<Integer> evenBeam1 = new ArrayList<>();
        for (int i = 0; i < beam1.size(); i++) {
            (i % 2 != 0 ? oddBeam1 : evenBeam1).add(beam1.get(i));
        }

        List<Integer> oddBeam2 = new ArrayList<>();
        List<Integer> evenBeam2 = new ArrayList<>();
        for (int i = 0; i < beam2.size(); i++) {
            (i % 2 != 0 ? oddBeam2 : evenBeam2).add(beam2.get(i));
        }
        
        // Find common elements except last element
        Set<Integer> oddCommon = new HashSet<>(oddBeam1.subList(0, Math.max(oddBeam1.size() - 1, 0)));
        oddCommon.retainAll(oddBeam2.subList(0, Math.max(oddBeam2.size() - 1, 0)));

        Set<Integer> evenCommon = new HashSet<>(evenBeam1.subList(0, Math.max(evenBeam1.size() - 1, 0)));
        evenCommon.retainAll(evenBeam2.subList(0, Math.max(evenBeam2.size() - 1, 0)));
        
        // Find min and max of shortest beam
        int minPosOfShortestBeam = (beam1.size() < beam2.size() ? Collections.min(beam1) : Collections.min(beam2));
        int maxPosOfShortestBeam = (beam1.size() < beam2.size() ? Collections.max(beam1) : Collections.max(beam2));
        
        // Find number of elements between min and max (inclusive) in the longer list
        int crossElementsWithStart = 1; // Count the starting element
        if (beam1.size() < beam2.size()) {
            crossElementsWithStart += beam2.stream().filter(x -> x >= minPosOfShortestBeam && x <= maxPosOfShortestBeam).count();
        } else {
            crossElementsWithStart += beam1.stream().filter(x -> x >= minPosOfShortestBeam && x <= maxPosOfShortestBeam).count();
        }
        System.out.println("crossElementsWithStart: " + crossElementsWithStart);
        
        int skip = 0;
        skip += findSkip(beam1, beam2, oddCommon);
        skip += findSkip(beam1, beam2, evenCommon);

        return crossElementsWithStart - skip;
    }

    public static int findSkip(List<Integer> beam1, List<Integer> beam2, Set<Integer> commonElements) {
        int crossOvers = 0;
        for (Integer commonElement : commonElements) {
            // find index of common elements
            int beam1Idx = beam1.indexOf(commonElement);
            int nextBeam1Element = beam1.get(beam1Idx + 1);
            int beam2Idx = beam2.indexOf(commonElement);
            int nextBeam2Element = beam2.get(beam2Idx + 1);

            if (beam1.size() < beam2.size() && nextBeam1Element > nextBeam2Element) {
                crossOvers++;
            } else if (beam2.size() <= beam1.size() && nextBeam2Element > nextBeam1Element) {
                crossOvers++;
            }
        }
        return crossOvers;
    }
}
