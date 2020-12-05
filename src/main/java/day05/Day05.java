package day05;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input05.txt");
        System.out.println(solveDay05a(input));
        System.out.println(solveDay05b(input));
    }

    private static int solveDay05a(List<String> input) {
        return input.stream()
                .mapToInt(Day05::calculateSeatIndex)
               .max()
                .orElse(-1);
    }

    private  static int solveDay05b(List<String> input) {
        List<Integer> seats = input.stream()
                .mapToInt(Day05::calculateSeatIndex)
                .boxed()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        for (int i = 9; i < seats.size() - 8; i ++) {
            if (seats.get(i) - seats.get(i - 1) != 1) {
                return seats.get(i) - 1;
            }
        }
        return -1;
    }

    private static String toBinary(String input, String charZero, String charOne) {
        return input
                .replaceAll(charZero, "0")
                .replaceAll(charOne, "1");
    }

    private static int calculateSeatIndex(String input) {
        int row = Integer.parseInt(toBinary(input.substring(0, 7), "F", "B"), 2);
        int col = Integer.parseInt(toBinary(input.substring(7), "L", "R"), 2);
        return 8 * row + col;
    }
}
