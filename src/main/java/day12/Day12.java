package day12;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input12.txt");
        System.out.println(solveDay12a(input));
        System.out.println(solveDay12b(input));
    }

    private static long solveDay12a(List<String> input) {
        Ship ship = new Ship();
        for (String line: input) {
            String instruction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring((1)));
            if (instruction.equals("L")) {
                ship.rotateLeft(value);
            } else if (instruction.equals("R")) {
                ship.rotateRight(value);
            } else {
                ship.moveWithoutWayPoint(instruction, value);
            }
        }
        return ship.getDistance();
    }

    private static long solveDay12b(List<String> input) {
        Ship ship = new Ship(10L, -1L);
        for (String line: input) {
            String instruction = line.substring(0, 1);
            int value = Integer.parseInt(line.substring((1)));
            if (instruction.equals("L")) {
                ship.rotateWayPointLeft(value);
            } else if (instruction.equals("R")) {
                ship.rotateWayPointRight(value);
            } else {
                ship.moveWithWayPoint(instruction, value);
            }
        }
        return ship.getDistance();
    }

    //415579909629976
    //1811241464339425441
}
