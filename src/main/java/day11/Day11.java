package day11;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input11.txt");
        SeatingMap seatingMap = new SeatingMap(input);
        System.out.println(solveDay11a(seatingMap, 4L, 'a'));
        System.out.println(solveDay11a(seatingMap, 5L, 'b'));
    }

    private static long solveDay11a(SeatingMap seatingMap, long tolerance, char mode) {
        boolean hasChanged = true;
        SeatingMap prevMap = seatingMap;
        while (hasChanged) {
            SeatingMap nextMap = prevMap.getNextMap(tolerance, mode);
            hasChanged = !nextMap.equals(prevMap);
            prevMap = nextMap;
        }
        return prevMap.toString().chars().filter(c -> c == '#').count();
    }
}
