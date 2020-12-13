package day12;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input12.txt");
        System.out.println(solveDay12a(input));



    }

    // 2752 too low

    private static int solveDay12a(List<String> input) {
        Ship ship = new Ship();
        for (String line: input) {
            String instruction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring((1)));
            if (instruction.equals("L")) {
                ship.rotateLeft(value);
            } else if (instruction.equals("R")) {
                ship.rotateRight(value);
            } else {
                ship.move(instruction, value);
            }
        }
        return ship.getDistance();
    }
}
