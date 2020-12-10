package day10;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> input = FileUtils.getIntegersFromInput("input10.txt");
        System.out.println(solveDay10a(input));
        System.out.println(solveDay10b(input));
    }

    private static int solveDay10a(List<Integer> input) {
        int jolt = 0;
        int ones = 0;
        int threes = 0;
        while (true) {
            int one = findJolt(input, jolt, 1);
            if (one != -1) {
                jolt = one;
                ones++;
                continue;
            }
            int two = findJolt(input, jolt, 2);
            if (two != -1) {
                jolt = two;
                continue;
            }
            int three = findJolt(input, jolt, 3);
            if (three != -1) {
                jolt = three;
                threes++;
                continue;
            }
            break;
        }
        return ones * (threes + 1);
    }

    private static long solveDay10b(List<Integer> input) {
        int highest = input.stream()
                .max(Integer::compareTo)
                .orElse(-1);
        if (highest < 0) {
            return -1;
        }
        long count = 0;
        List<Integer> fringe = new ArrayList<>();
        Map<Integer, Long> visited = new HashMap<>();
        fringe.add(0);
        visited.put(0, 1L);
        while (fringe.size() != 0) {
            fringe.sort(Integer::compareTo);
            int jolt = fringe.remove(0);
            if (jolt == highest) {
                count += visited.get(jolt);
            } else {
                for (int i = 1; i <= 3; i++) {
                    int child = findJolt(input, jolt, i);
                    if (child != -1 && child <= highest) {
                        if (!fringe.contains(child)) {
                            fringe.add(child);
                        }
                        visited.put(child, visited.containsKey(child)
                                ? visited.get(child) + visited.get(jolt)
                                : visited.get(jolt));
                    }
                }
            }
        }
        return count;
    }

    private static int findJolt(List<Integer> input, int start, int delta) {
        int result = 0;
        try {
            return input.stream().filter(i -> i == start + delta).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            return -1;
        }
    }
}
