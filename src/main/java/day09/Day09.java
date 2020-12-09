package day09;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day09 {
    private static final int PREAMBLE = 25;

    public static void main(String[] args) throws FileNotFoundException {
        List<Long> input = FileUtils.getLongsFromInput("input09.txt");
        long solutionA = solveDay09a(input, PREAMBLE);
        System.out.println(solutionA);
        System.out.println(solveDay09b(input, solutionA));


    }

    private static long solveDay09a(List<Long> input, int preamble) {
        for (int i = PREAMBLE; i < input.size(); i++) {
            if (!isValidA(input.get(i), input.subList(i - preamble, i))) {
                return input.get(i);
            }
        }
        return - 1;
    }

    private static long solveDay09b(List<Long> input, long target) {
        for (int i = 0; i < input.size(); i ++) {
            long sum = 0L;
            int counter = 0;
            while (sum < target) {
                sum += input.get(i + counter);
                if (sum == target && counter > 2) {
                    List<Long> range = input.subList(Math.min(i, i + counter), Math.max(i, i + counter));
                    return range.stream().max(Long::compareTo).orElse(-1L) + range.stream().min(Long::compareTo).orElse(-1L);
                }
                counter++;
            }
        }
        return -1;
    }

    private static boolean isValidA(long number, List<Long> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                if (!input.get(i).equals(input.get(j)) && input.get(i) + input.get(j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

}
