package day03;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day03 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input03.txt");
        System.out.println(solveDay03a(input));
        System.out.println(solveDay03b(input));
    }

    private static int solveDay03a(List<String> input) {
        Day03Map map = new Day03Map(input);
        return getTreeCount(map, 3, 1);
    }

    private static int solveDay03b(List<String> input) {
        Day03Map map = new Day03Map(input);
        return getTreeCount(map, 1, 1) *
                getTreeCount(map, 3, 1) *
                getTreeCount(map, 5, 1) *
                getTreeCount(map, 7, 1) *
                getTreeCount(map, 1, 2);
    }

    private static int getTreeCount(Day03Map map, int deltaX, int deltaY) {
        int x = 0;
        int y = 0;
        int width = map.getWidth();
        int count = 0;
        while (y < map.getHeight()) {
            if (map.getCharAtLocation(x % width, y) == '#') {
                count++;
            }
            x += deltaX;
            y += deltaY;
        }
        return count;
    }
}
